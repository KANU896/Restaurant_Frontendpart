package com.example.myapplication.Login.Login_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_Token {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getToken(){
        return token;
    }
    public String getMsg() {return msg;}
}
