// 작성자 : 김도윤
// 검색 결과 페이지
// Update : 22.08.18

package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Data.SearchedList;
import com.example.myapplication.Data.StoreArrayData;
import com.example.myapplication.Data.StoreResponseData;
import com.example.myapplication.Fragment.SearchResult.Alcohol;
import com.example.myapplication.Fragment.SearchResult.Cafe;
import com.example.myapplication.Fragment.SearchResult.Food;
import com.example.myapplication.Fragment.SearchResult.Total;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySearchResultBinding;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.SharedPreferencesUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {
    private static final String TAG = "Search_result_frame";

    //객체 선언
    private Total total;
    private Food food;
    private Alcohol alcohol;
    private Cafe cafe;
    private Fragment selected = null;

    private SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(this, "Searched");
    private String query;
    private ArrayList<SearchedList> searchHistoryList;
    private String location = null;

    private ActivitySearchResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.searchResultToolbar);
        Log.d(TAG, "onCreate");

        // 데이터 받기
        Intent intent = getIntent();
        this.query = intent.getStringExtra("query");

        binding.searchResultToolbar.setTitle(query);
        //내 현재위치
        String address = sharedPreferencesUtil.getPreferenceString("location");
        if (!TextUtils.isEmpty(address) && !address.equals("위치 정보 없음")) {
            this.location = address;
        }

        //프래그먼트 초기화
        total = new Total();
        food = new Food();
        alcohol = new Alcohol();
        cafe = new Cafe();
        selected = total;

        // 서버 통신
        SearchResultRetrofit(selected, query, null, 0);

        //탭 만들기 (동적)
        binding.tabs.addTab(binding.tabs.newTab().setText("전체"));
        binding.tabs.addTab(binding.tabs.newTab().setText("밥집"));
        binding.tabs.addTab(binding.tabs.newTab().setText("술집"));
        binding.tabs.addTab(binding.tabs.newTab().setText("카페"));

        //탭이 선택되었을때 작동하는 메서드
        binding.tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.e(TAG, "선택된 탭 : " + position);

                if (position == 0) {
                    selected = total;
                    SearchResultRetrofit(selected, query, null, position);
                }
                else if (position == 1) {
                    selected = food;
                    SearchResultRetrofit(selected, query, "밥집", position);
                }
                else if (position == 2) {
                    selected = alcohol;
                    SearchResultRetrofit(selected, query, "술집", position);
                }
                else if (position == 3) {
                    selected = cafe;
                    SearchResultRetrofit(selected, query, "카페", position);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater( );
        inflater.inflate(R.menu.search_list_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.app_bar_search);
        SearchView sv = (SearchView) mSearch.getActionView();
        ActionBar ab = getSupportActionBar();
        ab.setTitle(query);

        sv.setQueryHint("검색어를 입력하세요.");
        sv.setMaxWidth(Integer.MAX_VALUE);

        //SearchView 검색 시
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) { // 검색 버튼이 눌러졌을 때 이벤트 처리
                finish();//인텐트 종료
                overridePendingTransition(0, 0);//인텐트 효과 없애기
                searchHistoryList = (ArrayList<SearchedList>) sharedPreferencesUtil.getSearchHistoryList();
                searchHistoryList.add(new SearchedList(query));
                sharedPreferencesUtil.storeSearchHistoryList(searchHistoryList);
                intent(query);
                overridePendingTransition(0, 0);//인텐트 효과 없애기
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경되었을 때 이벤트 처리
                return false;
            }
        });

        return true;
    }
    public void intent (String query){
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

    public void SearchResultRetrofit (Fragment selected, String query, String category, int position){
        ArrayList<StoreResponseData> storeResponseData = new ArrayList<>();
        Bundle bundle = new Bundle();

        Call<StoreArrayData> call = RetrofitClient.getApiService().Search_post(query, category, location);
        call.enqueue((new Callback<StoreArrayData>() {
            @Override
            public void onResponse(Call<StoreArrayData> call, Response<StoreArrayData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.e("Search_List_total", "연결 성공");
                StoreArrayData searchdata = response.body();

                StoreResponseData[] data = searchdata != null
                        ? searchdata.getData()
                        : new StoreResponseData[0];

                // 데이터 저장 ArrayList<ResponseData>
                for (int i = 0; i < data.length; i++){
                    String id = data[i].getId();
                    String City = data[i].getCity();
                    String Address = data[i].getAddress();
                    String Name = data[i].getName();
                    String Tell = data[i].getTell_number();
                    String Score = data[i].getScore();
                    String Category = data[i].getCategory();
                    String Tag = data[i].getTag();
                    Boolean fav = false; //data[i].getFav();

                    storeResponseData.add(new StoreResponseData(
                            id,
                            City,
                            Address,
                            Name,
                            Tell,
                            Score,
                            Category,
                            Tag,
                            fav
                    ));
                }

                bundle.putSerializable("responseData", storeResponseData);
                selected.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.contatiner, selected).commit();
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<StoreArrayData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        }));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}