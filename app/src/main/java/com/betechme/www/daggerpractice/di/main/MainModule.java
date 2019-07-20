package com.betechme.www.daggerpractice.di.main;


import com.betechme.www.daggerpractice.network.main.MainApi;
import com.betechme.www.daggerpractice.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {


    @MainScope
    @Provides
    static PostsRecyclerAdapter providerAdapter(){
        return new PostsRecyclerAdapter();
    }

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}



