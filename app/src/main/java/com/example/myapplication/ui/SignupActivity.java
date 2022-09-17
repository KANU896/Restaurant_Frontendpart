// 회원가입 페이지
// 담당 : 김도윤 - 아이디 중복 확인 기능 구현(retrofit2)
//               회원가입 기능 구현(retrofit2)
// Update : 22.08.18

package com.example.myapplication.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Data.Sign.Signup_Data;
import com.example.myapplication.databinding.UserRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private int id_flag = 0; // 아이디 중복 검사 플래그
    private int pwd_flag = 0; // 비밀번호 일치 검사 플래그
    private AlertDialog.Builder builder;
    private UserRegisterBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.user_register);

        builder = new AlertDialog.Builder(SignupActivity.this);
//        EditText id = findViewById(R.id.id); // 이메일 입력 텍스트
//        Button duplicateID = findViewById(R.id.id_confirm_btn); // 이메일 중복 체크 버튼
//        EditText pwd = findViewById(R.id.passwordText); // 비밀번호 입력 텍스트
//        EditText pwd_cfm = findViewById(R.id.password_confirm); // 비밀번호 확인 텍스트
//        Button pwd_check = findViewById(R.id.pwd_confirm_btn); // 비밀번호 확인 버튼
//        EditText name = findViewById(R.id.nameText); // 이름 입력 텍스트
//        Button register_btn = findViewById(R.id.register_btn); // 회원가입 버튼


        // 이메일 증복체크 버튼(데이터 안에 있는 아이디 중복 검사) -> 데이터베이스 추가시 구현 예정!!
        binding.idConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이메일을 입력하지 않고 중복체크 버튼을 누를시 이메일을 입력하라는 창이뜸 (민우)
                if (binding.id.getText().toString().length() == 0 ) {
                    Toast.makeText(SignupActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    binding.id.requestFocus();
                    return;
                }

                Call<Signup_Data> call = RetrofitClient.getApiService().IDCheck_post(binding.id.getText().toString());
                retrofit(call, "IDCheck");
            }
        });

        // 비밀번호 확인 버튼(일치/불일치 검사)
        binding.pwdConfirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (binding.passwordText.getText().toString().equals(binding.passwordConfirm.getText().toString())) {
                    // 비밀번호와 비밀번호 확인란에 아무것도 안적고 확인 버튼 누를시 입력하라는 창이뜸 (민우)
                    if(binding.passwordText.getText().toString().length() == 0){
                        Toast.makeText(SignupActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                        binding.passwordText.requestFocus();
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
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이디 입력 필수
                if (binding.id.getText().toString().length() == 0 ) {
                    Toast.makeText(SignupActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    binding.id.requestFocus();
                    return;
                }
                // 비밀번호 입력 필수
                if (binding.passwordText.getText().toString().length() == 0 ) {
                    Toast.makeText(SignupActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    binding.passwordText.requestFocus();
                    return;
                }
                // 비밀번호 확인 입력 필수
                if (binding.passwordConfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(SignupActivity.this, "비밀번호 확인을 입력하세요", Toast.LENGTH_SHORT).show();
                    binding.passwordConfirm.requestFocus();
                    return;
                }
                // 닉네임 입력 필수
                if (binding.nameText.getText().toString().length() == 0 ) {
                    Toast.makeText(SignupActivity.this, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                    binding.nameText.requestFocus();
                    return;
                }
                // 아이디 중복 체크 필수
                if(id_flag == 0)
                {
                    Toast.makeText(SignupActivity.this, "아이디 중복 검사를 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 비밀번호 일치 검사 필수
                if(pwd_flag == 0)
                {
                    Toast.makeText(SignupActivity.this, "비밀번호 확인 검사를 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                //TODO Retrofit2 연결
                Call<Signup_Data> call = RetrofitClient.getApiService().Signup_post(
                        binding.id.getText().toString(),
                        binding.passwordText.getText().toString(),
                        binding.nameText.getText().toString()
                );
                retrofit(call, "Signup");
            }
        });
    }

    public void retrofit (Call<Signup_Data> call, String a){
        call.enqueue((new Callback<Signup_Data>() {
            @Override
            public void onResponse(Call<Signup_Data> call, Response<Signup_Data> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                String msg = response.body().getMsg();
                if (a.equals("Signup")) {
                    if (msg.equals("등록 완료"))
                        show(); // 팝업함수 호출
                    else
                        Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                else{ // IDCheck
                    builder.setTitle("확인 메시지");
                    builder.setMessage(msg);
                    builder.setPositiveButton("확인",null);
                    builder.create().show();
                    if (msg.equals("사용 가능한 아이디입니다.")) id_flag = 1; // 이메일 중복 검사 플래그 1로 변경
                    else id_flag = 0;
                }
            }
            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<Signup_Data> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(getApplicationContext(), "서버연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }

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
        timer.postDelayed(new Runnable(){ //Login.5초후 쓰레드를 생성하는 postDelayed 메소드
            public void run(){//intent 생성
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish(); // 이 액티비티를 종료
            }
        }, 2500); // Login.5초 대기
    }
}
