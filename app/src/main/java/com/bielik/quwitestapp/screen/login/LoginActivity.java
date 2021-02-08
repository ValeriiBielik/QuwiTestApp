package com.bielik.quwitestapp.screen.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.bielik.quwitestapp.R;
import com.bielik.quwitestapp.base.BaseActivity;
import com.bielik.quwitestapp.databinding.ActivityLoginBinding;
import com.bielik.quwitestapp.screen.main.MainActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginActivityView {

    private static final String TAG = "LoginActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(R.layout.activity_login);

        initViewModel();
        initListeners();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.attachView(this);
    }

    private void initListeners() {
        binding.btnLogin.setOnClickListener(v -> {
            showLoader();
            hideErrors();
            viewModel.validateCredentials(binding.etEmail.getText(), binding.etPassword.getText());
        });
    }

    @Override
    public void onEmailValidationError() {
        hideLoader();
        binding.etlEmail.setError(getString(R.string.email_validation_error));
    }

    @Override
    public void onPasswordValidationError() {
        hideLoader();
        binding.etlPassword.setError(getString(R.string.password_validation_error));
    }

    @Override
    public void onCredentialsValidated(String email, String password) {
        viewModel.doLogin(email, password);
    }

    @Override
    public void onUserLoggedIn() {
        hideLoader();
        MainActivity.start(this);
        finish();
    }

    private void hideErrors() {
        binding.etlEmail.setError(null);
        binding.etlPassword.setError(null);
    }

    @Override
    public void onError(String err) {
        hideLoader();
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
        Log.e(TAG, err);
    }
}