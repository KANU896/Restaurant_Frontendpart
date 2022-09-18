// 작성자 : 김도윤
// 음식점 상세정보 페이지
// Update : 22.08.18

package com.example.myapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Data.StoreResponseData;
import com.example.myapplication.Fragment.Detail.Detail_Info;
import com.example.myapplication.Fragment.Detail.Detail_Review;
import com.example.myapplication.Fragment.Detail.Detail_map;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDetailPageBinding;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.SharedPreferencesUtil;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private String TAG = "Detail_page";

    //객체 선언
    private Detail_Info detail_info;
    private Detail_Review detail_review;
    private Detail_map detail_map;
    private Fragment selected = null;
    private StoreResponseData responseData;
    private ActivityDetailPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("DetailActivity", "onCrete");
        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        responseData = (StoreResponseData) intent.getSerializableExtra("responseData");

        Log.e(TAG, responseData.getTag());

        //정적 데이터 설정
        binding.detailTitle.setText(responseData.getName());
        binding.detailScore.setText(responseData.getScore());
        //new ImageLoadTask(responseData.getImage(), binding.detailImage).execute();

        //전화 버튼 클릭 시 핸드폰 전화 기능으로 전환
        Uri number = Uri.parse("tel:"+responseData.getTell_number());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        binding.tellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(callIntent);
            }
        });


        //즐겨찾기 기능

        SharedPreferencesUtil spref = new SharedPreferencesUtil(getApplicationContext(), "User");
        binding.favorite.setChecked(responseData.getFav()); //해당 계정 즐겨찾기에 추가되어 있으면 체크 된 상태
        String token = spref.getPreferenceString("token");

        binding.favorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(token)) {
                    if (binding.favorite.isChecked()) {
                        binding.favorite.setChecked(false);
                        Call<Void> call = RetrofitClient.getApiService().favorite_delete(responseData.getId());
                        retrofit(call);
                    } else {
                        binding.favorite.setChecked(true);
                        Call<Void> call = RetrofitClient.getApiService().favorite_put(responseData.getId());
                        retrofit(call);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "로그인 후 이용할 수 있습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //프래그먼트 초기화
        detail_info = new Detail_Info();
        detail_review = new Detail_Review();
        detail_map = new Detail_map();
        selected = detail_info;

        Bundle bundle = new Bundle();

        //Tab 설정
        binding.tabs.addTab(binding.tabs.newTab().setText("정보"));
        binding.tabs.addTab(binding.tabs.newTab().setText("지도"));
        binding.tabs.addTab(binding.tabs.newTab().setText("리뷰"));

        //첫 화면(프레그먼트) 세팅
        bundle.putSerializable("responseData", responseData);
        selected.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contatiner, selected).commit();

        //탭이 선택되었을때 작동하는 메서드
        binding.tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //scrollView.fullScroll(ScrollView.FOCUS_UP);
                int position = tab.getPosition();
                Log.e(TAG, "선택된 탭 : " + position);

                if (position == 0) {
                    selected = detail_info;
                    bundle.putSerializable("responseData", responseData);
                    selected.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.contatiner, selected).commit();
                }
                else if (position == 1) {
                    selected = detail_map;
                    bundle.putSerializable("responseData", responseData);
                    selected.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.contatiner, selected).commit();
                }
                else if (position == 2) {
                    selected = detail_review;
                    bundle.putString("restaurant_id", responseData.getId());
                    selected.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.contatiner, selected).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 즐겨찾기용 서버 통신
    public void retrofit (Call<Void> call){
        call.enqueue((new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
            }
            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(getApplicationContext(), "연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
