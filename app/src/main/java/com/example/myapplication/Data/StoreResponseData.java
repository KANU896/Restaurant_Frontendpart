package com.example.myapplication.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreResponseData implements Serializable {
    @SerializedName("_id")
    @Expose
    private String Id;

    @SerializedName("City")
    @Expose
    private String City;

    @SerializedName("Address")
    @Expose
    private String Address;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Tell_number")
    @Expose
    private String Tell_number;

    @SerializedName("Score")
    @Expose
    private String Score;

    @SerializedName("Category")
    @Expose
    private String Category;

    @SerializedName("Tag")
    @Expose
    private String Tag;

    @SerializedName("fav")
    @Expose
    private boolean fav;

    @SerializedName("Open_Time")
    @Expose
    private String open_time;

    public StoreResponseData(String id, String city, String address, String name, String tell, String score, String category, String tag, boolean fav){
        this.Id = id;
        this.City = city;
        this.Address = address;
        this.Name = name;
        this.Tell_number = tell;
        this.Score = score;
        this.Category = category;
        this.Tag = tag;
        this.fav = fav;
    }

    public String getId() { return Id; }

    public String getCity(){
        return City;
    }

    public String getAddress(){
        return Address;
    }

    public String getName(){
        return Name;
    }

    public String getTell_number(){
        return Tell_number;
    }

    public String getScore(){
        return Score;
    }

    public String getCategory(){
        return Category;
    }

    public String getTag(){
        return Tag;
    }

    public boolean getFav() { return fav; }

    public String getOpen_time() { return open_time; }
}
