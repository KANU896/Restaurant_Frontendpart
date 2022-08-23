package com.example.myapplication.Detail_Page.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Detail_Page.Detail_Data.Detail_ResponseData;
import com.example.myapplication.R;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.ResponseData;

import org.json.JSONObject;

import java.util.ArrayList;

public class Detail_Info extends Fragment {
    private View view;

    private Detail_ResponseData responseData;
    private TextView detail_menu, detail_tag, detail_address, detail_number, detail_category, detail_opentime;
    private JSONObject jObject = null;
    double x, y;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_info, container,false);

        responseData = (Detail_ResponseData) getArguments().getSerializable("responseData");

        //TODO 카테고리
        detail_category = view.findViewById(R.id.info_category);


        //TODO 주소
        detail_address = view.findViewById(R.id.detail_address);
        String address = responseData.getAddress();
        if(!TextUtils.isEmpty(address))
            detail_address.setText(address);
        else
            detail_address.setText("-");

        //TODO 운영시간
        detail_opentime = view.findViewById(R.id.info_opentime);

        //TODO 번호
        detail_number = view.findViewById(R.id.info_number);
        String number = responseData.getTell_number();
        if(!TextUtils.isEmpty(number)){
            detail_number.setText(number);
        }
        else{
            detail_number.setText("-");
        }

        //TODO TAG
        detail_tag = view.findViewById(R.id.detail_tag);
        String tag = responseData.getTag();
        if(!TextUtils.isEmpty(tag)){
            detail_tag.setText(tag);
        }
        else{
            detail_tag.setText("-");
        }

        Button instar_button = view.findViewById(R.id.instar_button);
        String url = "https://www.instagram.com/explore/tags/"+responseData.getName()+"/";
        Uri instagram = Uri.parse(url);
        Intent instar_intent = new Intent(Intent.ACTION_VIEW, instagram);

        instar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(instar_intent);
            }
        });

        return view;
    }
}
