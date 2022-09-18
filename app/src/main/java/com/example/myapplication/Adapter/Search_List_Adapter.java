// 작성자 : 김도윤
// 검색 결과 또는 즐겨찾기 목록 등 RecyclerView에 데이터 넣고 띄워주는 Adapter
// Update : 22.08.18

package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.Data.StoreResponseData;
import com.example.myapplication.databinding.SearchResultRecyclerviewItemBinding;
import com.example.myapplication.ui.DetailActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_List_Adapter extends RecyclerView.Adapter<Search_List_Adapter.MyViewHolder>{
    private Context mContext;
    private String token;
    private SearchResultRecyclerviewItemBinding binding;
    private ArrayList<StoreResponseData> responseData;

    /**
     * Test
     */
    public Search_List_Adapter(Context mContext, ArrayList<StoreResponseData> responseData){
        this.responseData = responseData;
        this.mContext = mContext;
    }


    /**
     * Test
     */
    public void setData(ArrayList<StoreResponseData> responseData){
        this.responseData = responseData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        MyViewHolder(SearchResultRecyclerviewItemBinding view){
            super(view.getRoot());

            //음식점 클릭 시
            view.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    StoreResponseData clickdata = responseData.get(pos);
//
//                    if (!TextUtils.isEmpty(token)) {
//                        try {
//                            retrofit(v, test.getId());
//                        } catch (Exception e) {
//                            Log.e("JSON Decode Error", e.getMessage());
//                        }
//                    }
//                    else
                    retrofit(v, clickdata.getId());
                }
            });
        }
    }

    @NonNull
    @Override
    public Search_List_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_recyclerview_item,parent,false);
        binding = SearchResultRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Search_List_Adapter.MyViewHolder holder, int position) {
        //ResponseData text = responseData.get(position);
        StoreResponseData text = responseData.get(position);

        binding.title.setText(text.getName());
        if (text.getScore().equals(""))
            binding.reviewScore.setText("점수 없음");
        else
            binding.reviewScore.setText(text.getScore());
    }

    @Override
    public int getItemCount() {
        if (responseData != null)
            return responseData.size();
        else
            return 0;
    }


    public void retrofit (View v, String id){
        Call<StoreResponseData> call = RetrofitClient.getApiService().Detail_post(id);
        call.enqueue((new Callback<StoreResponseData>() {
            @Override
            public void onResponse(Call<StoreResponseData> call, Response<StoreResponseData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                StoreResponseData detail_data = response.body();

                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("responseData", detail_data);
                v.getContext().startActivity(intent);
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<StoreResponseData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(mContext,"연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
