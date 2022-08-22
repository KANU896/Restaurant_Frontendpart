package com.example.myapplication.Main_Screen.DayRecommend_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DR_ResponseData {
    @SerializedName("restaurant_id")
    @Expose
    String restaurant_id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("address")
    @Expose
    String address;

    public String getRestaurant_id() { return restaurant_id; }
    public String getName() { return name; }
    public String getImage() { return image; }
    public String getAddress() { return address; }
}
