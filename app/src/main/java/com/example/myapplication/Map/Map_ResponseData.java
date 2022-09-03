package com.example.myapplication.Map;

public class Map_ResponseData {

    private String id;
    private String Name;
    private String Score;
    private String Address;
    private double latitude;
    private double longitude;


    public Map_ResponseData(String id, String Image, String Name, String Score, String Address, String Tag, String Menu, boolean Fav){

        this.id = id;
        this.Name = Name;
        this.Score = Score;
        this.Address = Address;
        this.latitude = latitude;
        this.longitude = longitude;



    }

    public String getId() {
        return id;
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
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }


}
