package com.example.myapplication.Detail_Page.Detail_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail_Review_ResponseData {
    @SerializedName("review_id")
    @Expose
    private int review_id;

    @SerializedName("username")
    @Expose
    private String Username;

    @SerializedName("restaurant_id")
    @Expose
    private String restaurant_id;

    @SerializedName("content")
    @Expose
    private String Content;

    @SerializedName("image1")
    @Expose
    private String image1;

    @SerializedName("image2")
    @Expose
    private String image2;

    @SerializedName("image3")
    @Expose
    private String image3;

    @SerializedName("score")
    @Expose
    private String Score;

    @SerializedName("date_time")
    @Expose
    private String Date;

    public int getReview_id() { return review_id; }
    public void setReview_id(int review_id) { this.review_id = review_id; }

    public String getUsername(){
        return Username;
    }
    public void setUsername(String username) { this.Username = Username; }

    public String getScore(){
        return Score;
    }
    public void setScore(String score) { this.Score = Score; }

    public String getDate(){
        return Date;
    }

    public String getContent(){
        return Content;
    }
}
