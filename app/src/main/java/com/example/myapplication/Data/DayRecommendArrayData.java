package com.example.myapplication.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayRecommendArrayData {
    @SerializedName("data")
    @Expose
    private DayRecommendResponseData[] data;

    public DayRecommendResponseData[] getData () {
        return data;
    }
}
