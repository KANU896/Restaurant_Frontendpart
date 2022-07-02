package com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_Detail {
    @SerializedName("Image")
    @Expose
    private String Image;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Score")
    @Expose
    private String Score;

    @SerializedName("menu")
    @Expose
    private String Menu[];

    public String getImage() {
        return Image;
    }
    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getScore() {
        return Score;
    }
    public void setScore(String Score) {
        this.Score = Score;
    }

    public String[] getMenu() {
        return Menu;
    }
    public void setMenu(String[] Menu) {this.Menu = Menu;}

    @Override
    public String toString()
    {
        return "ClassPojo [Score = "+Score+", Image = "+Image+", Name = "+Name+"]";
    }
}
