package com.example.myapplication.homepage;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;


public class Main_Search extends AppCompatActivity {
    private ArrayList<ResponseData> responseData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

        responseData = (ArrayList<ResponseData>) getIntent().getSerializableExtra("responseData");

        for(ResponseData test : responseData){
            Log.d("Intent Test : ", test.getName());
        }

    }
}
