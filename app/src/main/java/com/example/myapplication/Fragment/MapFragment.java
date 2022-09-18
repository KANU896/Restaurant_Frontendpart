package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.Data.MapArrayData;
import com.example.myapplication.Data.Map_ResponseData;
import com.example.myapplication.databinding.MapPageBinding;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapFragment extends Fragment implements LocationService {
    //private View view;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private MapView mapView;
    //private Button search_button;
    //private RadioGroup radioGroup;
    double latitude, longitude;
    private MapPageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = MapPageBinding.inflate(getLayoutInflater(), container, false);
        //view = inflater.inflate(R.layout.map_page, container, false);

//        search_button = view.findViewById(R.id.around_search_button);
//        radioGroup = view.findViewById(R.id.radiogroup);
        Location_service locationService = new Location_service(getContext(), this);

        //위치 퍼미션 확인
        if(locationService.checkPermission()){
            // 위치 설정 활성화 여부 확인
            if(locationService.checkLocationServicesStatus()){
                // 퍼미션 허용 및 위치 설정 on
                mapView = new MapView(getActivity());
                //ViewGroup mapViewContainer = view.findViewById(R.id.map_view);
                //mapViewContainer.addView(mapView);
                binding.mapView.addView(mapView);

                mapView.setZoomLevel(4, true);
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

                Location_GPS locationGps = new Location_GPS(getContext(), getActivity());
                latitude = locationGps.getLatitude();
                longitude = locationGps.getLongitude();

                binding.radiogroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
            }
            else{
                locationService.showDialogForLocationServiceSetting();
            }
        }
        // 퍼미션 거부시 앱 접속 불가능이므로 간단히 Toast만 띄움
        else{
            Toast.makeText(getContext(), "설정(앱 정보)에서 퍼미션을 허용해야 합니다.", Toast.LENGTH_LONG).show();
        }

        return binding.getRoot();
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            binding.aroundSearchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton button = binding.getRoot().findViewById(checkedId);
                    String category = button.getText().toString();
                    Call<MapArrayData> call = RetrofitClient.getApiService().Map(latitude, longitude, category);
                    retrofit(call);

                }
            });
        }
    };

    private void retrofit(Call<MapArrayData> call) {
        ArrayList<Map_ResponseData> responseData = new ArrayList<>();
        call.enqueue((new Callback<MapArrayData>() {
            @Override
            public void onResponse(Call<MapArrayData> call, Response<MapArrayData> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.e("Search_List_total", "연결 성공");
                MapArrayData searchdata = response.body();
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

                    responseData.add(new Map_ResponseData(
                            Id,
                            Name,
                            Address,
                            latitude,
                            longitude
                    ));
                }

                // 주변 음식점 데이터 마커 세팅
                ArrayList<MapPOIItem> markerArr = new ArrayList<>();
                for (Map_ResponseData map_datastore : responseData){
                    MapPOIItem marker = new MapPOIItem();
                    marker.setMapPoint(MapPoint.mapPointWithGeoCoord(map_datastore.getLatitude(), map_datastore.getLongitude()));
                    marker.setItemName(map_datastore.getName());
                    markerArr.add(marker);
                }
                mapView.addPOIItems(markerArr.toArray(new MapPOIItem[markerArr.size()]));
            }

            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<MapArrayData> call, Throwable t) {
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
