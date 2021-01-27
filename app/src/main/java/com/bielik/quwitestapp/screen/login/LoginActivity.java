package com.bielik.quwitestapp.screen.login;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(R.layout.activity_login);

        initViewModel();
        initListeners();
        checkIsLoggedIn();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.attachView(this);
    }

    private void initListeners() {
        binding.btnLogin.setOnClickListener(v -> {
            hideErrors();
            viewModel.validateCredentials(binding.etEmail.getText(), binding.etPassword.getText());
        });
    }

    private void checkIsLoggedIn() {
        if (viewModel.checkIsLoggedIn()) {
            onUserLoggedIn();
        }
    }

    @Override
    public void onEmailValidationError() {
        binding.etlEmail.setError(getString(R.string.validation_error));
    }

    @Override
    public void onPasswordValidationError() {
        binding.etlPassword.setError(getString(R.string.validation_error));
    }

    @Override
    public void onCredentialsValidated(String email, String password) {
        viewModel.doLogin(email, password);
    }

    @Override
    public void onUserLoggedIn() {
        MainActivity.start(this);
    }

    private void hideErrors() {
        binding.etlEmail.setError(null);
        binding.etlPassword.setError(null);
    }

    @Override
    public void onError(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
        Log.e(TAG, err);
    }
}