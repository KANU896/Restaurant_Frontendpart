package com.example.myapplication.Main_Screen;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private  String Id;
    private  String Image;
    private  String Name;
    private  String Score;

    public ResponseData(String id, String Image, String Name, String Score){
        this.Id = id;
        this.Image = Image;
        this.Name = Name;
        this.Score = Score;
    }

    public String getId() {
        return Id;
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
}
