package com.example.android_mvvm_dager_rx.ui.main;

import com.example.android_mvvm_dager_rx.ui.detail.DetailsFragment;
import com.example.android_mvvm_dager_rx.ui.list.ListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {
    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}
