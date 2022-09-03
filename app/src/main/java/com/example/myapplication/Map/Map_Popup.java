package com.example.myapplication.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.myapplication.R;

public class Map_Popup extends AppCompatActivity {

    private TextView title;
    private TextView Score;
    private TextView address;

    private PopupMenu getData;

    @Override
    protected void Popup(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_pupop);

        Intent intent = getIntent();
        getData = (PopupMenu) intent.getSerializableExtra("data");

        title = (TextView)findViewById(R.id.map_pupop_title);
        Score = (TextView)findViewById(R.id.map_pupop_Score);
        address = (TextView)findViewById(R.id.map_pupop_address);

        title.setText(getData.getName());
        Score.setText(getData.getScore());
        address.setText(getData.getAddress());
    }
    public void mOnClose(View v){
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
