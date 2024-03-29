package com.example.myapplication.Detail_Page.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Detail_Page.Detail_Data.Detail_Datastore;
import com.example.myapplication.R;
import com.example.myapplication.databinding.DetailInfoBinding;

public class Detail_Info extends Fragment {
    //private View view;

    private Detail_Datastore responseData;
    //private TextView detail_tag, detail_address, detail_number, detail_category, detail_opentime;
    private DetailInfoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailInfoBinding.inflate(getLayoutInflater(), container, false);
        //view = inflater.inflate(R.layout.detail_info, container,false);

        responseData = (Detail_Datastore) getArguments().getSerializable("responseData");

        //TODO 카테고리
        //detail_category = view.findViewById(R.id.info_category);


        //TODO 주소
        //detail_address = view.findViewById(R.id.detail_address);
        String address = responseData.getAddress();
        if(!TextUtils.isEmpty(address))
            binding.detailAddress.setText(address);
        else
            binding.detailAddress.setText("-");

        //TODO 운영시간
        //detail_opentime = view.findViewById(R.id.info_opentime);

        //TODO 번호
        //detail_number = view.findViewById(R.id.info_number);
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
