package com.bielik.quwitestapp.screen.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.bielik.quwitestapp.R;
import com.bielik.quwitestapp.screen.login.LoginActivity;
import com.bielik.quwitestapp.screen.main.MainActivity;
import com.bielik.quwitestapp.util.PreferencesManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUpHandler();
    }

    private void setUpHandler() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (PreferencesManager.getInstance().getToken().isEmpty()) {
                goToLoginActivity();
            } else {
                goToMainActivity();
            }
            this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void goToMainActivity() {
        MainActivity.start(this);
    }

    private void goToLoginActivity() {
        LoginActivity.start(this);
    }
}