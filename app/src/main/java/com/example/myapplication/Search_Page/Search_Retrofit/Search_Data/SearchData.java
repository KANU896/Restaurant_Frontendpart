// 작성자 : 김도윤
// 검색어에 해당하는 음식점 데이터를 서버에 요청 후 받은 응답 데이터 관리하는 데이터 클래스
// Update : 22.08.18

package com.example.myapplication.Search_Page.Search_Retrofit.Search_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchData {
    @SerializedName("data")
    @Expose
    private Data[] data;

    public Data[] getData () {
        return data;
    }
}
