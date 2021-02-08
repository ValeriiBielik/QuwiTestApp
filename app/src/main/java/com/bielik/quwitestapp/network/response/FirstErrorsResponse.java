package com.bielik.quwitestapp.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstErrorsResponse {

    @SerializedName("email")
    @Expose
    private String email;

    public String getEmail() {
        return email == null ? "" : email;
    }
}
