// 작성자 : 김도윤
// 서버 전송 시 필요한 데이터, url 등 관리하는 인터페이스
// Update : 22.08.18

package com.example.myapplication.Common;

import com.example.myapplication.Detail_Page.Detail_Data.Detail_ResponseData;
import com.example.myapplication.Detail_Page.Detail_Data.Review_Data;
import com.example.myapplication.Login.Login_Data.Login_Token;
import com.example.myapplication.Login.Login_Data.Signup_Data;
import com.example.myapplication.Home.DayRecommend_Data.DR_Data;
import com.example.myapplication.Map.MapData.Map_Data;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.SearchData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Retrofit_Service {
    //검색한 음식점 리스트 불러오기
    @FormUrlEncoded
    @POST("/search/data/")
    Call<SearchData> Search_post(@Field("search_data") String search_data, @Field("category") String category,
                                      @Field("location") String location);

    //선택한 음식점 정보 불러오기
    @FormUrlEncoded
    @POST("/detail/random/")
    Call<Detail_ResponseData> Detail_random(@Field("location") String location, @Field("category") String category);


    //선택한 음식점 정보 불러오기
    @FormUrlEncoded
    @POST("/detail/data/")
    Call<Detail_ResponseData> Detail_post(@Field("restaurant_id") String restaurant_id);

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
    Call<Void> favorite_put(@Field("restaurant_id") String restaurant_id);

    //즐겨찾기 삭제
    @FormUrlEncoded
    @POST("/detail/favorite/")
    Call<Void> favorite_delete(@Field("restaurant_id") String restaurant_id);

    //즐겨찾기 목록
    @GET("/detail/favorite/")
    Call<SearchData> favorite_list();

    //리뷰 검색
    @FormUrlEncoded
    @POST("/detail/review/")
    Call<Review_Data> Review_list(@Field("restaurant_id") String restaurant_id);

    //리뷰 등록
    @FormUrlEncoded
    @PUT("/detail/review/")
    Call<Review_Data> Review_input(@Field("restaurant_id") String restaurant_id,
                                                  @Field("content") String content);

    //리뷰 삭제
    @FormUrlEncoded
    @POST("/detail/review/delete/")
    Call<Review_Data> Review_delete(@Field("review_id") int review_id);

    //일일 음식점 추천
    @FormUrlEncoded
    @POST("/day/recommend/")
    Call<DR_Data> Day_recommend(@Field("city") String city, @Field("category") String category);

    // 내 위치 주변 음식점
    @FormUrlEncoded
    @POST("/map/location/")
    Call<Map_Data> Map(@Field("latitude") double latitude, @Field("longitude") double longitude, @Field("category") String category);
}
