package com.example.myapplication.Search_Page;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class User_Search_Page extends Fragment implements Searched{
    private View view;
    private RecyclerView recyclerView;
    private Context context;
    private  Searched_Adapter adapter;
    private SharedPreferencesUtil spref;
    private ArrayList<SearchedList> searchHistoryList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.search_page, container,false);

        context = container.getContext();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        spref = new SharedPreferencesUtil(context, "Searched");

        Button delete_bt = view.findViewById(R.id.all_delete_button);

        searchHistoryList = (ArrayList<SearchedList>) spref.getSearchHistoryList();

        adapter = new Searched_Adapter(context,this);
        recyclerView = view.findViewById(R.id.searched_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setData(searchHistoryList);// searchHistoryList

        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spref.deleteSearchHistoryList();
                searchHistoryList = (ArrayList<SearchedList>) spref.getSearchHistoryList();
                adapter.setData(searchHistoryList);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSearchItemDeleteClicked(int position) {
        Log.e("User_Search_Page", "onSearchItemDeleteClick");
        searchHistoryList.remove(position);
        spref.storeSearchHistoryList(searchHistoryList);
        adapter.notifyDataSetChanged();
    }
}
