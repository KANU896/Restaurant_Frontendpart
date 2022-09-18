// 작성자 : 김도윤, 송민우, 송준곤
// 메인 페이지
// 담당 : 김도윤 - 현재 위치 정보 표시 & 검색 버튼
// Update : 22.08.18

package com.example.myapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.DayRecommend_Adapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.ui.RestourantKeywordActivity;
import com.example.myapplication.util.LocationService;
import com.example.myapplication.util.Location_GPS;
import com.example.myapplication.util.Location_service;

public class HomeFragment extends Fragment implements LocationService {
    private final String TAG = "User_Home_Page";
    private MainActivity main_activity;
    private String address;
    private Location_service locationService;
    private FragmentHomeBinding binding;

    /**
     * MainActivity에 있는 함수를 사용하기 위함
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        main_activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        main_activity = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        //현재 위치 정보 표시
        Location_GPS gps = new Location_GPS(getContext(), getActivity());
        address = gps.get_address(); //이 함수 실행되면서 Permission 검사 동시 실행

        binding.locationText.setText(address);

        locationService = new Location_service(getContext(), this);
        binding.locationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locationService.checkLocationServicesStatus()){
                    binding.locationText.setText(gps.get_address());
                }
                else{
                    locationService.showDialogForLocationServiceSetting();
                }
            }
        });


        //검색 버튼
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_activity.changeFragment();
            }
        });

        // 실시간 맛집 키워드 버튼 클릭시 화면전환
        binding.todayKeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getActivity(), RestourantKeywordActivity.class);

                startActivity(intent);
            }
        });

        /**
         * 일일 추천 ViewPager
         */
        //밥집
        DayRecommend_Adapter dayRecommend_adapter = new DayRecommend_Adapter(getChildFragmentManager(), getLifecycle(), "밥집");
        binding.viewpagerFood.setAdapter(dayRecommend_adapter);

        //카페
        DayRecommend_Adapter dayRecommend_adapter2 = new DayRecommend_Adapter(getChildFragmentManager(), getLifecycle(), "카페");
        binding.viewpagerCafe.setAdapter(dayRecommend_adapter2);

        //술집
        DayRecommend_Adapter dayRecommend_adapter3 = new DayRecommend_Adapter(getChildFragmentManager(), getLifecycle(), "술집");
        binding.viewpagerAlcohol.setAdapter(dayRecommend_adapter3);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;

    @Override
    public void onDialogSetClicked(Intent callGPSSettingIntent) {
        startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
        binding.locationText.setText("위치 갱신");
    }
}

