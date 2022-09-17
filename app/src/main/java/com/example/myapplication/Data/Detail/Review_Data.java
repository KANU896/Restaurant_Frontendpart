package com.example.myapplication.Data.Detail;

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
