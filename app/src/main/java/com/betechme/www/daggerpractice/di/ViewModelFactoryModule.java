package com.betechme.www.daggerpractice.di;


import androidx.lifecycle.ViewModelProvider;

import com.betechme.www.daggerpractice.viewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);



}
