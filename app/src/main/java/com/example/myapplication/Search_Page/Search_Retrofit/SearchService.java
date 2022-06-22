package com.example.myapplication.Search_Page.Search_Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SearchService {
    @FormUrlEncoded
    @POST("/search/data/")
    Call<SearchData> postOverlapCheck(@Field("search_data") String search_data, @Field("category") String category);
}
