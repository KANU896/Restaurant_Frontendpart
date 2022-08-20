package com.example.myapplication.Detail_Page;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.JWTUtils;
import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_Data;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_ResponseData;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_Review_Data;
import com.example.myapplication.R;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.MyViewHolder>{
        private ArrayList<Detail_Review_Data> responseData;
        private Context mContext;
        private Delete_Content delete_content;

    public Review_Adapter(Context mContext, ArrayList<Detail_Review_Data> responseData, Delete_Content delete_content){
            this.responseData = responseData;
            this.mContext = mContext;
            this.delete_content = delete_content;
        }

        public void setData(ArrayList<Detail_Review_Data> responseData){
            this.responseData = responseData;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView content;
            TextView username;
            TextView datetime;
            Button delete_button;


            MyViewHolder(View view){
                super(view);
                content = view.findViewById(R.id.review_content);
                datetime = view.findViewById(R.id.review_datetime);
                username = view.findViewById(R.id.username);
                delete_button = view.findViewById(R.id.review_delete);

                // 삭제 버튼 클릭 시
                delete_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        Detail_Review_Data delete_data = responseData.get(pos);
                        Log.e("click content delete", String.valueOf(pos));
                        delete_content.onSearchItemDeleteClicked(delete_data.getReview_id());
                    }
                });
            }
        }

        @NonNull
        @Override
        public Review_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_content,parent,false);
            return new Review_Adapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Review_Adapter.MyViewHolder holder, int position) {
            Detail_Review_Data data = responseData.get(position);
            holder.username.setText(data.getName());
            holder.datetime.setText(data.getDatetime());
            holder.content.setText(data.getContent());

            SharedPreferencesUtil spref = new SharedPreferencesUtil(mContext, "User");
            String token = spref.getPreferenceString("token");
            String token_info = null;
            try {
                JSONObject jObject = JWTUtils.decoded(token);
                token_info = jObject.getString("username");
            } catch (Exception e) {
                Log.e("JSON Decode Error", e.getMessage());
            }

            if (!token_info.equals(data.getName()) || TextUtils.isEmpty(token_info)) {
                holder.delete_button.setVisibility(View.INVISIBLE);
            } else {
                holder.delete_button.setVisibility(View.VISIBLE);
            }

//            catch (NullPointerException e){
//                Log.e("NullpointerException", "error");
//                holder.delete_button.setVisibility(View.INVISIBLE);
//            }

        }

        @Override
        public int getItemCount() {
            if (responseData != null)
                return responseData.size();
            else
                return 0;
        }

        public void retrofit (View v, String id, String username){
            Call<Detail_Data> call = RetrofitClient.getApiService().Detail_post(id, username);
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
                    boolean Fav = detail_data.getFav();

                    Detail_ResponseData response_detail_data = new Detail_ResponseData(
                            id,
                            Image,
                            Name,
                            Score,
                            Address,
                            Tag,
                            Menu,
                            Fav
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
