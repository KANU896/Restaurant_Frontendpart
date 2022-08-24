// 작성자 : 김도윤, 송민우, 송준곤
// 메인 페이지
// 담당 : 김도윤 - 현재 위치 정보 표시 & 검색 버튼
// Update : 22.08.18

package com.example.myapplication.Main_Screen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Common.LocationService;
import com.example.myapplication.Common.Location_GPS;
import com.example.myapplication.Common.Location_service;
import com.example.myapplication.Main_Frame;
import com.example.myapplication.R;

public class User_Home_Page extends Fragment implements LocationService {
    private final String TAG = "User_Home_Page";
    private View view;
    private Button today_key_btn;
    private TextView location_text;
    private Button search_button;
    private Main_Frame main_frame;
    private String address;
    private Location_service locationService;

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
        Location_GPS gps = new Location_GPS(getContext(), getActivity());
        address = gps.get_address(); //이 함수 실행되면서 Permission 검사 동시 실행

        location_text = (TextView) view.findViewById(R.id.location_text);
        location_text.setText(address);

        locationService = new Location_service(getContext(), this);
        location_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locationService.checkLocationServicesStatus()){
                    location_text.setText(gps.get_address());
                }
                else{
                    locationService.showDialogForLocationServiceSetting();
                }
            }
        });


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
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    @Override
    public void onDialogSetClicked(Intent callGPSSettingIntent) {
        startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
        location_text.setText("위치 갱신");
    }
}

