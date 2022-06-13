package com.example.myapplication.Search_List;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Search_Page.ResponseData;
import com.example.myapplication.R;

import java.util.ArrayList;


public class Search_List extends AppCompatActivity {
    private ArrayList<ResponseData> responseData;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

        responseData = (ArrayList<ResponseData>) getIntent().getSerializableExtra("responseData");

        for(ResponseData test : responseData){
            Log.d("Intent Test : ", test.getName());
        }

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Search_List_Adapter(responseData));
    }
}
