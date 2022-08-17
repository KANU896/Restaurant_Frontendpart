// 작성자 : 김도윤
// 마이페이지 - 로그인 & 로그아웃과 로그인시 해당 계정 즐겨찾기 목록 보여줌
// Update : 22.08.18

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.JWTUtils;
import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.R;
import com.example.myapplication.Login.User_Login;
import com.example.myapplication.Search_List.Search_List_Adapter;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.Data;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.ResponseData;
import com.example.myapplication.Search_Page.Search_Retrofit.Search_Data.SearchData;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Myfavorite_Page extends Fragment {
    private Button myfavorite_login;
    private TextView comment;
    private RecyclerView recyclerView;
    private ArrayList<ResponseData> responseData = new ArrayList<>();
    private Search_List_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.myfavorite_page, container, false);
        Log.e("User_Myfavorite_Page", "접속");
        comment = view.findViewById(R.id.comment);
        myfavorite_login = (Button) view.findViewById(R.id.myfavorite_login_btn); // 로그인 버튼

        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(this.getActivity(), "User");
        String token = sharedPreferencesUtil.getPreferenceString("token");
        if (!TextUtils.isEmpty(token)) {
            myfavorite_login.setText("로그아웃");
            //String token = sharedPreferencesUtil.getPreferenceString("token");
            try {
                JSONObject jObject = JWTUtils.decoded(token);
                comment.setText(jObject.getString("username"));
            } catch (Exception e) {
                Log.e("JSON Decode Error", e.getMessage());
            }
            //TODO 즐겨찾기 목록
            retrofit(view);
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

                        adapter.setData(null);
                        adapter.notifyDataSetChanged();

                    }
                    Log.e("token", sharedPreferencesUtil.getPreferenceString("token")+"끝");
                }
            }
        });

        return view;
    }

    private void retrofit(View view){
        SharedPreferencesUtil spref = new SharedPreferencesUtil(getContext(), "User");
        String token = spref.getPreferenceString("token");

        Call<SearchData> call = RetrofitClient.getApiService().favorite_list("", token);
        call.enqueue((new Callback<SearchData>() {
            @Override
            public void onResponse(Call<SearchData> call, Response<SearchData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.e("Search_List_total", "연결 성공");
                SearchData searchdata = response.body();
                Data[] data = searchdata != null
                        ? searchdata.getData()
                        :new Data[0];

                // 데이터 저장 ArrayList<ResponseData>
                for (int i = 0; i < data.length; i++){
                    String id = data[i].get_id();
                    String Image = data[i].getImage();
                    String Name = data[i].getName();
                    String Score = data[i].getScore();

                    responseData.add(new ResponseData(
                            id,
                            Image,
                            Name,
                            Score
                    ));

                    adapter = new Search_List_Adapter(getContext(), responseData);
                    recyclerView = view.findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                }

                for(ResponseData a : responseData) {
                    Log.e("ResponseData", a.getName());
                }
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<SearchData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        }));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}