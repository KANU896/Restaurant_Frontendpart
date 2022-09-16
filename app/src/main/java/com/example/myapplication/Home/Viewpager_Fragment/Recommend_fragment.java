package com.example.myapplication.Home.Viewpager_Fragment;

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

import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Home.DayRecommend_Data.DR_Data;
import com.example.myapplication.Home.DayRecommend_Data.DR_Datastore;
import com.example.myapplication.Home.DayRecommend_Data.DR_ResponseData;
import com.example.myapplication.Common.ImageLoadTask;
import com.example.myapplication.databinding.FragmentViewpagerBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recommend_fragment extends Fragment {
//    private ImageView Image;
//    private TextView Address, Title;
    private SharedPreferencesUtil spref;
    private Context mContext;
    private FragmentViewpagerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViewpagerBinding.inflate(getLayoutInflater(), container,false);
        //return inflater.inflate(R.layout.fragment_viewpager_, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<DR_Datastore> responseData = new ArrayList<>();

        mContext = getContext();

//        Image = view.findViewById(R.id.viewpager_image);
//        Title = view.findViewById(R.id.viewpager_title);
//        Address = view.findViewById(R.id.viewpager_address);

        spref = new SharedPreferencesUtil(getContext(), "Searched");
        String location = spref.getPreferenceString("location");

        if (location.equals("위치 정보 없음") || TextUtils.isEmpty(location)) location = null;

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
                    binding.viewpagerTitle.setText("해당 정보가 없습니다.");
                    return;
                }
                DR_Datastore current_data = responseData.get(position);

                if(!TextUtils.isEmpty(current_data.getImage())) {
                    //Glide.with(getContext()).load(current_data.getImage()).into(binding.viewpagerImage);
                    new ImageLoadTask(current_data.getImage(), binding.viewpagerImage).execute();
                }
                binding.viewpagerTitle.setText(current_data.getName());
                if (current_data.getAddress().equals(""))
                    binding.viewpagerAddress.setText("-");
                else
                    binding.viewpagerAddress.setText(current_data.getAddress());
            }
            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<DR_Data> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(mContext, "연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}