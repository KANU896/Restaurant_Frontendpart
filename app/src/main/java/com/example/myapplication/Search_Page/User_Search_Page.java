// 작성자 : 김도윤
// 최근 검색어를 띄워주는 클래스
// Update : 22.08.18

package com.example.myapplication.Search_Page;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.R;
import com.example.myapplication.databinding.SearchPageBinding;

import java.util.ArrayList;

public class User_Search_Page extends Fragment implements Searched{
    private Context context;
    private  Searched_Adapter adapter;
    private SharedPreferencesUtil spref;
    private ArrayList<SearchedList> searchHistoryList;
    private SearchPageBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = SearchPageBinding.inflate(getLayoutInflater(), container, false);
        context = container.getContext();

        //SharedPreferences에 저장되어 있는 최근 검색어 저장
        spref = new SharedPreferencesUtil(context, "Searched");
        searchHistoryList = (ArrayList<SearchedList>) spref.getSearchHistoryList();

        // 최근 검색어 RecyclerView 설정
        adapter = new Searched_Adapter(context,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.searchedRecycle.setLayoutManager(linearLayoutManager);
        binding.searchedRecycle.setAdapter(adapter);

        adapter.setData(searchHistoryList);// searchHistoryList

        // 최근 검색어 전체 삭제
        binding.allDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spref.deleteSearchHistoryList();
                searchHistoryList = (ArrayList<SearchedList>) spref.getSearchHistoryList();
                adapter.setData(searchHistoryList);
                adapter.notifyDataSetChanged();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // Adapter에서 최근 검색어 삭제 버튼 클릭 시
    @Override
    public void onSearchItemDeleteClicked(int position) {
        Log.e("User_Search_Page", "onSearchItemDeleteClick");
        searchHistoryList.remove(position);
        spref.storeSearchHistoryList(searchHistoryList);
        adapter.notifyDataSetChanged();
    }
}
