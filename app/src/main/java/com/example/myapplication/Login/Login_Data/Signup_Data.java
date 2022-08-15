package com.example.myapplication.Login.Login_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signup_Data {
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMsg() {return msg;}
}
