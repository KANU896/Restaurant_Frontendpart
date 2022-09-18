package com.example.myapplication.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapArrayData {
    @SerializedName("data")
    @Expose
    private Map_ResponseData[] data;

    public Map_ResponseData[] getData () {
        return data;
    }
}
