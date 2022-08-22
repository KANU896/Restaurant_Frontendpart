package com.example.myapplication.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import net.daum.mf.map.api.MapView;


public class User_Map_Page extends Fragment /*implements OnMapReadyCallback*/ {

    @Nullable
    public void onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_page);

        MapView mapView = new MapView(getContext());

        View root = inflater(R.layout.map_page, container, false);
        //RelativeLayout mapViewContainer = (RelativeLayout) findViewById(R.id.MapView);
        ViewGroup mapViewContainer = (ViewGroup)root.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
    }

    private View inflater(int map_page, ViewGroup container, boolean b) {
        return null;
    }

    private void setContentView(int map_page) {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
