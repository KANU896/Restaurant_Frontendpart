// 작성자 : 김도윤
// 최근 검색어들을 저장해주는 데이터 클래스
// Update : 22.08.18

package com.example.myapplication.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchedList {
    String name;

    public SearchedList(String query) {
        this.name = query;
    }

    public String getName() {
        return name;
    }

}
