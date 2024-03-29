package com.betechme.www.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import com.betechme.www.daggerpractice.di.ViewModelKey;
import com.betechme.www.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);



}
