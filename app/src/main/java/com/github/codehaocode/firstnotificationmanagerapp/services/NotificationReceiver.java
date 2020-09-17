package com.github.codehaocode.firstnotificationmanagerapp.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Objects;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String ACTION_NOTIFICATION_RECEIVED = "com.github.cr9ck.notificationrecorder.receiver.NotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ACTION_NOTIFICATION_RECEIVED)) {

            Intent processorService = new Intent(context, NotificationProcessorService.class);

            processorService.putExtra(EXTRA_NOTIFICATION_ID, intent.getIntExtra(EXTRA_NOTIFICATION_ID, -1));
            processorService.putExtra(EXTRA_NOTIFICATION_APP_NAME, intent.getStringExtra(EXTRA_NOTIFICATION_APP_NAME));
            processorService.putExtra(EXTRA_NOTIFICATION_APP_PACKAGE_NAME, intent.getStringExtra(EXTRA_NOTIFICATION_APP_PACKAGE_NAME));
            processorService.putExtra(EXTRA_NOTIFICATION_TEXT, intent.getStringExtra(EXTRA_NOTIFICATION_TEXT));
            processorService.putExtra(EXTRA_NOTIFICATION_CALENDAR, intent.getSerializableExtra(EXTRA_NOTIFICATION_CALENDAR));
            NotificationProcessor.enqueueWork(context, processorService);
        }
    }
}