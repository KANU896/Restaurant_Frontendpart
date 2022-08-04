package com.example.myapplication.Common;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    Context context;
    String Data_Store;
    public SharedPreferencesUtil(Context context, String data_store){
        this.context = context;
        this.Data_Store = data_store;
    }
    //데이터를 내부 저장소에 저장하기
    public void setPreference(String key, String value){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        return pref.getString(key, "");
    }

    //데이터 삭제
    public void deletePreference(String key){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }
}
