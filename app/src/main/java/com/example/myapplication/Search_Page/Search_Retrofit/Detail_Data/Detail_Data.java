package com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail_Data {
    @SerializedName("Address")
    @Expose
    private String Address;

    @SerializedName("Image")
    @Expose
    private String Image;

    @SerializedName("Menu")
    @Expose
    private String Menu;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Score")
    @Expose
    private String Score;

    @SerializedName("Tag")
    @Expose
    private String Tag;

    @SerializedName("fav")
    @Expose
    private boolean fav;

    public String getImage(){
        return Image;
    }

    public String getScore(){
        return Score;
    }

    public String getName(){
        return Name;
    }

    public String getMenu(){
        return Menu;
    }

    public String getAddress(){
        return Address;
    }

    public String getTag(){
        return Tag;
    }

    public boolean getFav() { return fav; }

    @Override
    public String toString() {
        return "ClassPojo [data = ";
    }
}
