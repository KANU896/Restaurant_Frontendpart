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

import com.example.myapplication.Data.Detail.Detail_Datastore;
import com.example.myapplication.databinding.DetailInfoBinding;

public class Detail_Info extends Fragment {
    private Detail_Datastore responseData;
    private DetailInfoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailInfoBinding.inflate(getLayoutInflater(), container, false);

        responseData = (Detail_Datastore) getArguments().getSerializable("responseData");

        //TODO 카테고리


        //TODO 주소
        String address = responseData.getAddress();
        if(!TextUtils.isEmpty(address))
            binding.detailAddress.setText(address);
        else
            binding.detailAddress.setText("-");

        //TODO 운영시간

        //TODO 번호
        String number = responseData.getTell_number();
        if(!TextUtils.isEmpty(number)){
            binding.infoNumber.setText(number);
        }
        else{
            binding.infoNumber.setText("-");
        }

        //TODO TAG
        //detail_tag = view.findViewById(R.id.detail_tag);
        String tag = responseData.getTag();
        if(!TextUtils.isEmpty(tag)){
            binding.detailTag.setText(tag);
        }
        else{
            binding.detailTag.setText("-");
        }

        //Button instar_button = view.findViewById(R.id.instar_button);
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
