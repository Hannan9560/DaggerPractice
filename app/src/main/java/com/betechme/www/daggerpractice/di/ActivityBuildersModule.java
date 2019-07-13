package com.betechme.www.daggerpractice.di;


import com.betechme.www.daggerpractice.di.auth.AuthViewModelsModule;
import com.betechme.www.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();


}
