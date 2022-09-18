package com.example.myapplication.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.JWTUtils;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Data.Detail_Review_ResponseData;
import com.example.myapplication.Interface.onReviewItemDelete;
import com.example.myapplication.databinding.DetailContentBinding;

import org.json.JSONObject;

import java.util.ArrayList;

public class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.MyViewHolder>{
    private ArrayList<Detail_Review_ResponseData> responseData;
    private Context mContext;
    private onReviewItemDelete onReviewItemDelete_;
    private DetailContentBinding binding;

    public Review_Adapter(Context mContext, ArrayList<Detail_Review_ResponseData> responseData, onReviewItemDelete onReviewItemDelete_){
        this.responseData = responseData;
        this.mContext = mContext;
        this.onReviewItemDelete_ = onReviewItemDelete_;
    }

    public void setData(ArrayList<Detail_Review_ResponseData> responseData){
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
                    //Detail_Review_ResponseData delete_data = responseData.get(pos);
                    Log.e("click content delete", String.valueOf(pos));
                    onReviewItemDelete_.onReviewItemDelete(responseData.get(pos).getReview_id());
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
        Detail_Review_ResponseData data = responseData.get(position);
        binding.username.setText(data.getUsername());
        binding.reviewDatetime.setText(data.getDate());
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
            if (!token_info.equals(data.getUsername())){
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
