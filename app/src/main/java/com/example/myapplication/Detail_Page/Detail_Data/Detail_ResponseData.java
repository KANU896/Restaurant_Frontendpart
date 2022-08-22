// 작성자 : 김도윤
// 상세 페이지에 필요한 응답 데이터를 저장하는 클래스
// Update : 22.08.18

package com.example.myapplication.Detail_Page.Detail_Data;

import java.io.Serializable;

public class Detail_ResponseData implements Serializable {

    private String Id;
    private String Image;
    private String Name;
    private String Score;
    private String Address;
    private String Tag;
    private String Tell_number;
    private boolean Fav;

    public Detail_ResponseData(String id, String Image, String Name, String Score, String Address, String Tag, String Tell_number, boolean Fav){
        this.Id = id;
        this.Image = Image;
        this.Name = Name;
        this.Score = Score;
        this.Address = Address;
        this.Tag = Tag;
        this.Tell_number = Tell_number;
        this.Fav = Fav;
    }
    public  String getId() { return Id; }
    public String getImage(){
        return Image;
    }
    public String getName() {
        return Name;
    }
    public String getScore(){
        return Score;
    }
    public String getAddress(){
        return Address;
    }
    public String getTag() {
        return Tag;
    }
    public String getTell_number(){
        return Tell_number;
    }
    public boolean getFav() { return Fav; }
}
