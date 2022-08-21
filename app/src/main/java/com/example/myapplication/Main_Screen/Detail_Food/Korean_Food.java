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

public class Korean_Food extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_restourant);
        image = (ImageView)findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.kor_food);
    // 한식식당이름 리스트
        List<String> food_data = new ArrayList();
        food_data.add("바른식당");
        food_data.add("가산");
        food_data.add("송연");

        // 위치 리스트
        List<String> location_data = new ArrayList();
        location_data.add("충청남도 천안시 동남구 안서동 266-4");
        location_data.add("충청남도 천안시 동남구 유량동 태조산길 179-17");
        location_data.add("충청남도 천안시 동남구 유량동 157");

    // 지도 리스트
        List<String> map_data = new ArrayList();
        map_data.add("https://www.google.com/maps/place/%EB%B0%94%EB%A5%B8%EC%8B%9D%EB%8B%B9/data=!4m5!3m4!1s0x0:0xe119b7bf9394708c!8m2!3d36.8288623!4d127.1829037");
        map_data.add("https://www.google.com/maps/search/%EC%B2%9C%EC%95%88+%EA%B0%80%EC%82%B0");
        map_data.add("https://www.google.com/maps/place/%EC%86%A1%EC%97%B0/data=!3m1!4b1!4m5!3m4!1s0x357b290a17230cb5:0xe5cf6a5756b89861!8m2!3d36.8152778!4d127.1855556");

        int random_num = (int)(Math.random() * 3);

        TextView kor_food = findViewById(R.id.rest_name);
        kor_food.setText(food_data.get(random_num));

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