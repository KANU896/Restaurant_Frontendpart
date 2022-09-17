// 작성자 : 김도윤
// 마이페이지 - 로그인 & 로그아웃과 로그인시 해당 계정 즐겨찾기 목록 보여줌
// Update : 22.08.18

package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Common.JWTUtils;
import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.Adapter.Search_List_Adapter;
import com.example.myapplication.Data.Search.Data;
import com.example.myapplication.Data.Search.ResponseData;
import com.example.myapplication.Data.Search.SearchData;
import com.example.myapplication.databinding.MyfavoritePageBinding;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageFragment extends Fragment {
//    private Button myfavorite_login;
//    private TextView comment;
//    private RecyclerView recyclerView;
    private ArrayList<ResponseData> responseData = new ArrayList<>();
    private Search_List_Adapter adapter;
    private MyfavoritePageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = MyfavoritePageBinding.inflate(getLayoutInflater(), container, false);
        //View view = inflater.inflate(R.layout.myfavorite_page, container, false);
        Log.e("User_Myfavorite_Page", "접속");
        //comment = view.findViewById(R.id.comment);
        //myfavorite_login = (Button) view.findViewById(R.id.myfavorite_login_btn); // 로그인 버튼

        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(this.getActivity(), "User");
        String token = sharedPreferencesUtil.getPreferenceString("token");
        if (!TextUtils.isEmpty(token)) {
            binding.myfavoriteLoginBtn.setText("로그아웃");
            try {
                JSONObject jObject = JWTUtils.decoded(token);
                binding.comment.setText(jObject.getString("username"));
            } catch (Exception e) {
                Log.e("JSON Decode Error", e.getMessage());
            }

            //즐겨찾기 목록 recyclerview adapter 설정
            adapter = new Search_List_Adapter(getContext(), null);
            //recyclerView = view.findViewById(R.id.recyclerview);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recyclerview.setAdapter(adapter);
            retrofit();
        }

        // 버튼 클릭 시
        binding.myfavoriteLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.myfavoriteLoginBtn.getText().equals("로그인")) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else {
                    sharedPreferencesUtil.deletePreference("token");
                    String token = sharedPreferencesUtil.getPreferenceString("token");
                    if (TextUtils.isEmpty(token)) {
                        binding.myfavoriteLoginBtn.setText("로그인");
                        binding.comment.setText("로그인 하시면 나만의 즐겨찾기 관리와\n리뷰 작성을 하실 수 있습니다.");
                        adapter.setData(null);
                        adapter.notifyDataSetChanged();

                    }
                    Log.e("token", sharedPreferencesUtil.getPreferenceString("token")+"끝");
                }
            }
        });

        return binding.getRoot();
    }

    private void retrofit(){
        SharedPreferencesUtil spref = new SharedPreferencesUtil(getContext(), "User");
        //String token = spref.getPreferenceString("token");

        Call<SearchData> call = RetrofitClient.getApiService().favorite_list();
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
                    for(ResponseData r : responseData){
                        Log.e("ResponseData", r.getName());
                    }
                    adapter.setData(responseData);
                    adapter.notifyDataSetChanged();
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