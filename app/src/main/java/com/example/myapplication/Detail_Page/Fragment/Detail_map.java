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
import com.example.myapplication.Detail_Page.Detail_Data.Detail_Datastore;
import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Detail_map extends Fragment {
    private View view;
    private GoogleMap mMap;
    private Detail_Datastore responseData;
    private double x, y;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_map, container,false);
        responseData = (Detail_Datastore) getArguments().getSerializable("responseData");
        Log.e("Detail_Map", responseData.getAddress());

        // 카카오맵 지도 (에뮬레이터랑 호환 안되서 평소엔 주석처리)
        MapView mapView = new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);

        if (responseData.getAddress().isEmpty()){
            View map = view.findViewById(R.id.map);
            map.setVisibility(View.INVISIBLE);
            return view;
        }

        Location_GPS location_gps = new Location_GPS(getContext(), null);

        x = location_gps.getLatitude();
        y = location_gps.getLongitude();

        mapViewContainer.addView(mapView);

        MapPOIItem marker = new MapPOIItem();
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(x, y);
        marker.setItemName(responseData.getName());
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
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