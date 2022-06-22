package com.example.myapplication.Search_List.search_result_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Search_List.Search_List_Adapter;
import com.example.myapplication.Search_Page.ResponseData;
import com.example.myapplication.R;
import com.example.myapplication.Search_Page.Search_Retrofit.Data;
import com.example.myapplication.Search_Page.Search_Retrofit.RetrofitClient_Search;
import com.example.myapplication.Search_Page.Search_Retrofit.SearchData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Search_List_total extends Fragment {
    private ArrayList<ResponseData> responseData = new ArrayList<>();
    private RecyclerView recyclerView;
    private View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.main_search, container,false);

        responseData = (ArrayList<ResponseData>) getArguments().getSerializable("responseData");

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Search_List_Adapter(responseData));

        return view;
    }
}
