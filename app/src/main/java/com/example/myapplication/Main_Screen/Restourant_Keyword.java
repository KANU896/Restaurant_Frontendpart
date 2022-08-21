package com.example.myapplication.Main_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Main_Screen.Detail_Food.Alchol_Food;
import com.example.myapplication.Main_Screen.Detail_Food.Chicken_Food;
import com.example.myapplication.Main_Screen.Detail_Food.Chinese_Food;
import com.example.myapplication.Main_Screen.Detail_Food.Flour_Food;
import com.example.myapplication.Main_Screen.Detail_Food.Japan_Food;
import com.example.myapplication.Main_Screen.Detail_Food.Korean_Food;
import com.example.myapplication.R;

public class Restourant_Keyword  extends AppCompatActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.restourant_keyword);


        Button korean_food = (Button) findViewById(R.id.today_keword2); // 한식 버튼
        //키워드화면 -> 한식화면 전환
        korean_food.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Korean_Food.class);
                startActivity(intent);
            }
        });

        Button china_food = (Button) findViewById(R.id.today_keword3); // 중식 버튼
        china_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chinese_Food.class);
                startActivity(intent);
            }
        });

        Button alchol = (Button) findViewById(R.id.today_keword4); // 술집 버튼
        alchol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Alchol_Food.class);
                startActivity(intent);
            }
        });

        Button japan_food = (Button) findViewById(R.id.today_keword5); // 일식 버튼
        japan_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Japan_Food.class);
                startActivity(intent);
            }
        });

        Button flour_food = (Button) findViewById(R.id.today_keword6); // 분식 버튼
        flour_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Flour_Food.class);
                startActivity(intent);
            }
        });

        Button chicken = (Button) findViewById(R.id.today_keword7); // 치킨 버튼
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chicken_Food.class);
                startActivity(intent);
            }
        });
    }
}



