package com.bielik.quwitestapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREF_NAME = "quwi_prefs";
    private static final String KEY_TOKEN = "token";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void saveToken(String token) {
        mPref.edit()
                .putString(KEY_TOKEN, token)
                .apply();
    }

    public String getToken() {
        return mPref.getString(KEY_TOKEN, "");
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .apply();
    }
}
