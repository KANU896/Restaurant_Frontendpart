package com.example.myapplication.Search_List.search_result_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Search_List.Search_List_Adapter;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.ResponseData;
import com.example.myapplication.R;

import java.util.ArrayList;


public class Search_List_total extends Fragment {
    private ArrayList<ResponseData> responseData = new ArrayList<>();
    private RecyclerView recyclerView;
    private View view;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.main_search, container,false);

        context = container.getContext();
        responseData = (ArrayList<ResponseData>) getArguments().getSerializable("responseData");

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Search_List_Adapter(context, responseData));

        //recyclerView.

//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView,
//                new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        // event code
//                        Toast.makeText(context, "Test Toast", Toast.LENGTH_SHORT).show();
//                    }
//                }));

        return view;
    }
}