// 작성자 : 김도윤
// Retrofit Client
// Update : 22.08.18

package com.example.myapplication.Common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://192.168.219.103:8000"; // 서버 ip 주소

    public static Retrofit_Service getApiService(){
        return getInstance().create(Retrofit_Service.class);
    }

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthInterceptor()).build();

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
