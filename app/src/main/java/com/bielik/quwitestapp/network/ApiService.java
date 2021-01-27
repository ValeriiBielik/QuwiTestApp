package com.bielik.quwitestapp.network;

import androidx.lifecycle.LiveData;

import com.bielik.quwitestapp.network.response.LoginResponse;
import com.bielik.quwitestapp.network.util.ApiResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    LiveData<ApiResponse<LoginResponse>> login(@Field("email") String email, @Field("password") String password);

    @POST("auth/logout")
    Call<Response<Object>> logout();

    @GET("projects-manage/index")
    Call<Response<Object>> getProjects();

    @FormUrlEncoded
    @POST("projects-manage/update")
    Call<Response<Object>> updateProjectName(@Query("id") long id, @Field("name") String newName);
}
