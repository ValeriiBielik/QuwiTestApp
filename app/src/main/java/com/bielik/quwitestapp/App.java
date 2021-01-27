package com.bielik.quwitestapp;

import android.app.Application;

import com.bielik.quwitestapp.util.PreferencesManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesManager.initializeInstance(this);
    }
}
