package com.example.myapplication.Main_Screen;

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
import com.example.myapplication.Main_Screen.Search_Retrofit.Data;
import com.example.myapplication.Main_Screen.Search_Retrofit.RetrofitClient_Search;
import com.example.myapplication.Main_Screen.Search_Retrofit.SearchData;
import com.example.myapplication.Search_List.Search_List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Home_Page extends Fragment  {
    private View view;
    private Button today_key_btn;
    private Button today_rest_btn;
    private SearchView main_search;
    //private MutableLiveData<ArrayList<ResponseData>> dieseldata;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.home_page, container,false);


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
                        //dieseldata = new MutableLiveData<>();
                        ArrayList<ResponseData> responseData = new ArrayList<>();
                        SearchData searchdata = response.body();
                        Data[] data = searchdata != null
                                ? searchdata.getData()
                                :new Data[0];
                        // 데이터 저장 ArrayList<ResponseData>
                        for (int i = 0; i < data.length; i++){
                            String id = data[i].get_id();
                            String Image = data[i].getImage();
                            String Name = data[i].getName();
                            String Score = data[i].getScore();

                            responseData.add(new ResponseData(
                                    id,
                                    Image,
                                    Name,
                                    Score
                            ));
                        }

                        //dieseldata.setValue(responseData); // MutableLiveData에 저장한 데이터 ArrayList<ResponseData> 저장

                        // 데이터 확인
                        for (ResponseData responseData1 : responseData)
                            Log.d("live 데이터 : ", responseData1.getName());

                        Intent intent = new Intent(getActivity(), Search_List.class);
                        intent.putExtra("responseData", responseData);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SearchData> call, Throwable t) {
                        Toast.makeText(getActivity(),"서버 연결 실패",Toast.LENGTH_SHORT).show();
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

