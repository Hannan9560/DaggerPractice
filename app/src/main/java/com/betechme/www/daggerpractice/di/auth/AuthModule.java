package com.betechme.www.daggerpractice.di.auth;

import com.betechme.www.daggerpractice.models.User;
import com.betechme.www.daggerpractice.network.auth.AuthApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    static User someSuer(){
        return new User();
    }

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }


}
