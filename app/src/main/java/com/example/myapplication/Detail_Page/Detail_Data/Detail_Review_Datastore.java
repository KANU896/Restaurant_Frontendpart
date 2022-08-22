package com.example.myapplication.Detail_Page.Detail_Data;

import java.util.Date;

public class Detail_Review_Datastore {private int review_id;
    private String Name;
    private String Content;
    private String Datetime;

    public Detail_Review_Datastore(int review_id, String Name, String Content, String Datetime){
        this.review_id = review_id;
        this.Name = Name;
        this.Content = Content;
        this.Datetime = Datetime;

    }
    public int getReview_id() { return review_id; }
    public String getName() {
        return Name;
    }
    public String getContent(){
        return Content;
    }
    public String getDatetime() {
        return Datetime;
    }

}
