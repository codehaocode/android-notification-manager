package com.github.codehaocode.firstnotificationmanagerapp.dependencyinjection;

import android.content.Context;

import com.github.codehaocode.firstnotificationmanagerapp.Application;
import com.github.codehaocode.firstnotificationmanagerapp.dependencyinjection.modules.ComponentsModule;
import com.github.codehaocode.firstnotificationmanagerapp.dependencyinjection.modules.ModelModule;
import com.github.codehaocode.firstnotificationmanagerapp.dependencyinjection.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ViewModelModule.class, ModelModule.class, ComponentsModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<Application> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }
}
