// 로그인 페이지
// 담당 : 김도윤 - 로그인 서버 통신 기능(retrofit2) 구현
// Update : 22.08.18

package com.example.myapplication.Login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.Login.Login_Data.Login_Token;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.UserLoginBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//로그인화면 -> 회원가입화면 전환
public class User_Login extends AppCompatActivity {
    SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(this, "User");
    private UserLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.user_login);

        //Button reg_btn = (Button) findViewById(R.id.reg_btn); // 회원가입 버튼
        //로그인화면 -> 회원가입화면 전환
        binding.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_Register.class);
                startActivity(intent);
            }
        });

        // 로그인 버튼
//        Button login_btn = findViewById(R.id.login_btn);
//        EditText username = findViewById(R.id.username);
//        EditText password = findViewById(R.id.password);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAppKeyHash();
                String id = binding.username.getText().toString();
                String pw = binding.password.getText().toString();
                retrofit(id, pw);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void retrofit (String username, String password){
        Call<Login_Token> call = RetrofitClient.getApiService().Login_post(username, password);
        call.enqueue((new Callback<Login_Token>() {
            @Override
            public void onResponse(Call<Login_Token> call, Response<Login_Token> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Login_Token login_data = response.body();

                String token = login_data.getToken();

                if(login_data.getMsg().equals("success")) {
                    sharedPreferencesUtil.setPreference("token", token);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                else {
                    Toast.makeText(getApplicationContext(), login_data.getMsg(), Toast.LENGTH_LONG).show();
                    Log.e("틀림", login_data.getMsg());
                }
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<Login_Token> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(getApplicationContext(),"연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("name not found", e.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

