package com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data;

import java.io.Serializable;

public class Detail_ResponseData implements Serializable {

    private String Image;
    private String Name;
    private String Score;
    private String Address;
    private String Tag;
    private String Menu;

    public Detail_ResponseData(String Image, String Name, String Score, String Address, String Tag, String Menu){
        this.Image = Image;
        this.Name = Name;
        this.Score = Score;
        this.Address = Address;
        this.Tag = Tag;
        this.Menu = Menu;
    }

    public String getImage(){
        return Image;
    }
    public String getName() {
        return Name;
    }
    public String getScore(){
        return Score;
    }
    public String getAddress(){
        return Address;
    }
    public String getTag() {
        return Tag;
    }
    public String getMenu(){
        return Menu;
    }
}
