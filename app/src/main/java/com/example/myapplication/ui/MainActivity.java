// 작성자 : 김도윤, 송민우, 송준곤
// 메인페이지, 검색, 지도, 마이페이지 4개의 Fragment를 관리하는 메인 Frame
// 담당 : 김도윤 - SearchView, onBackPressed(), 현재 위치 설정
// Update : 22.08.18

package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;
import com.example.myapplication.databinding.UserMainBinding;

public class MainActivity extends AppCompatActivity {
    public UserMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    /**
     * user_home_page에서 검색 button 클릭 시 검색 nav로 변경
     */

    public void changeFragment(){
       binding.navView.setSelectedItemId(R.id.searchFragment);
    }

    /**
     * 뒤로가기 두번 클릭 시 종료
     */
    private long backpressedTime = 0;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backpressedTime + 2000) {
            backpressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
            finish();
        }
    }
}
