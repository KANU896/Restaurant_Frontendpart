// 작성자 : 김도윤
// 검색 결과 페이지에 필요한 응답 데이터를 저장하는 클래스
// Update : 22.08.18

package com.example.myapplication.Search_Page.Search_Retrofit.Search_Data;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private String Id;
    private String Image;
    private String Name;
    private String Score;

    public ResponseData(String id, String Image, String Name, String Score){
        this.Id = id;
        this.Image = Image;
        this.Name = Name;
        this.Score = Score;
    }

    public String getId() {
        return Id;
    }
    public String getImage(){
        return Image;
    }
    public String getName() {
        return Name;
    }
    public String getScore(){
        return Score;
    }
}
