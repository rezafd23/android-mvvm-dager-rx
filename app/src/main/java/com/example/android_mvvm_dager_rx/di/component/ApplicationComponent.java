package com.example.android_mvvm_dager_rx.di.component;

import android.app.Application;
import android.content.Context;

import com.example.android_mvvm_dager_rx.base.BaseApplication;
import com.example.android_mvvm_dager_rx.di.module.ActivityBindingModule;
import com.example.android_mvvm_dager_rx.di.module.ApplicationModule;
import com.example.android_mvvm_dager_rx.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
