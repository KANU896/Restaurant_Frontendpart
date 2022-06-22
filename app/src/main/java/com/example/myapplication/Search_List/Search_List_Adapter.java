package com.example.myapplication.Search_List;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Search_Page.ResponseData;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Search_List_Adapter extends RecyclerView.Adapter<Search_List_Adapter.MyViewHolder> {
    private ArrayList<ResponseData> responseData;
    private AsyncTask<Void, Void, Bitmap> image_url;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView score;

        MyViewHolder(View view){
            super(view);
            image = (ImageView)view.findViewById(R.id.restaurant_image);
            title = (TextView)view.findViewById(R.id.title);
            score = (TextView)view.findViewById(R.id.score);
        }
    }

    public Search_List_Adapter(ArrayList<ResponseData> responseData){
        this.responseData = responseData;
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
    }

    @Override
    public int getItemCount() {
        if (responseData != null)
           return responseData.size();
        else
            return 0;
    }
}
