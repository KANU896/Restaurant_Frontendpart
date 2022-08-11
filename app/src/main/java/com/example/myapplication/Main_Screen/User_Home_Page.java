package com.example.myapplication.Main_Screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Common.Location_GPS;
import com.example.myapplication.Main_Frame;
import com.example.myapplication.R;

public class User_Home_Page extends Fragment  {
    private View view;
    private Button today_key_btn;
    private Button today_rest_btn;
    private TextView location_text;
    private Button search_button;
    private Main_Frame main_frame;
    private String address;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

