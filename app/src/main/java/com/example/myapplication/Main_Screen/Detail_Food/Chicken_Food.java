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

public class Chicken_Food extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_restourant);
        image = (ImageView)findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.chick_food);
        // 한식식당이름 리스트
        List<String> food_data = new ArrayList();
        food_data.add("봉명치킨");
        food_data.add("60계치킨");
        food_data.add("처갓집");

        // 위치 리스트
        List<String> location_data = new ArrayList();
        location_data.add("충청남도 천안시 동남구 봉명동 42-34");
        location_data.add("충청남도 천안시 동남구 신부동 455-5");
        location_data.add("충청남도 천안시 서북구 터미널2로 32");

        // 지도 리스트
        List<String> map_data = new ArrayList();
        map_data.add("https://www.google.com/maps/place/%EB%B4%89%EB%AA%85%EC%B9%98%ED%82%A8/data=!4m9!1m2!2m1!1z7LKc7JWIIOy5mO2CqA!3m5!1s0x357b2810fc74fac3:0xfd79268d650f9277!8m2!3d36.8052016!4d127.1396789!15sCg3sspzslYgg7LmY7YKoWg8iDeyynOyViCDsuZjtgqiSARZmcmllZF9jaGlja2VuX3Rha2Vhd2F5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVU54YlhCaVUzaG5SUkFC");
        map_data.add("https://www.google.com/maps/place/60%EA%B3%84%EC%B9%98%ED%82%A8/data=!4m9!1m2!2m1!1z7LKc7JWIIOy5mO2CqA!3m5!1s0x357b285b13be8491:0xab44e432b64b40bd!8m2!3d36.8179831!4d127.1542304!15sCg3sspzslYgg7LmY7YKoWg8iDeyynOyViCDsuZjtgqiSAQxjaGlja2VuX3Nob3CaASRDaGREU1VoTk1HOW5TMFZKUTBGblNVUnBkbTh0U1cxQlJSQUI");
        map_data.add("https://www.google.com/maps/place/%EC%B2%98%EA%B0%93%EC%A7%91%EC%96%91%EB%85%90%EC%B9%98%ED%82%A8+%EC%8B%A0%EB%B6%80%EC%A0%90/data=!4m9!1m2!2m1!1z7LKc7JWIIOy5mO2CqA!3m5!1s0x357b29ceee51c4cd:0x2bfafd75dd104991!8m2!3d36.8216778!4d127.1584077!15sCg3sspzslYgg7LmY7YKoWg8iDeyynOyViCDsuZjtgqiSARZmcmllZF9jaGlja2VuX3Rha2Vhd2F5mgEjQ2haRFNVaE5NRzluUzBWSlEwRm5TVVJOY0RSVVZGWm5FQUU");

        int random_num = (int)(Math.random() * 3);

        TextView chick_food = findViewById(R.id.rest_name);
        chick_food.setText(food_data.get(random_num));

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