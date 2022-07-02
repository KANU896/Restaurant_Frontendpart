package com.example.myapplication.Detail_Page;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Search_List.ImageLoadTask;
import com.example.myapplication.Search_Page.Search_Retrofit.Detail_Data.Detail_ResponseData;

public class Detail_page extends AppCompatActivity {
    Detail_ResponseData responseData;
    TextView detail_title, detail_menu, detail_tag, detail_score, detail_address;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        Intent intent = getIntent();
        responseData = (Detail_ResponseData) intent.getSerializableExtra("responseData");

        Log.e("Detail_page", responseData.getTag());

        detail_title = findViewById(R.id.detail_title);
        detail_menu = findViewById(R.id.detail_menu);
        detail_tag = findViewById(R.id.detail_tag);
        detail_score = findViewById(R.id.detail_score);
        detail_address = findViewById(R.id.detail_address);
        imageView = findViewById(R.id.detail_image);

        detail_title.setText(responseData.getName());
        detail_score.setText(responseData.getScore());
        detail_address.setText(responseData.getAddress());
        detail_tag.setText(responseData.getTag());
        detail_menu.setText(responseData.getMenu());
        new ImageLoadTask(responseData.getImage(), imageView).execute();
    }
}
