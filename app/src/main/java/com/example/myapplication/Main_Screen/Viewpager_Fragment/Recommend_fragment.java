package com.example.myapplication.Main_Screen.Viewpager_Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Main_Screen.DayRecommend_Data.DR_Data;
import com.example.myapplication.Main_Screen.DayRecommend_Data.DR_Datastore;
import com.example.myapplication.Main_Screen.DayRecommend_Data.DR_ResponseData;
import com.example.myapplication.R;
import com.example.myapplication.Search_List.ImageLoadTask;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recommend_fragment extends Fragment {
    private ImageView Image;
    private TextView Address, Title, Notice;
    private SharedPreferencesUtil spref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpager_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("Fragment", String.valueOf(getArguments().getInt("position")));
        ArrayList<DR_Datastore> responseData = new ArrayList<>();

        Image = view.findViewById(R.id.viewpager_image);
        Title = view.findViewById(R.id.viewpager_title);
        Address = view.findViewById(R.id.viewpager_address);
        Notice = view.findViewById(R.id.notice_text);

        spref = new SharedPreferencesUtil(getContext(), "Searched");
        String location = spref.getPreferenceString("location");

        if (!TextUtils.isEmpty(location) && !location.equals("")) {
            String remove_text = "대한민국 ";
            location = location.replace(remove_text,"");
        }
        if (location.equals("위치 정보 없음")) location = null;

        String category = getArguments().getString("category");

        Call<DR_Data> call = RetrofitClient.getApiService().Day_recommend(location, category);
        call.enqueue((new Callback<DR_Data>() {
            @Override
            public void onResponse(Call<DR_Data> call, Response<DR_Data> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                DR_Data review_data = response.body();

                DR_ResponseData[] data = review_data != null
                        ? review_data.getData()
                        :new DR_ResponseData[0];

                for (int i = 0; i < data.length; i++){
                    String Restaurant_id = data[i].getRestaurant_id();
                    String Name = data[i].getName();
                    String Image = data[i].getImage();
                    String Address = data[i].getAddress();

                    responseData.add(new DR_Datastore(
                            Restaurant_id,
                            Name,
                            Image,
                            Address
                    ));
                }
                int position = getArguments().getInt("position");
                if (responseData.isEmpty()){
                    Notice.setVisibility(View.VISIBLE);
                    Image.setVisibility(View.GONE);
                    Title.setVisibility(View.GONE);
                    Address.setVisibility(View.GONE);
                    return;
                }
                Notice.setVisibility(View.GONE);
                Image.setVisibility(View.VISIBLE);
                Title.setVisibility(View.VISIBLE);
                Address.setVisibility(View.VISIBLE);
                DR_Datastore current_data = responseData.get(position);
                Log.e("data", current_data.getName());
                if(current_data.getImage().equals("")) {
                    Image.setImageResource(R.drawable.no_image);
                }
                else {
                    new ImageLoadTask(current_data.getImage(), Image).execute();
                }
                Title.setText(current_data.getName());
                if (current_data.getAddress().equals(""))
                    Address.setText("-");
                else
                    Address.setText(current_data.getAddress());
            }
            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<DR_Data> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(getContext(), "연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}