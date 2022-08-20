package com.example.myapplication.Detail_Page.Detail_Data;

import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review_Data {
    @SerializedName("data")
    @Expose
    private Detail_Review_ResponseData[] review_data;

    public Detail_Review_ResponseData[] getReview_data () {
        return review_data;
    }
}
