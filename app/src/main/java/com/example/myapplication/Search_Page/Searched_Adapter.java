// 작성자 : 김도윤
// 최근 검색어를 RecyclerView에 데이터 넣고 띄워주는 Adapter
// Update : 22.08.18

package com.example.myapplication.Search_Page;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Search_List.Search_result_page;
import com.example.myapplication.databinding.SearchedRecyclerviewItemBinding;

import java.util.ArrayList;

public class Searched_Adapter extends RecyclerView.Adapter<Searched_Adapter.MyViewHolder>{
    private ArrayList<SearchedList> searched;
    private Context mContext;
    private Searched I_searched = null;
    private SearchedRecyclerviewItemBinding binding;

    public Searched_Adapter(Context mContext, Searched Interface){
        this.mContext = mContext;
        this.I_searched = Interface;
    }

    public void setData(ArrayList<SearchedList> searched){
        this.searched = searched;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        MyViewHolder(SearchedRecyclerviewItemBinding view){
            super(view.getRoot());

            // x 버튼 클릭 시
            binding.reviewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Log.e("click x", String.valueOf(pos));
                    I_searched.onSearchItemDeleteClicked(pos);
                }
            });

            //검색 내역 클릭 시
            view.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(mContext, Search_result_page.class);
                    intent.putExtra("query", searched.get(pos).name);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public Searched_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SearchedRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Searched_Adapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Searched_Adapter.MyViewHolder holder, int position) {
        String name = searched.get(position).name;
        binding.searchedTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        if (searched != null)
            return searched.size();
        else
            return 0;
    }
}
