// 작성자 : 김도윤
// 검색 결과 페이지 -> 전체 Tab 선택 페이지 (Default)
// Update : 22.08.18

package com.example.myapplication.Fragment.SearchResult;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Adapter.Search_List_Adapter;
import com.example.myapplication.Data.Search.ResponseData;
import com.example.myapplication.databinding.TotalFragmentBinding;

import java.util.ArrayList;


public class Total extends Fragment {
    private ArrayList<ResponseData> responseData = new ArrayList<>();
//    private RecyclerView recyclerView;
//    private View view;
    private Context context;
    private TotalFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = TotalFragmentBinding.inflate(getLayoutInflater(), container, false);
//        view = inflater.inflate(R.layout.total_fragment, container,false);

        context = container.getContext();
        responseData = (ArrayList<ResponseData>) getArguments().getSerializable("responseData");

//        recyclerView = view.findViewById(R.id.recyclerview);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerview.setAdapter(new Search_List_Adapter(context, responseData));

        return binding.getRoot();
    }
}
