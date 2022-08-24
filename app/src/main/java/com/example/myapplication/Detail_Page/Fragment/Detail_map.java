package com.example.myapplication.Detail_Page.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Common.Location_GPS;
import com.example.myapplication.Detail_Page.Detail_Data.Detail_ResponseData;
import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Detail_map extends Fragment implements OnMapReadyCallback {
    private View view;
    private GoogleMap mMap;
    private Detail_ResponseData responseData;
    private double x, y;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_map, container,false);
        responseData = (Detail_ResponseData) getArguments().getSerializable("responseData");
        Log.e("Detail_Map", responseData.getAddress());

        if (responseData.getAddress().isEmpty()){
            View map = view.findViewById(R.id.map);
            map.setVisibility(View.INVISIBLE);
            return view;
        }

        Location_GPS location_gps = new Location_GPS(getContext(), null);
        String [] coordinate = location_gps.getCoordinate(responseData.getAddress());

        x = Double.parseDouble(coordinate[0]);
        y = Double.parseDouble(coordinate[1]);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng restaurant_location = new LatLng(x, y);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(restaurant_location);
        markerOptions.title(responseData.getAddress());
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurant_location, 13));
    }
}
//        // 카카오맵 지도 (에뮬레이터랑 호환 안되서 평소엔 주석처리)
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