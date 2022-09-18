package com.example.myapplication.util;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPreferencesUtil spref = new SharedPreferencesUtil(MyApp.getContext(),"User");
        String token = spref.getPreferenceString("token");
        if (TextUtils.isEmpty(token)){
            return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "").build());
        }
        return chain.proceed(chain.request().newBuilder().addHeader("Authorization", token).build());
    }
}
