package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Data.StoreResponseData;
import com.example.myapplication.databinding.ActivityRestourantKeywordBinding;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.SharedPreferencesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestourantKeywordActivity extends AppCompatActivity {
    private SharedPreferencesUtil spref;
    private String location;
    private ActivityRestourantKeywordBinding binding;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestourantKeywordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.restourant_keyword);

        spref = new SharedPreferencesUtil(getApplicationContext(), "Searched"); // 위치 정보

        location = spref.getPreferenceString("location");

//        if (!TextUtils.isEmpty(location) && !location.equals("")) {
//            String remove_text = "대한민국 ";
//            location = location.replace(remove_text,"");
//        }
        if (location.equals("위치 정보 없음") || TextUtils.isEmpty(location)) location = null;



        //Button food = findViewById(R.id.keyword_food); // 밥집 버튼
        binding.keywordFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<StoreResponseData> call = RetrofitClient.getApiService().Detail_random(location, "밥집");
                retrofit(call);
            }
        });

        //Button alchol = findViewById(R.id.keyword_alcohol); // 술집 버튼
        binding.keywordAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<StoreResponseData> call = RetrofitClient.getApiService().Detail_random(location, "술집");
                retrofit(call);
            }
        });

        //Button cafe = findViewById(R.id.keyword_cafe); // 카페 버튼
        binding.keywordCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<StoreResponseData> call = RetrofitClient.getApiService().Detail_random(location, "카페");
                retrofit(call);
            }
        });
    }

    private void retrofit(Call<StoreResponseData> call){
        call.enqueue((new Callback<StoreResponseData>() {
            @Override
            public void onResponse(Call<StoreResponseData> call, Response<StoreResponseData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    Toast.makeText(getApplicationContext(), "위치 서비스가 필요합니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                StoreResponseData detail_data = response.body();

                if(TextUtils.isEmpty(detail_data.getId())){
                    Log.e("데이터", "없음");
                    Toast.makeText(getApplicationContext(), "해당 지역의 음식점 데이터가 없습니다.", Toast.LENGTH_LONG).show();
                    return;
                }

//                String id = detail_data.getId();
//                String City = detail_data.getCity();
//                String Address = detail_data.getAddress();
//                String Name = detail_data.getName();
//                String Tell = detail_data.getTell_number();
//                String Score = detail_data.getScore();
//                String Category = detail_data.getCategory();
//                String Tag = detail_data.getTag();
//                Boolean fav = detail_data.getFav();
//
//                TestResponseData response_detail_data = new TestResponseData(
//                        id,
//                        City,
//                        Address,
//                        Name,
//                        Tell,
//                        Score,
//                        Category,
//                        Tag,
//                        fav
//                );

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("responseData", detail_data);
                startActivity(intent);
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<StoreResponseData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(getApplicationContext(),"연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
