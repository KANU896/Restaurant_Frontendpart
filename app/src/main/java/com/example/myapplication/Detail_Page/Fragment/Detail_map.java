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
import com.example.myapplication.databinding.DetailMapBinding;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Detail_map extends Fragment {
    //private View view;
    private Detail_Datastore responseData;
    private double x, y;
    private DetailMapBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailMapBinding.inflate(getLayoutInflater(), container, false);
        //view = inflater.inflate(R.layout.detail_map, container,false);

        responseData = (Detail_Datastore) getArguments().getSerializable("responseData");
        Log.e("Detail_Map", responseData.getAddress());

        if (responseData.getAddress().isEmpty()){
            //View map = view.findViewById(R.id.detail_map);
            binding.detailMap.setVisibility(View.INVISIBLE);
            return binding.getRoot();
        }

        Location_GPS location_gps = new Location_GPS(getContext(), null);
        String [] coordinate = location_gps.getCoordinate(responseData.getAddress());

        x = Double.parseDouble(coordinate[0]);
        y = Double.parseDouble(coordinate[1]);


        // 카카오맵 지도 (에뮬레이터랑 호환 안되서 평소엔 주석처리)
        MapView mapView = new MapView(getActivity());
        //ViewGroup mapViewContainer = view.findViewById(R.id.detail_map);
        binding.detailMap.addView(mapView);

        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(x, y), true);

        MapPOIItem marker = new MapPOIItem();
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(x, y);
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}