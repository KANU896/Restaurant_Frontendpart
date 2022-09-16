package com.example.myapplication.Map.MapData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map_ResponseData {
    @SerializedName("_id")
    @Expose
    String restaurant_id;

    @SerializedName("Address")
    @Expose
    String address;

    @SerializedName("Name")
    @Expose
    String name;

    @SerializedName("latitude")
    @Expose
    double latitude;

    @SerializedName("longitude")
    @Expose
    double longitude;

    public String getRestaurant_id() { return restaurant_id; }
    public String getName() { return name; }
    public String getAddress() { return address; }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
