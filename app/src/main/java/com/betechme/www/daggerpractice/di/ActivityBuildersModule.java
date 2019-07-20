package com.betechme.www.daggerpractice.di;


import com.betechme.www.daggerpractice.di.auth.AuthModule;
import com.betechme.www.daggerpractice.di.auth.AuthScope;
import com.betechme.www.daggerpractice.di.auth.AuthViewModelsModule;
import com.betechme.www.daggerpractice.di.main.MainFragmentBuildersModule;
import com.betechme.www.daggerpractice.di.main.MainModule;
import com.betechme.www.daggerpractice.di.main.MainScope;
import com.betechme.www.daggerpractice.di.main.MainViewModelsModule;
import com.betechme.www.daggerpractice.network.auth.AuthApi;
import com.betechme.www.daggerpractice.ui.auth.AuthActivity;
import com.betechme.www.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class,}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class}
    )
    abstract MainActivity contributeMainActivity();


}
