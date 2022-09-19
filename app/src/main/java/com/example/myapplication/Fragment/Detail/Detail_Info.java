package com.example.myapplication.Fragment.Detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Data.StoreResponseData;
import com.example.myapplication.databinding.FragmentDetailInfoBinding;

public class Detail_Info extends Fragment {
    private StoreResponseData responseData;
    private FragmentDetailInfoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailInfoBinding.inflate(getLayoutInflater(), container, false);

        responseData = (StoreResponseData) getArguments().getSerializable("responseData");

        //TODO 카테고리
        binding.infoCategory.setText(responseData.getCategory());

        //TODO 주소
        String address = responseData.getAddress();
        if(!TextUtils.isEmpty(address))
            binding.detailAddress.setText(address);
        else
            binding.detailAddress.setText("-");

        //TODO 운영시간
        binding.infoOpentime.setText(responseData.getOpen_time());

        //TODO 번호
        String number = responseData.getTell_number();
        if(!TextUtils.isEmpty(number)){
            binding.infoNumber.setText(number);
        }
        else{
            binding.infoNumber.setText("-");
        }

        //TODO TAG
        String tag = responseData.getTag();
        if(!TextUtils.isEmpty(tag)){
            binding.detailTag.setText(tag);
        }
        else{
            binding.detailTag.setText("-");
        }

        String url = "https://www.instagram.com/explore/tags/"+responseData.getName()+"/";
        Uri instagram = Uri.parse(url);
        Intent instar_intent = new Intent(Intent.ACTION_VIEW, instagram);

        binding.instarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(instar_intent);
            }
        });

        return binding.getRoot();
    }
}
