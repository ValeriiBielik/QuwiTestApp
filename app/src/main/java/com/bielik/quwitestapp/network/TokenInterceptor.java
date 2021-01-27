package com.bielik.quwitestapp.network;

import androidx.annotation.NonNull;

import com.bielik.quwitestapp.util.PreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String token = PreferencesManager.getInstance().getToken();
        if (!token.isEmpty()) {
            builder = builder.addHeader("Authorization", "Bearer " + token);
        }
        return chain.proceed(builder.build());
    }
}
