package com.example.myapplication.Search_List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Detail_Page.Detail_page;
import com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data.Detail_ResponseData;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.ResponseData;
import com.example.myapplication.R;
import com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data.Data_Detail;
import com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data.Detail_Data;
import com.example.myapplication.Search_Page.Search_Retrofit.RetrofitClient_Search;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_List_Adapter extends RecyclerView.Adapter<Search_List_Adapter.MyViewHolder>{
    private ArrayList<ResponseData> responseData;
    private AsyncTask<Void, Void, Bitmap> image_url;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView score;
        //CardView cvItem;

        MyViewHolder(View view){
            super(view);
            image = (ImageView)view.findViewById(R.id.restaurant_image);
            title = (TextView)view.findViewById(R.id.title);
            score = (TextView)view.findViewById(R.id.score);

            //음식점 클릭 시
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    ResponseData test = responseData.get(pos);
                    retrofit(v, test.getId());
                    //Toast.makeText(mContext,test.getName(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public Search_List_Adapter(Context mContext, ArrayList<ResponseData> responseData){
        this.responseData = responseData;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public Search_List_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Search_List_Adapter.MyViewHolder holder, int position) {
        ResponseData text = responseData.get(position);
        if(text.getImage().equals("")) {
            holder.image.setImageResource(R.drawable.no_image);
        }
        else {
            new ImageLoadTask(text.getImage(), holder.image).execute();
        }
        holder.title.setText(text.getName());
        if (text.getScore().equals(""))
            holder.score.setText("점수 없음");
        else
            holder.score.setText(text.getScore());

//        holder.cvItem.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Toast.makeText(mContext,"Toast",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (responseData != null)
           return responseData.size();
        else
            return 0;
    }

    public void retrofit (View v, String id){
        Bundle bundle = new Bundle();

        Call<Detail_Data> call = RetrofitClient_Search.getApiService().Detail_post(id);
        call.enqueue((new Callback<Detail_Data>() {
            @Override
            public void onResponse(Call<Detail_Data> call, Response<Detail_Data> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Detail_Data detail_data = response.body();

                String Image = detail_data.getImage();
                String Name = detail_data.getName();
                String Score = detail_data.getScore();
                String Address = detail_data.getAddress();
                String Tag = detail_data.getTag();
                String Menu = detail_data.getMenu();

                Detail_ResponseData response_detail_data = new Detail_ResponseData(
                        Image,
                        Name,
                        Score,
                        Address,
                        Tag,
                        Menu
                );

                Intent intent = new Intent(v.getContext(), Detail_page.class);
                intent.putExtra("responseData", response_detail_data);
                v.getContext().startActivity(intent);
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<Detail_Data> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(mContext,"연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
