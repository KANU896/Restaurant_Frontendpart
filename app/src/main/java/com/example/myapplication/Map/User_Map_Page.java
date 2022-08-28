package com.example.myapplication.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Common.LocationService;
import com.example.myapplication.Common.Location_GPS;
import com.example.myapplication.Common.Location_service;
import com.example.myapplication.Common.RetrofitClient;
import com.example.myapplication.Map.MapData.Map_Data;
import com.example.myapplication.Map.MapData.Map_Datastore;
import com.example.myapplication.Map.MapData.Map_ResponseData;
import com.example.myapplication.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class User_Map_Page extends Fragment implements LocationService {
    private View view;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private MapView mapView;
    private Button search_button;
    private RadioGroup radioGroup;
    double latitude, longitude;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.map_page, container, false);

        search_button = view.findViewById(R.id.around_search_button);
        radioGroup = view.findViewById(R.id.radiogroup);
        Location_service locationService = new Location_service(getContext(), this);

        //위치 퍼미션 확인
        if(locationService.checkPermission()){
            // 위치 설정 활성화 여부 확인
            if(locationService.checkLocationServicesStatus()){
                // 퍼미션 허용 및 위치 설정 on
                mapView = new MapView(getActivity());
                ViewGroup mapViewContainer = view.findViewById(R.id.map_view);
                mapViewContainer.addView(mapView);

                mapView.setZoomLevel(4, true);
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

                Location_GPS locationGps = new Location_GPS(getContext(), getActivity());
                latitude = locationGps.getLatitude();
                longitude = locationGps.getLongitude();

                radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
            }
            else{
                locationService.showDialogForLocationServiceSetting();
            }
        }
        // 퍼미션 거부시 앱 접속 불가능이므로 간단히 Toast만 띄움
        else{
            Toast.makeText(getContext(), "설정(앱 정보)에서 퍼미션을 허용해야 합니다.", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            search_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton button = view.findViewById(checkedId);
                    String category = button.getText().toString();
                    Call<Map_Data> call = RetrofitClient.getApiService().Map(latitude, longitude, category);
                    retrofit(call);

                }
            });
        }
    };

    private void retrofit(Call<Map_Data> call) {
        ArrayList<Map_Datastore> responseData = new ArrayList<>();
        call.enqueue((new Callback<Map_Data>() {
            @Override
            public void onResponse(Call<Map_Data> call, Response<Map_Data> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.e("Search_List_total", "연결 성공");
                Map_Data searchdata = response.body();
                Map_ResponseData[] data = searchdata != null
                        ? searchdata.getData()
                        :new Map_ResponseData[0];

                // 데이터 저장 ArrayList<ResponseData>
                for (int i = 0; i < data.length; i++){
                    String Id = data[i].getRestaurant_id();
                    String Name = data[i].getName();
                    String Address = data[i].getAddress();
                    double latitude = data[i].getLatitude();
                    double longitude = data[i].getLongitude();

                    responseData.add(new Map_Datastore(
                            Id,
                            Name,
                            Address,
                            latitude,
                            longitude
                    ));
                }

                // 주변 음식점 데이터 마커 세팅
                ArrayList<MapPOIItem> markerArr = new ArrayList<>();
                for (Map_Datastore map_datastore : responseData){
                    MapPOIItem marker = new MapPOIItem();
                    marker.setMapPoint(MapPoint.mapPointWithGeoCoord(map_datastore.getLatitude(), map_datastore.getLongitude()));
                    marker.setItemName(map_datastore.getName());
                    markerArr.add(marker);
                }
                mapView.addPOIItems(markerArr.toArray(new MapPOIItem[markerArr.size()]));
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<Map_Data> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        }));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDialogSetClicked(Intent callGPSSettingIntent) {
        startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
    }
}
