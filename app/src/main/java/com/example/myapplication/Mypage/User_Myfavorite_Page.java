package com.example.myapplication.Mypage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.Common.JWTUtils;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.R;
import com.example.myapplication.Login.User_Login;

import org.json.JSONObject;

public class User_Myfavorite_Page extends Fragment {
    private Button myfavorite_login;
    private TextView comment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.myfavorite_page, container, false);

        comment = view.findViewById(R.id.comment);
        myfavorite_login = (Button) view.findViewById(R.id.myfavorite_login_btn); // 로그인 버튼

        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(this.getActivity(), "User");
        String token = sharedPreferencesUtil.getPreferenceString("token");
        if (!TextUtils.isEmpty(token)) {
            myfavorite_login.setText("로그아웃");
            //String token = sharedPreferencesUtil.getPreferenceString("token");ㅅ
            try {
                JSONObject jObject = JWTUtils.decoded(token);
                comment.setText(jObject.getString("username"));
            } catch (Exception e) {
                Log.e("JSON Decode Error", e.getMessage());
            }
        }
        else{
            comment.setText("로그인 하시면 나만의 즐겨찾기 관리와 리뷰 작성을 하실 수 있습니다.");
        }

        // 버튼 클릭 시
        myfavorite_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myfavorite_login.getText().equals("로그인")) {
                    Intent intent = new Intent(getActivity(), User_Login.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else {
                    sharedPreferencesUtil.deletePreference("token");
                    String token = sharedPreferencesUtil.getPreferenceString("token");
                    if (TextUtils.isEmpty(token)) {
                        myfavorite_login.setText("로그인");
                        comment.setText("로그인 하시면 나만의 즐겨찾기 관리와 리뷰 작성을 하실 수 있습니다.");
                    }
                    Log.e("token", sharedPreferencesUtil.getPreferenceString("token")+"끝");
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}