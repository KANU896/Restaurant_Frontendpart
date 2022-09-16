// 작성자 : 김도윤
// 앱에 데이터를 저장시킬 수 있는 기능 모음 클래스
// Update : 22.08.18

package com.example.myapplication.Common;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myapplication.Search_Page.SearchedList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesUtil {
    private Context context;
    private String Data_Store;

    public SharedPreferencesUtil(Context context, String data_store){
        this.context = context;
        this.Data_Store = data_store;
    }
    //데이터를 내부 저장소에 저장하기
    public void setPreference(String key, String value){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!value.isEmpty()) editor.putString(key, value);
        else editor.putString(key, null);
        editor.apply();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        String data;
        Log.e("context", String.valueOf(context));
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        Log.e("getpreference", Data_Store);
        try{
            data = pref.getString(key, null);
        }
        catch (Exception e){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(key, "");
            editor.apply();
            data = pref.getString(key, null);
        }
        return data;
    }

    //데이터 삭제
    public void deletePreference(String key){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }


    private String SEARCHHISTORYLIST_KEY = "key_search_history";
    // 검색 기록 저장
    public void storeSearchHistoryList(List<SearchedList> searchHistoryList){
        String searchHistoryListString = new Gson().toJson(searchHistoryList);
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(SEARCHHISTORYLIST_KEY, searchHistoryListString);
        editor.apply();
    }

    //검색 기록 불러오기
    public List<SearchedList> getSearchHistoryList(){
        Log.e("GSH", String.valueOf(context));
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        String searchHistoryListString = pref.getString(SEARCHHISTORYLIST_KEY, null);

        ArrayList<SearchedList> storedSearchHistoryList = new ArrayList<SearchedList>();
        try {
            if (!searchHistoryListString.isEmpty()) {
                Type listType = new TypeToken<ArrayList<SearchedList>>() {
                }.getType();
                storedSearchHistoryList = new Gson().fromJson(searchHistoryListString, listType);
            }
        }
        catch (Exception e){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(SEARCHHISTORYLIST_KEY, "");
            editor.apply();
            searchHistoryListString = pref.getString(SEARCHHISTORYLIST_KEY, null);
            if (!searchHistoryListString.isEmpty()) {
                Type listType = new TypeToken<ArrayList<SearchedList>>() {
                }.getType();
                storedSearchHistoryList = new Gson().fromJson(searchHistoryListString, listType);
            }
        }

        return storedSearchHistoryList;
    }

    //검색 기록 전체 삭제
    public void deleteSearchHistoryList(){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }

}
