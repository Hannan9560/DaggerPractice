package com.betechme.www.daggerpractice.di;


import com.betechme.www.daggerpractice.di.auth.AuthModule;
import com.betechme.www.daggerpractice.di.auth.AuthViewModelsModule;
import com.betechme.www.daggerpractice.network.auth.AuthApi;
import com.betechme.www.daggerpractice.ui.auth.AuthActivity;
import com.betechme.www.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class,}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();


}
