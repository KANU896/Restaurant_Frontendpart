package com.example.myapplication.Common;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import androidx.core.content.ContextCompat;

public class Location_service {
    private Context mContext;
    private LocationService locationService;

    public Location_service(Context context, LocationService locationService){
        this.mContext = context;
        this.locationService = locationService;
    }

    //퍼미션 확인
    public boolean checkPermission() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
            return true;
        }

        return false;
    }

    //위치 서비스 상태 확인
    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //GPS 활성화를 위한 메소드
    public void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("해당 기능을 사용하기 위해서는 위치 서비스가 필요합니다.");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                locationService.onDialogSetClicked(callGPSSettingIntent);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
