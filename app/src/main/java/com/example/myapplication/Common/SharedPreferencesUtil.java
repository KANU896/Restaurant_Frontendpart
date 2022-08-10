package com.example.myapplication.Common;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myapplication.Search_Page.SearchedList;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        return pref.getString(key, null);
    }

    //데이터 삭제
    public void deletePreference(String key){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

    public void setSearchedData(String value){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        JSONArray jsonArray = getSearchData();

        try{
            jsonArray.put(value);
            editor.putString("searched", jsonArray.toString());
        }
        catch (Exception e){
            JSONArray jsonArray2 = new JSONArray();
            jsonArray2.put(value);
            editor.putString("searched", jsonArray2.toString());
        }
        editor.apply();
        Log.e("after", getPreferenceString("searched"));
    }

    public JSONArray getSearchData(){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        String json = pref.getString("searched", null);
        JSONArray jsonArray = null;
        if(json != null) {
            try {
                jsonArray = new JSONArray(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public void deleteSearchData(String value){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        ArrayList<String> list = new ArrayList<>();
        String json = pref.getString("searched", null);
        JSONArray jsonArray, jsonArray2 = null;
        if(json != null) {
            try {
                jsonArray = new JSONArray(json);
                jsonArray2 = new JSONArray();
                for (int i = 0; i < jsonArray.length(); i++){
                    list.add(jsonArray.optString(i));
                }
                list.remove(value);
                for(int i = 0; i < list.size(); i++) {
                    jsonArray2.put(list.get(i));
                }
                editor.putString("searched", jsonArray2.toString());
                editor.apply();
                Log.e("delete2", getPreferenceString("searched"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void storeSearchHistoryList(List<SearchedList> searchHistoryList){
        String searchHistoryListString = new Gson().toJson(searchHistoryList);
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("key_search_history", searchHistoryListString);
        editor.apply();
    }

    public List<SearchedList> getSearchHistoryList(){
        SharedPreferences pref = context.getSharedPreferences(Data_Store, MODE_PRIVATE);
        String searchHistoryListString = pref.getString("key_search_history", null);

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
            editor.putString("key_search_history", "");
            editor.apply();
            searchHistoryListString = pref.getString("key_search_history", null);
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
