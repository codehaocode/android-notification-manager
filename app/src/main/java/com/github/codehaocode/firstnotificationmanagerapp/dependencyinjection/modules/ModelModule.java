package com.github.codehaocode.firstnotificationmanagerapp.dependencyinjection.modules;

import android.content.Context;

import com.github.codehaocode.firstnotificationmanagerapp.Application;
import com.github.codehaocode.firstnotificationmanagerapp.model.database.NotificationsDatabase;
import com.github.codehaocode.firstnotificationmanagerapp.model.mapper.NotificationTypesMapper;
import com.github.codehaocode.firstnotificationmanagerapp.model.repository.NotificationsRepository;
import com.github.codehaocode.firstnotificationmanagerapp.model.repository.NotificationsRepositoryImpl;
import com.github.codehaocode.firstnotificationmanagerapp.presentation.filter.FilterMode;
import com.github.codehaocode.firstnotificationmanagerapp.receiver.NotificationReceiver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

@Module
public class ModelModule {

    @Provides
    @Singleton
    public NotificationsDatabase provideDatabase(Context context){
        return NotificationsDatabase.getInstance(context);
    }

    @Provides
    @Singleton
    public NotificationsRepository provideRepository(NotificationsDatabase notificationsDatabase) {
        return new NotificationsRepositoryImpl(
                new NotificationTypesMapper(),
                notificationsDatabase.notificationsDao()
        );
    }

    @Provides
    public NotificationReceiver provideNotificationReceiver() {
        return new NotificationReceiver();
    }

    @Provides
    public boolean provideServiceActivityStatus() {
        return Application.isForegroundServiceRunning();
    }

    @Provides
    public FilterMode provideDefaultFilterMode() {
        return FilterMode.ALL;
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    public Subject<FilterMode> provideBehaviorSubject() {
        return BehaviorSubject.create();
    }
}
