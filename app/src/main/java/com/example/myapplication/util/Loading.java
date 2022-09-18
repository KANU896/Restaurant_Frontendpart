package com.example.myapplication.util;

import android.content.Context;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class Loading {
    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        // 길이, 높이 지정
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        // 프로그레스 바를 작동시키고
        progressDrawable.start();
        // 이 프로그레스 바를 리턴시킨다
        return progressDrawable;
    }
}
