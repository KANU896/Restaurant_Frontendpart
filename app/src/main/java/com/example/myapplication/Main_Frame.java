package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Main_Screen.User_Home_Page;
import com.example.myapplication.Map.User_Map_Page;
import com.example.myapplication.Mypage.User_Myfavorite_Page;
import com.example.myapplication.Search_List.search_result_fragment.Search_List_total;
import com.example.myapplication.Search_List.Search_result_frame;
import com.example.myapplication.Search_Page.ResponseData;
import com.example.myapplication.Search_Page.Search_Retrofit.Data;
import com.example.myapplication.Search_Page.Search_Retrofit.RetrofitClient_Search;
import com.example.myapplication.Search_Page.Search_Retrofit.SearchData;
import com.example.myapplication.Search_Page.User_Search_Page;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Frame extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    //private ArrayList<ResponseData> responseData = new ArrayList<>();

    User_Search_Page user_search_page = new User_Search_Page();

    Fragment search_list = new Search_List_total();
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment currentFragment;
    //FragmentManager search_result_FM;
    FragmentTransaction search_result_FT;
    // Fragment search_result_Fragment;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());
                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.navigation_1);
    }

    //BottomNavigation 페이지 변경
    private void BottomNavigate(int id) {
        this.tag = String.valueOf(id);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        currentFragment = fragmentManager.getPrimaryNavigationFragment();

      //  Log.e("currentFragment", String.valueOf(currentFragment));

        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        if(search_result_FT != null){
            search_result_FT.remove(search_list);
          //  Log.e("search_result_FT", String.valueOf(search_result_FT));
        }

        fragment = fragmentManager.findFragmentByTag(tag);
       // Log.e("fragment", String.valueOf(fragment));
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                fragment = new User_Home_Page();
            } else if (id == R.id.navigation_2){
                fragment = new User_Search_Page();
            }else if(id == R.id.navigation_4){
                fragment = new User_Myfavorite_Page();
            }
            else{
                fragment = new User_Map_Page();
            }
            //Log.d("fragment2 : ", String.valueOf(fragment));
            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        }
        else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }
    
    //user_home_page에서 검색 button 클릭 시 검색 nav로 변경
    public void changeFragment(){
        mBottomNV.setSelectedItemId(R.id.navigation_2);
    }

    // ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();

        // 검색 nav 일 때 ActionBar에 SearchView 표시
        if(tag.equals("2131296581")){
            inflater.inflate(R.menu.search_list_menu, menu);
            MenuItem mSearch = menu.findItem(R.id.app_bar_search); // SearchView 있는 res/menu/app_bar_search.xml

            SearchView sv = (SearchView) mSearch.getActionView();
            SearchviewOptions(sv);
            //SearchView 검색 시
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                public boolean onQueryTextSubmit(String query) { // 검색 버튼이 눌러졌을 때 이벤트 처리

                    intent(query);
                    return true;
                }

                public boolean onQueryTextChange(String newText) {
                    // 검색어가 변경되었을 때 이벤트 처리
                    return false;
                }
            });

        }

        //Title만 있는 ActionBar
        else{
            inflater.inflate(R.menu.no_menu, menu);
        }

        return true;
    }

    //ActionBar SearchView 설정
    public void SearchviewOptions(SearchView searchView){
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("검색어를 입력하세요.");
        searchView.setMaxWidth(Integer.MAX_VALUE);
    }

    public void intent (String query){
        Intent intent = new Intent(this, Search_result_frame.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }
}
