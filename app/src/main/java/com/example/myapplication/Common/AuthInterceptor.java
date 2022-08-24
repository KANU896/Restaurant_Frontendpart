package com.example.myapplication.Common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPreferencesUtil spref = new SharedPreferencesUtil(MyApp.getContext(),"User");

        Log.e("AuthInterceptor", "token");
        String token = spref.getPreferenceString("token");
        if (TextUtils.isEmpty(token)){
            return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "").build());
        }
        return chain.proceed(chain.request().newBuilder().addHeader("Authorization", token).build());
    }
}
