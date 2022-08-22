package com.example.myapplication.Main_Screen.DayRecommend_Data;

public class DR_Datastore {
    private String Restaurant_id;
    private String Name;
    private String Image;
    private String Address;

    public DR_Datastore(String Restaurant_id, String Name, String Image, String Address){
        this.Restaurant_id = Restaurant_id;
        this.Name = Name;
        this.Image = Image;
        this.Address = Address;

    }
    public String getRestaurant_id() { return Restaurant_id; }
    public String getName() {
        return Name;
    }
    public String getImage(){
        return Image;
    }
    public String getAddress() {
        return Address;
    }
}
