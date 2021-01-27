package com.bielik.quwitestapp.screen.login;

import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;

import com.bielik.quwitestapp.base.BaseViewModel;
import com.bielik.quwitestapp.network.ApiService;
import com.bielik.quwitestapp.network.RetrofitService;
import com.bielik.quwitestapp.util.PreferencesManager;

import static java.net.HttpURLConnection.HTTP_OK;

public class LoginViewModel extends BaseViewModel<LoginActivityView> {

    private final ApiService api = RetrofitService.getApi();

    public void validateCredentials(Editable email, Editable password) {
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.onEmailValidationError();
        } else if (TextUtils.isEmpty(password)) {
            view.onPasswordValidationError();
        } else {
            view.onCredentialsValidated(email.toString(), password.toString());
        }
    }

    public void doLogin(String email, String password) {
        api.login(email, password).observeForever(response -> {
            if (response.isSuccessful() && response.code == HTTP_OK) {
                if (response.body != null) {
                    PreferencesManager.getInstance().saveToken(response.body.getToken());
                    view.onUserLoggedIn();
                    return;
                }
            }
            if (response.errorMessage != null) {
                view.onError(response.errorMessage);
            }
        });
    }

    public boolean checkIsLoggedIn() {
        return !PreferencesManager.getInstance().getToken().isEmpty();
    }
}
