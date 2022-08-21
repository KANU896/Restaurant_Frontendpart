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

public class Japan_Food extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_restourant);
        image = (ImageView)findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.jpn_food);
        // 한식식당이름 리스트
        List<String> food_data = new ArrayList();
        food_data.add("청이해");
        food_data.add("대도일식");
        food_data.add("어가일식");

        // 위치 리스트
        List<String> location_data = new ArrayList();
        location_data.add("충청남도 천안시 서북구 성정2동");
        location_data.add("충청남도 천안시 동남구 원성동 506");
        location_data.add("충청남도 천안시 서북구 두정동 833");

        // 지도 리스트
        List<String> map_data = new ArrayList();
        map_data.add("https://www.google.com/maps/place/%EC%B2%AD%EC%9D%B4%ED%95%B4/data=!4m9!1m2!2m1!1z7LKc7JWIIOydvOyLnQ!3m5!1s0x357b2861fb0f32cf:0x51e629667c290e24!8m2!3d36.8296355!4d127.1424638!15sCg3sspzslYgg7J287IudWg8iDeyynOyViCDsnbzsi52SARJzYXNoaW1pX3Jlc3RhdXJhbnSaASNDaFpEU1VoTk1HOW5TMFZKUTBGblNVTlJPV05ZYlZwbkVBRQ");
        map_data.add("https://www.google.com/maps/place/%EB%8C%80%EB%8F%84%EC%9D%BC%EC%8B%9D/data=!4m9!1m2!2m1!1z7LKc7JWIIOydvOyLnQ!3m5!1s0x357b28484ba8289b:0x88b37687e8632db3!8m2!3d36.8062353!4d127.1603759!15sCg3sspzslYgg7J287IudWg8iDeyynOyViCDsnbzsi52SARNqYXBhbmVzZV9yZXN0YXVyYW50mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2TkY5NWRYTm5SUkFC");
        map_data.add("https://www.google.com/maps/place/%EC%96%B4%EA%B0%80%EC%9D%BC%EC%8B%9D/data=!4m9!1m2!2m1!1z7LKc7JWIIOydvOyLnQ!3m5!1s0x357b287d321ac269:0x35db5718091cd63a!8m2!3d36.8305995!4d127.1362802!15sCg3sspzslYgg7J287IudWg8iDeyynOyViCDsnbzsi52SARNqYXBhbmVzZV9yZXN0YXVyYW50");

        int random_num = (int)(Math.random() * 3);

        TextView jpn_food = findViewById(R.id.rest_name);
        jpn_food.setText(food_data.get(random_num));

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