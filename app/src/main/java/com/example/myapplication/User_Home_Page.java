package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class User_Home_Page extends Fragment {

    private View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.home_page, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    protected void onCreateView(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button today_keyword = (Button) view.findViewById(R.id.today_keword); // 실시간 맛집 키워드 버튼
        Button today_restourant = (Button) view.findViewById(R.id.today_restourant); // 오늘의 맛집 버튼

        // 메인화면 -> 실시간 맛집 키워드 화면 전환
        today_keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Restourant_Keyword.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        // 메인화면 -> 오늘의 맛집 화면 전환
        today_restourant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Today_Restourant.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

    private void setContentView(int home_page) {
        Button today_keyword = (Button) view.findViewById(R.id.today_keword); // 실시간 맛집 키워드 버튼
        Button today_restourant = (Button) view.findViewById(R.id.today_restourant); // 오늘의 맛집 버튼

        // 메인화면 -> 실시간 맛집 키워드 화면 전환
        today_keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Restourant_Keyword.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        // 메인화면 -> 오늘의 맛집 화면 전환
        today_restourant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Today_Restourant.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }
}

