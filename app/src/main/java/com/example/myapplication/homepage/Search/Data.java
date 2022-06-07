package com.example.myapplication.homepage.Search;

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

    @Override
    public String toString()
    {
        return "ClassPojo [Score = "+Score+", _id = "+_id+", Image = "+Image+", Name = "+Name+"]";
    }
}
