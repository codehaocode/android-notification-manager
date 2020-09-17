package com.github.codehaocode.firstnotificationmanagerapp.model.repository;

import androidx.annotation.NonNull;

import com.github.codehaocode.firstnotificationmanagerapp.model.NotificationModel;

import java.util.List;

public class NotificationsRepositoryImpl implements NotificationsRepository {

    private TypeMapper<NotificationModel, NotificationEntity> mapper;
    private NotificationsDao notificationsDao;

    public NotificationsRepositoryImpl(
            TypeMapper<NotificationModel, NotificationEntity> mapper,
            NotificationsDao notificationsDao) {
        this.mapper = mapper;
        this.notificationsDao = notificationsDao;
    }

    @Override
    public void addNotification(NotificationModel notificationModel) {
        notificationsDao.insertNotification(mapper.mapToEntity(notificationModel));
    }

    @NonNull
    @Override
    public Flowable<List<NotificationModel>> getNotifications(FilterPeriod period) {
        return notificationsDao.getNotification(period.getStartPeriod()).map(notificationEntities -> mapper.mapToModel(notificationEntities));
    }

    @Override
    public int getNotificationsCount() {
        return notificationsDao.getNotificationsCount();
    }
}

