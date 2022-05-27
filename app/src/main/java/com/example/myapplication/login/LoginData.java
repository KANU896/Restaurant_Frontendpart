package com.example.myapplication.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }
}
