package com.github.codehaocode.firstnotificationmanagerapp.services;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.github.codehaocode.firstnotificationmanagerapp.receiver.NotificationReceiver;

import java.util.Calendar;

import static com.github.codehaocode.firstnotificationmanagerapp.services.NotificationProcessorService.EXTRA_NOTIFICATION_APP_NAME;
import static com.github.codehaocode.firstnotificationmanagerapp.services.NotificationProcessorService.EXTRA_NOTIFICATION_APP_PACKAGE_NAME;
import static com.github.codehaocode.firstnotificationmanagerapp.services.NotificationProcessorService.EXTRA_NOTIFICATION_CALENDAR;
import static com.github.codehaocode.firstnotificationmanagerapp.services.NotificationProcessorService.EXTRA_NOTIFICATION_ID;
import static com.github.codehaocode.firstnotificationmanagerapp.services.NotificationProcessorService.EXTRA_NOTIFICATION_TEXT;


public class NotificationListener extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if (getPackageName().equals(sbn.getPackageName())) return;

        parseNotification(sbn);
    }

    private void parseNotification(StatusBarNotification sbn) {
        String appName = "";
        String text = "";
        String notificationTitle = sbn.getNotification().extras.getString(Notification.EXTRA_TITLE);
        String notificationText = sbn.getNotification().extras.getString(Notification.EXTRA_TEXT);
        if (notificationTitle != null)
            text += notificationTitle + " ";
        if (notificationText != null)
            text += notificationText;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(sbn.getPackageName(), 0);
            appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sbn.getPostTime());

        sendBroadcast(sbn.getId(), sbn.getPackageName(), appName, text, calendar);
        sendBroadcastToApp2(sbn.getId(), sbn.getPackageName(), appName, text, calendar);
    }

    private void sendBroadcast(int notificationId, String packageName, String appName, String text, Calendar calendar) {
        Intent intent = new Intent(NotificationReceiver.ACTION_NOTIFICATION_RECEIVED);
        intent.setAction("com.github.codehaocode.firstnotificationmanagerapp");
        intent.putExtra(EXTRA_NOTIFICATION_ID, notificationId);
        intent.putExtra(EXTRA_NOTIFICATION_APP_NAME, appName);
        intent.putExtra(EXTRA_NOTIFICATION_APP_PACKAGE_NAME, packageName);
        intent.putExtra(EXTRA_NOTIFICATION_CALENDAR, calendar);
        intent.putExtra(EXTRA_NOTIFICATION_TEXT, text);
        sendBroadcast(intent);
    }

    private void sendBroadcastToApp2(int notificationId, String packageName, String appName, String text, Calendar calendar) {
        Intent intentForOtherApp = new Intent(NotificationReceiver.ACTION_NOTIFICATION_RECEIVED);
        intentForOtherApp.setAction("com.github.codehaocode.notificationreceiverapp");
        intentForOtherApp.putExtra(EXTRA_NOTIFICATION_ID, notificationId);
        intentForOtherApp.putExtra(EXTRA_NOTIFICATION_APP_NAME, appName);
        intentForOtherApp.putExtra(EXTRA_NOTIFICATION_APP_PACKAGE_NAME, packageName);
        intentForOtherApp.putExtra(EXTRA_NOTIFICATION_CALENDAR, calendar);
        intentForOtherApp.putExtra(EXTRA_NOTIFICATION_TEXT, text);
        sendBroadcast(intentForOtherApp);
    }

}
