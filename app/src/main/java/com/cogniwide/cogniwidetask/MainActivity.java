package com.cogniwide.cogniwidetask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import com.cogniwide.cogniwidetask.databinding.ActivityMainBinding;

import model.LoginModel;
import viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mMainBinding;
    private LoginViewModel mLoginViewModel;
    boolean isEmailCorrect, isPasswordCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.setLoginModel(mLoginViewModel);
        mMainBinding.button.setEnabled(false);
        mLoginViewModel.getEmailAddress().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    isEmailCorrect = true;
                    setEnableLoginButton();
                } else {
                    isEmailCorrect = false;
                    mMainBinding.button.setBackgroundColor(getColor(R.color.grey));
                }
            }
        });

        mLoginViewModel.getPassword().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty() && s.length() > 5) {
                    isPasswordCorrect = true;
                    setEnableLoginButton();
                } else {
                    isPasswordCorrect = false;
                    mMainBinding.button.setBackgroundColor(getColor(R.color.grey));
                }
            }
        });

        mLoginViewModel.getLoginData().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                Intent intent = new Intent(getApplicationContext(), PopularMoviesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void setEnableLoginButton() {
        mMainBinding.button.setEnabled(isEmailCorrect && isPasswordCorrect);
        if(isEmailCorrect && isPasswordCorrect) {
            mMainBinding.button.setBackgroundColor(getColor(R.color.purple_500));
        }

    }
}
