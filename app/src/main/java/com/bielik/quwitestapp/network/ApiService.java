package com.bielik.quwitestapp.network;

import androidx.lifecycle.LiveData;

import com.bielik.quwitestapp.network.response.LoginResponse;
import com.bielik.quwitestapp.network.response.ProjectResponse;
import com.bielik.quwitestapp.network.response.TicketsResponse;
import com.bielik.quwitestapp.network.util.ApiResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    LiveData<ApiResponse<LoginResponse>> login(@Field("email") String email, @Field("password") String password);

    @GET("projects-manage/index")
    LiveData<ApiResponse<ProjectResponse>> getProjects();

    @GET("issues?")
    LiveData<ApiResponse<TicketsResponse>> getTickets(@Query("filters[id_project]") long id);

    @FormUrlEncoded
    @POST("projects-manage/update")
    LiveData<ApiResponse<Object>> editProjectName(@Query("id") long id, @Field("name") String newName);
}
