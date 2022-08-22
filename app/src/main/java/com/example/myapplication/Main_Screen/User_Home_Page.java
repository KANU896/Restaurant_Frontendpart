// 작성자 : 김도윤, 송민우, 송준곤
// 메인 페이지
// 담당 : 김도윤 - 현재 위치 정보 표시 & 검색 버튼
// Update : 22.08.18

package com.example.myapplication.Main_Screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Main_Frame;
import com.example.myapplication.Main_Screen.DayRecommend_Data.DR_Data;
import com.example.myapplication.Main_Screen.DayRecommend_Data.DR_Datastore;
import com.example.myapplication.Main_Screen.DayRecommend_Data.DR_ResponseData;
import com.example.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Home_Page extends Fragment  {
    private View view;
    private Button today_key_btn;
    private TextView location_text;
    private Button search_button;
    private Main_Frame main_frame;
    private String address;
    private String location = null;
    private SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(getContext(), "Searched");

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        main_frame = (Main_Frame) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        main_frame = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.home_page, container,false);

        //현재 위치 정보 표시
        location_text = (TextView) view.findViewById(R.id.location_text);
        address = getArguments().getString("address");
        location_text.setText(address);

        //검색 버튼
        search_button = (Button) view.findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_frame.changeFragment();
            }
        });

        // 실시간 맛집 키워드 버튼 클릭시 화면전환
        today_key_btn = (Button) view.findViewById(R.id.today_keword);
        today_key_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getActivity(), Restourant_Keyword.class);

                startActivity(intent);
            }
        });

        //밥집 카테고리 일일 추천 viewpager
        ViewPager2 viewPager2_food = view.findViewById(R.id.viewpager_food);
        DR_Adapter dr_adapter = new DR_Adapter(getChildFragmentManager(), getLifecycle(), "밥집");
        viewPager2_food.setAdapter(dr_adapter);

        //카페 카테고리 일일 추천 viewpager
        ViewPager2 viewPager2_cafe = view.findViewById(R.id.viewpager_cafe);
        DR_Adapter dr_adapter2 = new DR_Adapter(getChildFragmentManager(), getLifecycle(), "카페");
        viewPager2_cafe.setAdapter(dr_adapter2);

        //술집 카테고리 일일 추천 viewpager
        ViewPager2 viewPager2_alcohol = view.findViewById(R.id.viewpager_alcohol);
        DR_Adapter dr_adapter3 = new DR_Adapter(getChildFragmentManager(), getLifecycle(), "술집");
        viewPager2_alcohol.setAdapter(dr_adapter3);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

