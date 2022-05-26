package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_Register extends AppCompatActivity {

    private int id_flag = 0; // 아이디 중복 검사 플래그
    private int pwd_flag = 0; // 비밀번호 일치 검사 플래그

    // 가입환영 팝업 출력, 로그인화면 전환 함수
    void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("회원가입 성공!");
        //타이틀설정
        String congratu_text = "호이닝 코드에 오신 여러분을 환영합니다~";
        builder.setMessage(congratu_text);
        //내용설정
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"가입완료",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();

        Handler timer = new Handler(); //Handler 생성
        timer.postDelayed(new Runnable(){ //2.5초후 쓰레드를 생성하는 postDelayed 메소드
            public void run(){//intent 생성
                Intent intent = new Intent(getApplicationContext(), User_Login.class);
                startActivity(intent);
                finish(); // 이 액티비티를 종료
            }
        }, 2500); // 2.5초 대기
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        AlertDialog.Builder builder = new AlertDialog.Builder(User_Register.this);
        EditText emailText = (EditText) findViewById(R.id.emailText); // 이메일 입력 텍스트
        Button duplicateID = (Button) findViewById(R.id.email_confirm_btn); // 이메일 중복 체크 버튼
        EditText pwd = (EditText) findViewById(R.id.passwordText); // 비밀번호 입력 텍스트
        EditText pwd_cfm = (EditText) findViewById(R.id.password_confirm); // 비밀번호 확인 텍스트
        Button pwd_check = (Button) findViewById(R.id.pwd_confirm_btn); // 비밀번호 확인 버튼
        EditText name = (EditText) findViewById(R.id.nameText); // 이름 입력 텍스트
        Button register_btn = (Button) findViewById(R.id.register_btn); // 회원가입 버튼


        // 이메일 증복체크 버튼(데이터 안에 있는 아이디 중복 검사) -> 데이터베이스 추가시 구현 예정!!
        duplicateID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이메일을 입력하지 않고 중복체크 버튼을 누를시 이메일을 입력하라는 창이뜸 (민우)
                if (emailText.getText().toString().length() == 0 ) {
                    Toast.makeText(User_Register.this, "이메일를 입력하세요", Toast.LENGTH_SHORT).show();
                    emailText.requestFocus();
                    return;
                }
                builder.setTitle("확인 메시지");
                builder.setMessage("사용 가능한 이메일입니다.");
                builder.setPositiveButton("확인",null);
                builder.create().show();
                id_flag = 1; // 이메일 중복 검사 플래그 1로 변경
            }
        });

        // 비밀번호 확인 버튼(일치/불일치 검사)
        pwd_check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (pwd.getText().toString().equals(pwd_cfm.getText().toString())) {
                    // 비밀번호와 비밀번호 확인란에 아무것도 안적고 확인 버튼 누를시 입력하라는 창이뜸 (민우)
                    if(pwd.getText().toString().length() == 0){
                        Toast.makeText(User_Register.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                        pwd.requestFocus();
                        return;
                    }
                    builder.setTitle("확인 메시지");
                    builder.setMessage("비밀번호 일치 ^^");
                    builder.setPositiveButton("확인",null);
                    builder.setNegativeButton("취소",  null);
                    builder.create().show();
                    pwd_flag = 1; // 비밀번호 확인 검사 플래그 1로 변경
                } else{
                    builder.setTitle("확인 메시지");
                    builder.setMessage("비밀번호 불일치!");
                    builder.setPositiveButton("확인",null);
                    builder.setNegativeButton("취소",  null);
                    builder.create().show();
                }
            }
        });

        // 회원 가입 버튼 클릭시 실행
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이메일 입력 필수
                if (emailText.getText().toString().length() == 0 ) {
                    Toast.makeText(User_Register.this, "이메일를 입력하세요", Toast.LENGTH_SHORT).show();
                    emailText.requestFocus();
                    return;
                }
                // 비밀번호 입력 필수
                if ( pwd.getText().toString().length() == 0 ) {
                    Toast.makeText(User_Register.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    pwd.requestFocus();
                    return;
                }
                // 비밀번호 확인 입력 필수
                if ( pwd_cfm.getText().toString().length() == 0 ) {
                    Toast.makeText(User_Register.this, "비밀번호 확인을 입력하세요", Toast.LENGTH_SHORT).show();
                   pwd_cfm.requestFocus();
                    return;
                }
                // 닉네임 입력 필수
                if (name.getText().toString().length() == 0 ) {
                    Toast.makeText(User_Register.this, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                    return;
                }
                // 이메일 중복 체크 필수
                if(id_flag == 0)
                {
                    Toast.makeText(User_Register.this, "이메일 중복 검사를 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 비밀번호 일치 검사 필수
                if(pwd_flag == 0)
                {
                    Toast.makeText(User_Register.this, "비밀번호 확인 검사를 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                show(); // 팝업함수 호출
            }
        });
    }
}
