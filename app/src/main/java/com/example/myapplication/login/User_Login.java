package com.example.myapplication.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.User_Main;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//로그인화면 -> 회원가입화면 전환
public class User_Login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        EditText userid = (EditText) findViewById(R.id.userid);
        EditText password = (EditText)findViewById(R.id.password);

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
                String id = userid.getText().toString();
                String pw = password.getText().toString();
                Call<LoginData> call = RetrofitClient_Login.getApiService().postOverlapCheck(id,pw);
                call.enqueue((new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        if(!response.isSuccessful()){
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                            return;
                        }
                        LoginData checkAlready = response.body();
                        Log.d("연결이 성공적 : ", response.body().getToken());
                        Intent intent = new Intent(getApplicationContext(), User_Main.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Log.e("연결실패", t.getMessage());
                    }
                }));



            }
        });
    }
}

