// 작성자 : 김도윤
// 최근 검색어를 띄워주는 클래스
// Update : 22.08.18

package com.example.myapplication.Search_Page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Search_List.Search_result_page;
import com.example.myapplication.databinding.SearchPageBinding;

import java.util.ArrayList;

public class User_Search_Page extends Fragment implements Searched{
    private  Searched_Adapter adapter;
    private SharedPreferencesUtil spref ;
    private ArrayList<SearchedList> searchHistoryList;
    private SearchPageBinding binding;
    private MainActivity main_activity;
    private Toolbar toolbar;

    /**
     * MainActivity에 있는 binding을 사용하기 위함
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        main_activity = (MainActivity) getActivity();
        toolbar = main_activity.binding.toolbar;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        main_activity = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SearchPageBinding.inflate(getLayoutInflater(), container, false);

        setHasOptionsMenu(true); // Fragment에서 onCreateOptionsMenu 사용

        //SharedPreferences에 저장되어 있는 최근 검색어 저장
        spref = new SharedPreferencesUtil(getContext(), "Searched");
        searchHistoryList = (ArrayList<SearchedList>) spref.getSearchHistoryList();

        // 최근 검색어 RecyclerView 설정
        adapter = new Searched_Adapter(getContext(),this);
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

        adapter.setData(searchHistoryList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_list_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.app_bar_search); // SearchView 있는 res/menu/app_bar_search.xml
        SearchView sv = (SearchView) mSearch.getActionView();
        SearchviewOptions(sv);

        //SearchView 검색 시
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) { // 검색 버튼이 눌러졌을 때 이벤트 처리
                searchHistoryList = (ArrayList<SearchedList>) spref.getSearchHistoryList();
                searchHistoryList.add(new SearchedList(query));
                spref.storeSearchHistoryList(searchHistoryList);

                intent(query);
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경되었을 때 이벤트 처리
                return false;
            }
        });
    }

    public void SearchviewOptions(SearchView searchView){
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("검색어를 입력하세요.");
        searchView.setMaxWidth(Integer.MAX_VALUE);
    }

    public void intent (String query){
        Intent intent = new Intent(getContext(), Search_result_page.class);
        intent.putExtra("query", query);
        startActivity(intent);
        getActivity().finish();
    }
}
