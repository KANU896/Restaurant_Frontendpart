// 작성자 : 김도윤
// 상세 페이지에 필요한 데이터를 서버에 요청 후 받은 응답 데이터 관리하는 데이터 클래스
// Update : 22.08.18

package com.example.myapplication.Detail_Page.Detail_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail_ResponseData {
    @SerializedName("_id")
    @Expose
    private String Id;

    @SerializedName("Address")
    @Expose
    private String Address;

    @SerializedName("Image")
    @Expose
    private String Image;

    @SerializedName("Tell_number")
    @Expose
    private String Tell_number;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Score")
    @Expose
    private String Score;

    @SerializedName("Tag")
    @Expose
    private String Tag;

    @SerializedName("fav")
    @Expose
    private boolean fav;

    public String getId() { return Id; }

    public String getImage(){
        return Image;
    }

    public String getScore(){
        return Score;
    }

    public String getName(){
        return Name;
    }

    public String getTell_number(){
        return Tell_number;
    }

    public String getAddress(){
        return Address;
    }

    public String getTag(){
        return Tag;
    }

    public boolean getFav() { return fav; }
}
