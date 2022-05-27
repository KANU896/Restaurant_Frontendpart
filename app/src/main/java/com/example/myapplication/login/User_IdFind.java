package com.example.myapplication.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class User_IdFind extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_idfind);

        AlertDialog.Builder builder = new AlertDialog.Builder(User_IdFind.this);
        EditText nameckText = (EditText) findViewById(R.id.nameckText); // 이름 입력 텍스트
        EditText emailckText = (EditText) findViewById(R.id.emailckText); // 이메일 입력 텍스트
        Button checkEmail = (Button) findViewById(R.id.emailck_btn); // 이름 및 이메일 확인, 전송 버튼
        Button find_Exit = (Button) findViewById(R.id.find_exit); // 나가기 버튼



        // 이름 및 이메일 확인 버튼(데이터베이스 안의 이름과 이메일이 일치하는지 확인) -> 데이터베이스 추가시 구현 예정!!(민우)
       checkEmail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // 이름을 입력하지 않s고 전송 버튼을 누를시 이름을 입력하라는 창이뜸 (민우)
                   if (nameckText.getText().toString().length() == 0) {
                       Toast.makeText(User_IdFind.this, "이름를 입력하세요", Toast.LENGTH_SHORT).show();
                   nameckText.requestFocus();
                   return;
               }
               // 이메일을 입력하지 않고 전송 버튼을 누를시 이메일을 입력하라는 창이뜸 (민우)
               if (emailckText.getText().toString().length() == 0 ) {
                   Toast.makeText(User_IdFind.this, "이메일를 입력하세요", Toast.LENGTH_SHORT).show();
                   emailckText.requestFocus();
                   return;
               }
               builder.setTitle("확인 메시지");
               builder.setMessage("임시 비밀번호를 전송하였습니다.");
               builder.setPositiveButton("확인",null);
               builder.create().show();
           }
       });


       // 나가기 버튼 클릭시 로그인 화면으로 전환(민우)
       find_Exit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Handler timer = new Handler(); //Handler 생성
               timer.postDelayed(new Runnable(){ //0.1초후 쓰레드를 생성하는 postDelayed 메소드
                   public void run(){//intent 생성
                       Intent intent = new Intent(getApplicationContext(), User_Login.class);
                       startActivity(intent);
                       finish(); // 이 액티비티를 종료
                   }
               }, 100); // 0.1초 대기
           }
       });


    }
}