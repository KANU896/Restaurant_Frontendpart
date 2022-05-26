import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Restourant_Keyword;
import com.example.myapplication.Today_Restourant;

public class User_Home_Page2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button today_keyword = (Button) findViewById(R.id.today_keword); // 실시간 맛집 키워드 버튼
        Button today_restourant = (Button) findViewById(R.id.today_restourant); // 오늘의 맛집 버튼

        // 메인화면 -> 실시간 맛집 키워드 화면 전환
        today_keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Restourant_Keyword.class);
                startActivity(intent);
            }
        });

        // 메인화면 -> 오늘의 맛집 화면 전환
        today_restourant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Today_Restourant.class);
                startActivity(intent);
            }
        });
    }
}