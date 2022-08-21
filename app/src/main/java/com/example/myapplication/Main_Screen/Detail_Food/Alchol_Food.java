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

public class Alchol_Food extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_restourant);
        image = (ImageView)findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.alchol_food);
        // 한식식당이름 리스트
        List<String> food_data = new ArrayList();
        food_data.add("오술차");
        food_data.add("1943");
        food_data.add("탐라포차");

        // 위치 리스트
        List<String> location_data = new ArrayList();
        location_data.add("충청남도 천안시 서북구 두정동 832-1");
        location_data.add("충청남도 천안시 서북구 원두정2길 22 2층");
        location_data.add("충청남도 천안시 서북구 원두정8길 20 2층");

        // 지도 리스트
        List<String> map_data = new ArrayList();
        map_data.add("https://www.google.com/maps/place/%EC%98%A4%EC%88%A0%EC%B0%A8/data=!4m9!1m2!2m1!1z7LKc7JWIIOyIoOynkQ!3m5!1s0x357b287d2fc75a03:0xda0826e14ed8535b!8m2!3d36.8305549!4d127.1360177!15sCg3sspzslYgg7Iig7KeRWg8iDeyynOyViCDsiKDsp5GSAQNiYXKaASRDaGREU1VoTk1HOW5TMFZKUTBGblNVTlhkSFZxUkhsQlJSQUI");
        map_data.add("https://www.google.com/maps/place/1943+%EC%B2%9C%EC%95%88%EC%A0%90/data=!4m9!1m2!2m1!1z7LKc7JWIIOyIoOynkQ!3m5!1s0x357b29d271502d71:0x2e43a1850c5e61a0!8m2!3d36.8321558!4d127.1358372!15sCg3sspzslYgg7Iig7KeRWg8iDeyynOyViCDsiKDsp5GSAQNiYXI");
        map_data.add("https://www.google.com/maps/place/%ED%83%90%EB%9D%BC%ED%8F%AC%EC%B0%A8+%EC%B2%9C%EC%95%88%EB%91%90%EC%A0%95%EC%A0%90/data=!4m9!1m2!2m1!1z7LKc7JWIIOyIoOynkQ!3m5!1s0x357b296e952d790f:0xaea13134efc7cb4e!8m2!3d36.8323919!4d127.1354612!15sCg3sspzslYgg7Iig7KeRWg8iDeyynOyViCDsiKDsp5GSAQNiYXI");

        int random_num = (int)(Math.random() * 3);

        TextView alchol_food = findViewById(R.id.rest_name);
        alchol_food.setText(food_data.get(random_num));

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