package com.example.myapplication.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreArrayData {
    @SerializedName("data")
    @Expose
    private StoreResponseData[] data;

    public StoreResponseData[] getData () {
        return data;
    }
}
