package com.example.myapplication.Main_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_ResponseData;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_Datastore;
import com.example.myapplication.Detail_Page.Detail_page;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Restourant_Keyword  extends AppCompatActivity {
    private SharedPreferencesUtil spref, spref2;
    private String location, token;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.restourant_keyword);

        spref = new SharedPreferencesUtil(getApplicationContext(), "Searched"); // 위치 정보

        location = spref.getPreferenceString("location");

//        if (!TextUtils.isEmpty(location) && !location.equals("")) {
//            String remove_text = "대한민국 ";
//            location = location.replace(remove_text,"");
//        }
        if (location.equals("위치 정보 없음") || TextUtils.isEmpty(location)) location = null;



        Button food = findViewById(R.id.keyword_food); // 밥집 버튼
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Detail_ResponseData> call = RetrofitClient.getApiService().Detail_random(location, "밥집");
                retrofit(call);
            }
        });

        Button alchol = findViewById(R.id.keyword_alcohol); // 술집 버튼
        alchol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Detail_ResponseData> call = RetrofitClient.getApiService().Detail_random(location, "술집");
                retrofit(call);
            }
        });

        Button cafe = findViewById(R.id.keyword_cafe); // 카페 버튼
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Detail_ResponseData> call = RetrofitClient.getApiService().Detail_random(location, "카페");
                retrofit(call);
            }
        });
    }

    private void retrofit(Call<Detail_ResponseData> call){
        call.enqueue((new Callback<Detail_ResponseData>() {
            @Override
            public void onResponse(Call<Detail_ResponseData> call, Response<Detail_ResponseData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    Toast.makeText(getApplicationContext(), "위치 서비스가 필요합니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                Detail_ResponseData detail_data = response.body();

                if(TextUtils.isEmpty(detail_data.getId())){
                    Log.e("데이터", "없음");
                    Toast.makeText(getApplicationContext(), "해당 지역의 음식점 데이터가 없습니다.", Toast.LENGTH_LONG).show();
                    return;
                }

                String Id = detail_data.getId();
                String Image = detail_data.getImage();
                String Name = detail_data.getName();
                String Score = detail_data.getScore();
                String Address = detail_data.getAddress();
                String Tag = detail_data.getTag();
                String Tell_number = detail_data.getTell_number();
                boolean Fav = detail_data.getFav();

                Detail_Datastore response_detail_data = new Detail_Datastore(
                        Id,
                        Image,
                        Name,
                        Score,
                        Address,
                        Tag,
                        Tell_number,
                        Fav
                );

                Intent intent = new Intent(getApplicationContext(), Detail_page.class);
                intent.putExtra("responseData", response_detail_data);
                startActivity(intent);
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<Detail_ResponseData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(getApplicationContext(),"연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
