package com.example.myapplication.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayRecommendResponseData {
    @SerializedName("restaurant_id")
    @Expose
    String restaurant_id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("address")
    @Expose
    String address;

    public String getRestaurant_id() { return restaurant_id; }
    public String getName() { return name; }
    public String getAddress() { return address; }

    public DayRecommendResponseData(String Restaurant_id, String Name, String Address){
        this.restaurant_id = Restaurant_id;
        this.name = Name;
        this.address = Address;

    }
}
