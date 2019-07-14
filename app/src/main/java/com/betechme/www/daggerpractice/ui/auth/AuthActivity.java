package com.betechme.www.daggerpractice.ui.auth;



import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.betechme.www.daggerpractice.R;
import com.betechme.www.daggerpractice.models.User;
import com.betechme.www.daggerpractice.viewModels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {


    private static final String TAG = "AuthActivity" ;

    private AuthViewModel viewModel;

    private EditText userId;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscriberObservers();
    }

    private void subscriberObservers(){

        viewModel.observeUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){
                        case LOADING:{
                            showProgressbar(true);
                            break;
                        }

                        case AUTHENTICATED:{
                            showProgressbar(false);
                            Log.d(TAG, "onChanged LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            break;
                        }

                        case ERROR:{
                            showProgressbar(false);
                            Toast.makeText(AuthActivity.this, userAuthResource.message+"\nDid you enter a number between 1 and 10?", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        case NOT_AUTHENTICATED:{
                            showProgressbar(false);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void showProgressbar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:{
                attemptLogin();
                break;
            }
        }
    }

    private void attemptLogin() {
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }
}
