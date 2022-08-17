// 작성자 : 김도윤
// 음식점 상세정보 페이지
// Update : 22.08.18

package com.example.myapplication.Detail_Page;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Common.JWTUtils;
import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Common.SharedPreferencesUtil;
import com.example.myapplication.R;
import com.example.myapplication.Search_List.ImageLoadTask;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_page extends AppCompatActivity {
    private Detail_ResponseData responseData;
    private TextView detail_title, detail_menu, detail_tag, detail_score, detail_address;
    private ImageView imageView;
    private JSONObject jObject = null;
    double x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        Intent intent = getIntent();
        responseData = (Detail_ResponseData) intent.getSerializableExtra("responseData");

        Log.e("Detail_page", responseData.getTag());

        detail_title = findViewById(R.id.detail_title);
        detail_score = findViewById(R.id.detail_score);
        imageView = findViewById(R.id.detail_image);

        //정적 데이터 설정
        detail_title.setText(responseData.getName());
        detail_score.setText(responseData.getScore());
        new ImageLoadTask(responseData.getImage(), imageView).execute();

        //TODO 즐겨찾기 기능
        CheckBox favorite = findViewById(R.id.favorite);
        SharedPreferencesUtil spref = new SharedPreferencesUtil(getApplicationContext(), "User");
        favorite.setChecked(responseData.getFav()); //해당 계정 즐겨찾기에 추가되어 있으면 체크 된 상태
        String token = spref.getPreferenceString("token");
        if(!TextUtils.isEmpty(token)){
            jObject = JWTUtils.decoded(token);
        }

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = null;
                if (!TextUtils.isEmpty(token)) {
                    try {
                        username = jObject.getString("username");
                    } catch (JSONException e) {
                        Log.e("JSON ERROR", e.getMessage());
                    }
                    // DB에 추가
                    if (favorite.isChecked()) {
                        Call<Void> call = RetrofitClient.getApiService().favorite_put(responseData.getId(), username, token);
                        retrofit(call);
                        Log.e("CheckBox", "check" + username);
                    }
                    // DB에서 삭제
                    else {
                        Call<Void> call = RetrofitClient.getApiService().favorite_delete(responseData.getId(), username, token);
                        retrofit(call);
                        Log.e("CheckBox", "uncheck" + username);
                    }
                }
                else{
                    favorite.setChecked(false);
                    Toast.makeText(getApplicationContext(), "로그인 후 이용할 수 있습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //TODO 메뉴
        LinearLayout menu_layout = findViewById(R.id.menu_layout);
        Button menu_button = findViewById(R.id.menubutton);

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) menu_button.getText();

                // 펼치기
                if (text.equals("+")){
                    menu_button.setText("-");
                    menu_layout.setVisibility(View.VISIBLE);
                }
                // 접기
                else{
                    menu_button.setText("+");
                    menu_layout.setVisibility(View.GONE);
                }
            }
        });

        //TODO 위치
        detail_address = findViewById(R.id.detail_address);
        Button location_button = findViewById(R.id.locationbutton);
        LinearLayout location_layout = findViewById(R.id.location_layout);
        String address = responseData.getAddress();
        if(!address.equals(""))
            detail_address.setText(address);
        else
            detail_address.setText("찾을 수 없습니다.");

        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) location_button.getText();

                // 펼치기
                if (text.equals("+")){
                    location_button.setText("-");
                    location_layout.setVisibility(View.VISIBLE);
                }
                // 접기
                else{
                    location_button.setText("+");
                    location_layout.setVisibility(View.GONE);
                }
            }
        });

//        // 지도 (에뮬레이터랑 호환 안되서 평소엔 주석처리)
//        MapView mapView = new MapView(this);
//        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
//
//        //주소로 좌표 추출
//        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
//
//        try{
//            List<Address> addresses = geocoder.getFromLocationName(address,3);
//            StringBuffer buffer= new StringBuffer();
//            for(Address t : addresses){
//                buffer.append(t.getLatitude()+", "+t.getLongitude()+"\n");
//            }
//            String[] coordinate = buffer.toString().split(", ");
//            x = Double.parseDouble(coordinate[0]);
//            y = Double.parseDouble(coordinate[1]);
//
//            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(x, y), 2, true);
//
//        }
//        catch (IOException e) {
//            Toast.makeText(this, "검색 실패", Toast.LENGTH_SHORT).show();
//        }
//
//        mapViewContainer.addView(mapView);
//
//        MapPOIItem marker = new MapPOIItem();
//        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(x, y);
//        marker.setItemName("Default Marker");
//        marker.setTag(0);
//        marker.setMapPoint(MARKER_POINT);
//        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//        mapView.addPOIItem(marker);

        //TODO TAG
        detail_tag = findViewById(R.id.detail_tag);
        Button tag_button = findViewById(R.id.tagbutton);
        LinearLayout tag_layout = findViewById(R.id.tag_layout);
        detail_tag.setText(responseData.getTag());

        tag_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) tag_button.getText();

                // 펼치기
                if (text.equals("+")){
                    tag_button.setText("-");
                    tag_layout.setVisibility(View.VISIBLE);
                }
                // 접기
                else{
                    tag_button.setText("+");
                    tag_layout.setVisibility(View.GONE);
                }
            }
        });
    }

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
