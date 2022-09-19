package com.example.myapplication.Fragment.ViewpagerFragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Data.DayRecommendArrayData;
import com.example.myapplication.Data.DayRecommendResponseData;
import com.example.myapplication.databinding.FragmentRecommendBinding;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.SharedPreferencesUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recommend_fragment extends Fragment {
    private SharedPreferencesUtil spref;
    private Context mContext;
    private FragmentRecommendBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecommendBinding.inflate(getLayoutInflater(), container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<DayRecommendResponseData> responseData = new ArrayList<>();

        mContext = getContext();

        spref = new SharedPreferencesUtil(getContext(), "Searched");
        String location = spref.getPreferenceString("location");

        if (location.equals("위치 정보 없음") || TextUtils.isEmpty(location)) location = null;

        String category = getArguments().getString("category");

        Call<DayRecommendArrayData> call = RetrofitClient.getApiService().Day_recommend(location, category);
        call.enqueue((new Callback<DayRecommendArrayData>() {
            @Override
            public void onResponse(Call<DayRecommendArrayData> call, Response<DayRecommendArrayData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                DayRecommendArrayData review_data = response.body();

                DayRecommendResponseData[] data = review_data != null
                        ? review_data.getData()
                        :new DayRecommendResponseData[0];

                for (int i = 0; i < data.length; i++){
                    String Restaurant_id = data[i].getRestaurant_id();
                    String Name = data[i].getName();
                    String Address = data[i].getAddress();
                    String Tag = data[i].getTag();

                    responseData.add(new DayRecommendResponseData(
                            Restaurant_id,
                            Name,
                            Address,
                            Tag
                    ));
                }

                int position = getArguments().getInt("position");

                if (responseData.isEmpty()){
                    binding.viewpagerTitle.setText("해당 정보가 없습니다.");
                    return;
                }
                DayRecommendResponseData current_data = responseData.get(position);

                binding.viewpagerTitle.setText(current_data.getName());
                if (current_data.getAddress().equals(""))
                    binding.viewpagerAddress.setText("-");
                else
                    binding.viewpagerAddress.setText(current_data.getAddress());
                binding.viewpagerTag.setText(current_data.getTag());
            }
            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<DayRecommendArrayData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(mContext, "연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}