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

public class Chinese_Food extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_restourant);
        image = (ImageView)findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.chn_food);
        // 한식식당이름 리스트
        List<String> food_data = new ArrayList();
        food_data.add("슈엔");
        food_data.add("메이탄");
        food_data.add("이화원");

        // 위치 리스트
        List<String> location_data = new ArrayList();
        location_data.add("충청남도 천안시 서북구 성정2동 성정중1길 26-2");
        location_data.add("충청남도 천안시 서북구 다가말3길 2");
        location_data.add("충청남도 천안시 서북구 불당동 23 로 73-27");

        // 지도 리스트
        List<String> map_data = new ArrayList();
        map_data.add("https://www.google.com/maps/place/%EC%8A%88%EC%97%94/data=!4m9!1m2!2m1!1z7LKc7JWIIOykkeyLnQ!3m5!1s0x357b287c8976a629:0xd8ae09806beccdc7!8m2!3d36.8293473!4d127.1341191!15sCg3sspzslYgg7KSR7IudWg8iDeyynOyViCDspJHsi52SARJjaGluZXNlX3Jlc3RhdXJhbnSaASNDaFpEU1VoTk1HOW5TMFZKUTBGblNVUnpNRTVxZWxwQkVBRQ");
        map_data.add("https://www.google.com/maps/place/%EB%A9%94%EC%9D%B4%ED%83%84/data=!4m9!1m2!2m1!1z7LKc7JWIIOykkeyLnQ!3m5!1s0x357b2801f85355a1:0xe4475f030cea3680!8m2!3d36.797366!4d127.1310591!15sCg3sspzslYgg7KSR7IudWg8iDeyynOyViCDspJHsi52SARJjaGluZXNlX3Jlc3RhdXJhbnSaASNDaFpEU1VoTk1HOW5TMFZKUTBGblNVUkhNbkpMVm1OUkVBRQ");
        map_data.add("https://www.google.com/maps/place/%EC%9D%B4%ED%99%94%EC%9B%90/data=!4m9!1m2!2m1!1z7LKc7JWIIOykkeyLnQ!3m5!1s0x357b2741f10fb767:0xa9180c668ecc5742!8m2!3d36.8199239!4d127.1098072!15sCg3sspzslYgg7KSR7IudWg8iDeyynOyViCDspJHsi52SARJjaGluZXNlX3Jlc3RhdXJhbnSaASRDaGREU1VoTk1HOW5TMFZKUTBGblNVTkhORGxsYkd0blJSQUI");

        int random_num = (int)(Math.random() * 3);

        TextView chn_food = findViewById(R.id.rest_name);
        chn_food.setText(food_data.get(random_num));

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