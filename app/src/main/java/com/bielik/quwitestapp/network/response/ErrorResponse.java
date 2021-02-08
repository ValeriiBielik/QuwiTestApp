package com.bielik.quwitestapp.network.response;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("first_errors")
    @Expose
    private FirstErrorsResponse firstErrors;

    public FirstErrorsResponse getFirstErrors() {
        return firstErrors;
    }

    public static String parseLoginError(String error) {
        ErrorResponse errorResponse = new Gson().fromJson(error, ErrorResponse.class);
        if (errorResponse != null && errorResponse.firstErrors != null) {
            return errorResponse.firstErrors.getEmail();
        }
        return "";
    }
}
