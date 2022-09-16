package com.example.myapplication.Detail_Page;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.JWTUtils;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_Review_Datastore;
import com.example.myapplication.R;
import com.example.myapplication.databinding.DetailContentBinding;

import org.json.JSONObject;

import java.util.ArrayList;

public class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.MyViewHolder>{
    private ArrayList<Detail_Review_Datastore> responseData;
    private Context mContext;
    private Delete_Content delete_content;
    private DetailContentBinding binding;

    public Review_Adapter(Context mContext, ArrayList<Detail_Review_Datastore> responseData, Delete_Content delete_content){
        this.responseData = responseData;
        this.mContext = mContext;
        this.delete_content = delete_content;
    }

    public void setData(ArrayList<Detail_Review_Datastore> responseData){
        this.responseData = responseData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView content;
//        TextView username;
//        TextView datetime;
//        Button delete_button;


        MyViewHolder(DetailContentBinding view){
            super(view.getRoot());
//            content = view.findViewById(R.id.review_content);
//            datetime = view.findViewById(R.id.review_datetime);
//            username = view.findViewById(R.id.username);
//            delete_button = view.findViewById(R.id.review_delete);

            // 삭제 버튼 클릭 시
            binding.reviewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Detail_Review_Datastore delete_data = responseData.get(pos);
                    Log.e("click content delete", String.valueOf(pos));
                    delete_content.onSearchItemDeleteClicked(delete_data.getReview_id());
                }
            });
        }
    }

    @NonNull
    @Override
    public Review_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DetailContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_content,parent,false);
        return new Review_Adapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Review_Adapter.MyViewHolder holder, int position) {
        Detail_Review_Datastore data = responseData.get(position);
        binding.username.setText(data.getName());
        binding.reviewDatetime.setText(data.getDatetime());
        binding.reviewContent.setText(data.getContent());

        SharedPreferencesUtil spref = new SharedPreferencesUtil(mContext, "User");
        String token = spref.getPreferenceString("token");
        String token_info = null;
        try {
            JSONObject jObject = JWTUtils.decoded(token);
            token_info = jObject.getString("username");
        } catch (Exception e) {
            Log.e("JSON Decode Error", e.getMessage());
        }

        if(TextUtils.isEmpty(token_info)){
            binding.reviewDelete.setVisibility(View.INVISIBLE);
        }
        else{
            if (!token_info.equals(data.getName())){
                binding.reviewDelete.setVisibility(View.INVISIBLE);
            }
            else {
                binding.reviewDelete.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (responseData != null)
            return responseData.size();
        else
            return 0;
    }
}
