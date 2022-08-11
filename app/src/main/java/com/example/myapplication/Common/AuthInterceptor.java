package com.example.myapplication.Common;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //SharedPreferencesUtil spref = new SharedPreferencesUtil(,"User");
        return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "header").build());
    }
}
