package com.example.myapplication.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map_Data {

    @SerializedName("_id")
    @Expose
    private String Id;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Score")
    @Expose
    private String Score;

    @SerializedName("Address")
    @Expose
    private String Address;

    @SerializedName("latitude")
    @Expose
    double latitude;

    @SerializedName("longitude")
    @Expose
    double longitude;

    public String getId() { return Id; }

    public String getName(){
        return Name;
    }

    public String getScore(){
        return Score;
    }

    public String getAddress(){
        return Address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}