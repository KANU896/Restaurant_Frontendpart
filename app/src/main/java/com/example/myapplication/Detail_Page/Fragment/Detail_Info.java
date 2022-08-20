package com.example.myapplication.Detail_Page.Fragment;

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
    private TextView detail_menu, detail_tag, detail_address;
    private JSONObject jObject = null;
    double x, y;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_info, container,false);

        responseData = (Detail_ResponseData) getArguments().getSerializable("responseData");

        //TODO 메뉴
        LinearLayout menu_layout = view.findViewById(R.id.menu_layout);
        Button menu_button = view.findViewById(R.id.menubutton);

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) menu_button.getText();

                // 펼치기
                if (text.equals("+")){
                    menu_button.setText("-");
                    menu_layout.setVisibility(View.VISIBLE);
                }
                // 접기
                else{
                    menu_button.setText("+");
                    menu_layout.setVisibility(View.GONE);
                }
            }
        });

        //TODO 위치
        detail_address = view.findViewById(R.id.detail_address);
        Button location_button = view.findViewById(R.id.locationbutton);
        LinearLayout location_layout = view.findViewById(R.id.location_layout);
        String address = responseData.getAddress();
        if(!TextUtils.isEmpty(address))
            detail_address.setText(address);
        else
            detail_address.setText("찾을 수 없습니다.");

        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) location_button.getText();

                // 펼치기
                if (text.equals("+")){
                    location_button.setText("-");
                    location_layout.setVisibility(View.VISIBLE);
                }
                // 접기
                else{
                    location_button.setText("+");
                    location_layout.setVisibility(View.GONE);
                }
            }
        });


        //TODO TAG
        detail_tag = view.findViewById(R.id.detail_tag);
        Button tag_button = view.findViewById(R.id.tagbutton);
        LinearLayout tag_layout = view.findViewById(R.id.tag_layout);
        detail_tag.setText(responseData.getTag());

        tag_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) tag_button.getText();

                // 펼치기
                if (text.equals("+")){
                    tag_button.setText("-");
                    tag_layout.setVisibility(View.VISIBLE);
                }
                // 접기
                else{
                    tag_button.setText("+");
                    tag_layout.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}
