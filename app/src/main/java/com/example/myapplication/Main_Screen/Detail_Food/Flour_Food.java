package com.example.myapplication.Main_Screen.Detail_Food;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Flour_Food extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_restourant);
        image = (ImageView)findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.flour_food);
        // 한식식당이름 리스트
        List<String> food_data = new ArrayList();
        food_data.add("중앙분식");
        food_data.add("분식쌀롱");
        food_data.add("분식이이래도되는가");

        // 위치 리스트
        List<String> location_data = new ArrayList();
        location_data.add("충청남도 천안시 동남구 원성동 462-8");
        location_data.add("충청남도 천안시 동남구 봉명동 60-35");
        location_data.add("충청남도 천안시 서북구 두정동 905");

        // 지도 리스트
        List<String> map_data = new ArrayList();
        map_data.add("https://www.google.com/maps/place/%EC%A4%91%EC%95%99%EB%B6%84%EC%8B%9D/data=!4m9!1m2!2m1!1z7LKc7JWIIOu2hOyLnQ!3m5!1s0x357b284f67f7c309:0xc650b6ff9e23bd84!8m2!3d36.8113548!4d127.1586395!15sCg3sspzslYgg67aE7IudWg8iDeyynOyViCDrtoTsi52SAQpyZXN0YXVyYW50");
        map_data.add("https://www.google.com/maps/place/%EB%B6%84%EC%8B%9D%EC%8C%80%EB%A1%B1+%EC%B2%9C%EC%95%88%EB%B4%89%EB%AA%85%EC%A0%90/data=!4m9!1m2!2m1!1z7LKc7JWIIOu2hOyLnQ!3m5!1s0x357b293abeafd7a3:0xa72bb1875c3bf715!8m2!3d36.8070874!4d127.1380562!15sCg3sspzslYgg67aE7IudWg8iDeyynOyViCDrtoTsi52SARB1ZG9uX25vb2RsZV9zaG9w");
        map_data.add("https://www.google.com/maps/place/%EB%B6%84%EC%8B%9D%EC%9D%B4%EC%9D%B4%EB%9E%98%EB%8F%84%EB%90%98%EB%8A%94%EA%B0%80+%EB%91%90%EC%A0%95%EC%A0%90/data=!4m9!1m2!2m1!1z7LKc7JWIIOu2hOyLnQ!3m5!1s0x357b2902b0266559:0xd543a9fcdde5682!8m2!3d36.8337797!4d127.1345047!15sCg3sspzslYgg67aE7IudWg8iDeyynOyViCDrtoTsi52SAQpyZXN0YXVyYW50");

        int random_num = (int)(Math.random() * 3);

        TextView flour_food = findViewById(R.id.rest_name);
        flour_food.setText(food_data.get(random_num));

        TextView loc_name = findViewById(R.id.loaction_name);
        loc_name.setText(location_data.get(random_num));


        Button map = (Button) findViewById(R.id.google_map); // 지도이동 버튼
        //한식화면 -> 지도화면 전환 (지도검색)
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map_data.get(random_num)));
                startActivity(intent);
            }
        });

    }
}