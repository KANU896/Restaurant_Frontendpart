package com.example.myapplication.Main_Screen.DayRecommend_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DR_Data {
    @SerializedName("data")
    @Expose
    private DR_ResponseData[] data;

    public DR_ResponseData[] getData () {
        return data;
    }
}
