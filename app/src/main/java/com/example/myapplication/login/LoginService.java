package com.example.myapplication.login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("/api/token/")
//    @POST("/test/")
    Call<LoginData> postOverlapCheck(@Field("username") String username, @Field("password") String password);
}
