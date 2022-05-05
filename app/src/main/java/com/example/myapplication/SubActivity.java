package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button reg_btn = (Button) findViewById(R.id.reg_btn); // 회원가입 버튼
        Button button2 = (Button) findViewById(R.id.button2); // 계정찾기 버튼


        //로그인화면 -> 회원가입화면 전환
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), log_to_reg.class);
                startActivity(intent);
            }
        });

        //로그인화면 -> 계정찾기 전환
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), log_to_reg2.class);
                startActivity(intent);
            }
        });


    }

}

