package com.example.android_mvvm_dager_rx.di.module;

import com.example.android_mvvm_dager_rx.ui.main.MainActivity;
import com.example.android_mvvm_dager_rx.ui.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

}
