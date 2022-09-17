// 작성자 : 김도윤
// 최근 검색어를 RecyclerView에 데이터 넣고 띄워주는 Adapter
// Update : 22.08.18

package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.SearchResultActivity;
import com.example.myapplication.Interface.onSearchedItemDelete;
import com.example.myapplication.Data.SearchedList;
import com.example.myapplication.databinding.SearchedRecyclerviewItemBinding;

import java.util.ArrayList;

public class Searched_Adapter extends RecyclerView.Adapter<Searched_Adapter.MyViewHolder>{
    private ArrayList<SearchedList> searched;
    private Context mContext;
    private onSearchedItemDelete i_onSearchedItemDelete = null;
    private SearchedRecyclerviewItemBinding binding;

    public Searched_Adapter(Context mContext, onSearchedItemDelete Interface){
        this.mContext = mContext;
        this.i_onSearchedItemDelete = Interface;
    }

    public void setData(ArrayList<SearchedList> searched){
        if (this.searched != null) this.searched.clear();
        this.searched = searched;
        //this.searched = searched;
        //notifyDataSetChanged();
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
                    i_onSearchedItemDelete.onSearchedItemDelete(pos);
                }
            });

            //검색 내역 클릭 시
            view.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(mContext, SearchResultActivity.class);
                    intent.putExtra("query", searched.get(pos).getName());
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
        String name = searched.get(position).getName();
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
