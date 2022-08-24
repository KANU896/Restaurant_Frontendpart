// 작성자 : 김도윤
// SearchData 클래스에서 받은 응답 데이터는 배열로 구성되어 있어 추가로 구성한 데이터 클래스
// Update : 22.08.18

package com.example.myapplication.Search_Page.Search_Retrofit.Search_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("Image")
    @Expose
    private String Image;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Score")
    @Expose
    private String Score;

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage() {
        return Image;
    }
    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getScore() {
        return Score;
    }
    public void setScore(String Score) {
        this.Score = Score;
    }
}
