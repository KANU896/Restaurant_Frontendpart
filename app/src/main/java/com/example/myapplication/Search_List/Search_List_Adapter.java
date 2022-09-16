// 작성자 : 김도윤
// 검색 결과 또는 즐겨찾기 목록 등 RecyclerView에 데이터 넣고 띄워주는 Adapter
// Update : 22.08.18

package com.example.myapplication.Search_List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.ImageLoadTask;
import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_ResponseData;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_Datastore;
import com.example.myapplication.Detail_Page.Detail_page;
import com.example.myapplication.R;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.ResponseData;
import com.example.myapplication.databinding.SearchResultRecyclerviewItemBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_List_Adapter extends RecyclerView.Adapter<Search_List_Adapter.MyViewHolder>{
    private ArrayList<ResponseData> responseData;
    private Context mContext;
    private String token;
    private SearchResultRecyclerviewItemBinding binding;

    public Search_List_Adapter(Context mContext, ArrayList<ResponseData> responseData){
        this.responseData = responseData;
        this.mContext = mContext;
    }

    public void setData(ArrayList<ResponseData> responseData){
        this.responseData = responseData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
//        ImageView image;
//        TextView title;
//        TextView score;

        MyViewHolder(SearchResultRecyclerviewItemBinding view){
            super(view.getRoot());
//            image = (ImageView)view.findViewById(R.id.restaurant_image);
//            title = (TextView)view.findViewById(R.id.title);
//            score = (TextView)view.findViewById(R.id.review_score);

            //음식점 클릭 시
            view.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    ResponseData test = responseData.get(pos);
//
//                    if (!TextUtils.isEmpty(token)) {
//                        try {
//                            retrofit(v, test.getId());
//                        } catch (Exception e) {
//                            Log.e("JSON Decode Error", e.getMessage());
//                        }
//                    }
//                    else
                    retrofit(v, test.getId());
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
        ResponseData text = responseData.get(position);
        if(text.getImage().equals("")) {
            binding.restaurantImage.setImageResource(R.drawable.no_image);
        }
        else {
            new ImageLoadTask(text.getImage(), binding.restaurantImage).execute();
        }
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
        Call<Detail_ResponseData> call = RetrofitClient.getApiService().Detail_post(id);
        call.enqueue((new Callback<Detail_ResponseData>() {
            @Override
            public void onResponse(Call<Detail_ResponseData> call, Response<Detail_ResponseData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Detail_ResponseData detail_data = response.body();

                String Image = detail_data.getImage();
                String Name = detail_data.getName();
                String Score = detail_data.getScore();
                String Address = detail_data.getAddress();
                String Tag = detail_data.getTag();
                String Tell_number = detail_data.getTell_number();
                boolean Fav = detail_data.getFav();

                Detail_Datastore response_detail_data = new Detail_Datastore(
                        id,
                        Image,
                        Name,
                        Score,
                        Address,
                        Tag,
                        Tell_number,
                        Fav
                );



                Intent intent = new Intent(v.getContext(), Detail_page.class);
                intent.putExtra("responseData", response_detail_data);
                v.getContext().startActivity(intent);
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<Detail_ResponseData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(mContext,"연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
