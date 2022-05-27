package com.example.myapplication.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.User_Main;

//로그인화면 -> 회원가입화면 전환
public class User_Login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        Button reg_btn = (Button) findViewById(R.id.reg_btn); // 회원가입 버튼
        //로그인화면 -> 회원가입화면 전환
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_Register.class);
                startActivity(intent);
            }
        });

        Button id_find = (Button) findViewById(R.id.id_find); // 계정찾기 버튼
        //로그인화면 -> 계정찾기화면 전환
        id_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_IdFind.class);
                startActivity(intent);
            }
        });

        Button login_btn = (Button) findViewById(R.id.login_btn); // 로그인 버튼
        //로그인화면 -> 메인화면 전환
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_Main.class);
                startActivity(intent);
            }
        });
    }
}

