package com.example.myapplication.Common;

import com.example.myapplication.Login.Login_Data.Login_Token;
import com.example.myapplication.Login.Login_Data.Signup_Data;
import com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data.Detail_Data;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.SearchData;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Retrofit_Service {
    //검색한 음식점 리스트 불러오기
    @FormUrlEncoded
    @POST("/search/data/")
    Call<SearchData> postOverlapCheck(@Field("search_data") String search_data, @Field("category") String category);


    //선택한 음식점 정보 불러오기
    @FormUrlEncoded
    @POST("/detail/data/")
    Call<Detail_Data> Detail_post(@Field("mongo_id") String mongo_id, @Field("username") String username);

    //로그인
    @FormUrlEncoded
    @POST("/account/login/")
    Call<Login_Token> Login_post(@Field("username") String username, @Field("password") String password);

    //회원가입
    @FormUrlEncoded
    @POST("/account/signup/")
    Call<Signup_Data> Signup_post(@Field("username") String username, @Field("password") String password, @Field("name") String name);

    //아이디 중복 확인
    @FormUrlEncoded
    @POST("/account/idcheck/")
    Call<Signup_Data> IDCheck_post(@Field("username") String username);

    //즐겨찾기 추가
    @FormUrlEncoded
    @PUT("/detail/favorite/")
    Call<Void> favorite_put(@Field("mongo_id") String mongo_id, @Field("username") String username,
                            @Header("Authorization") String token);

    //즐겨찾기 삭제
    @FormUrlEncoded
    @POST("/detail/favorite/")
    Call<Void> favorite_delete(@Field("mongo_id") String mongo_id, @Field("username") String username,
                               @Header("Authorization") String token);

    //즐겨찾기 목록
    @FormUrlEncoded
    @POST("/detail/favorite_list/")
    Call<SearchData> favorite_list(@Field("") String a, @Header("Authorization") String token);
}
