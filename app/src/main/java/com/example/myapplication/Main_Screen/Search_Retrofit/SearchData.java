package com.example.myapplication.Main_Screen.Search_Retrofit;

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
