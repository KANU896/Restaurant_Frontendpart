package com.example.myapplication.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.homepage.Search.Data;
import com.example.myapplication.homepage.Search.RetrofitClient_Search;
import com.example.myapplication.homepage.Search.SearchData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Home_Page extends Fragment  {
    private View view;
    private Button today_key_btn;
    private Button today_rest_btn;
    private SearchView main_search;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.home_page,container,false);


        // 실시간 맛집 키워드 버튼 클릭시 화면전환
        today_key_btn = (Button) view.findViewById(R.id.today_keword);
        today_key_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getActivity(), Restourant_Keyword.class);

                startActivity(intent);
            }
        });

        //오늘의 맛집 버튼 클릭 시 화면전환
        today_rest_btn = (Button) view.findViewById(R.id.today_restourant);
        today_rest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Today_Restourant.class);
                startActivity(intent);
            }
        });

        main_search = (SearchView) view.findViewById(R.id.restour_search);
        main_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                // 검색 버튼이 눌러졌을 때 이벤트 처리

                Call<SearchData> call = RetrofitClient_Search.getApiService().postOverlapCheck(query);
                call.enqueue((new Callback<SearchData>() {
                    @Override
                    public void onResponse(Call<SearchData> call, Response<SearchData> response) {
                        if(!response.isSuccessful()){
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                            return;
                        }
                        //TODO 응답 받은 데이터 처리
                        SearchData searchdata = response.body();
                        Data[] data = searchdata != null
                                ? searchdata.getData()
                                : new Data[0];

                        Log.d("연결이 성공적 : ", String.valueOf(data));
                        // 여기까지
                        Intent intent = new Intent(getActivity(), Main_Search.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SearchData> call, Throwable t) {
                        Log.e("연결실패", t.getMessage());
                    }
                }));
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경되었을 때 이벤트 처리
                return false;
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

