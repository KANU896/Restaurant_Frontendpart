package com.example.myapplication.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewArrayData {
    @SerializedName("data")
    @Expose
    private Detail_Review_ResponseData[] review_data;

    public Detail_Review_ResponseData[] getReview_data () {
        return review_data;
    }
}
