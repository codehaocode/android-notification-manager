package com.github.codehaocode.firstnotificationmanagerapp.model.repository;

import androidx.annotation.NonNull;

import com.github.codehaocode.firstnotificationmanagerapp.model.NotificationModel;
import com.github.codehaocode.firstnotificationmanagerapp.presentation.filter.period.FilterPeriod;

import java.util.List;

import io.reactivex.Flowable;

public interface NotificationsRepository {
    void addNotification(NotificationModel notificationModel);
    @NonNull
    Flowable<List<NotificationModel>> getNotifications(FilterPeriod period);
    int  getNotificationsCount();
}
