package com.bielik.quwitestapp.screen.login;

import com.bielik.quwitestapp.base.BaseView;

public interface LoginActivityView extends BaseView {

    void onEmailValidationError();
    void onPasswordValidationError();
    void onCredentialsValidated(String email, String password);
    void onUserLoggedIn();
}
