// 작성자 : 김도윤
// Token 복호화 클래스
// Update : 22.08.18

package com.example.myapplication.util;

import android.util.Base64;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class JWTUtils {
    public static JSONObject decoded(String JWTEncoded){
        String[] split = JWTEncoded.split("\\.");
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(JWTUtils.getJson(split[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObject;
    }

    public static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
