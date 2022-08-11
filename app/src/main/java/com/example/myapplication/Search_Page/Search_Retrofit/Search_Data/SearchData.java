package com.example.myapplication.Search_Page.Search_Retrofit.Search_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchData {
    @SerializedName("data")
    @Expose
    private Data[] data;

    public Data[] getData () {
        return data;
    }

    public void setData (Data[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [data = "+data+"]";
    }

}
