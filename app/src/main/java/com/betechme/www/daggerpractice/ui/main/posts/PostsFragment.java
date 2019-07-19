package com.betechme.www.daggerpractice.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betechme.www.daggerpractice.R;
import com.betechme.www.daggerpractice.models.Post;
import com.betechme.www.daggerpractice.ui.main.Resource;
import com.betechme.www.daggerpractice.ui.util.VerticalSpacingItemDecoration;
import com.betechme.www.daggerpractice.viewModels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";
    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostsRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        initRecyclerView();
        subscribeObserver();
    }

    private void subscribeObserver(){
        viewModel.observerPosts().removeObservers(getViewLifecycleOwner());
        viewModel.observerPosts().observe(this, new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource != null){
                   switch (listResource.status){
                       case LOADING:{
                           Log.d(TAG, "onChanged: Loading");
                           break;
                       }
                       case SUCCESS:{
                           Log.d(TAG, "onChanged: got posts");
                           adapter.setPosts(listResource.data);
                           break;
                       }
                       case ERROR:{
                           Log.e(TAG, "onChanged: ERROR..." + listResource.message );
                       }
                   }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
