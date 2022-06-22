package com.example.myapplication.Search_Page.Search_Retrofit;

import com.google.gson.annotations.Expose;//object 중 null 값 자동 생략
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("Image")
    @Expose
    private String Image;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Score")
    @Expose
    private String Score;

    @SerializedName("Category")
    @Expose
    private String[] Category;

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

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

    public String[] getCategory() {
        return Category;
    }
    public void setCategory(String[] Category) {this.Category = Category;}

    @Override
    public String toString()
    {
        return "ClassPojo [Score = "+Score+", _id = "+_id+", Image = "+Image+", Name = "+Name+"]";
    }
}
