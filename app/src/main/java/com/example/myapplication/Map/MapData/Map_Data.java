package com.example.myapplication.Map.MapData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map_Data {
    @SerializedName("data")
    @Expose
    private Map_ResponseData[] data;

    public Map_ResponseData[] getData () {
        return data;
    }
}
