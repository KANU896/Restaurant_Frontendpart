package com.example.myapplication.Main_Screen.Search_Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient_Search {
    private static final String BASE_URL = "http://192.168.219.101:8000"; // 서버 ip 주소
            //"http://10.9.203.128:8000";
            //"http://192.168.219.103:8000"; // 서버 ip 주소
    public static SearchService getApiService(){return getInstance().create(SearchService.class);}

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
