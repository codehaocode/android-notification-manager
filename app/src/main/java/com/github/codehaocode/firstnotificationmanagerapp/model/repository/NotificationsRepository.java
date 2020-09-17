package com.github.codehaocode.firstnotificationmanagerapp.model.repository;

import androidx.annotation.NonNull;

import com.github.codehaocode.firstnotificationmanagerapp.model.NotificationModel;

import java.util.List;

public interface NotificationsRepository {
    void addNotification(NotificationModel notificationModel);
    @NonNull
    Flowable<List<NotificationModel>> getNotifications(FilterPeriod period);
    int  getNotificationsCount();
}
