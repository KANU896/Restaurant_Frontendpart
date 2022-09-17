package com.example.myapplication.Data.DayRecommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayRecommendData {
    @SerializedName("data")
    @Expose
    private DayRecommendResponseData[] data;

    public DayRecommendResponseData[] getData () {
        return data;
    }
}
