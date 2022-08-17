// 작성자 : 김도윤
// 로그인 서버 통신 때 들어오는 응답 데이터들 관리하는 데이터 클래스
// Update : 22.08.18

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
