package com.example.myapplication.Common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.myapplication.Main_Frame;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location_GPS {
    Context mContext;
    Activity activity;
    double longitude, latitude;

    public Location_GPS(Context context, Main_Frame activity) {
        this.mContext = context;
        this.activity = activity;
    }

    public String get_address() {
        Log.e("Location_GPS", "get_address");
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0 );
        }
        else{
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                String provider = location.getProvider();
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }

            // 위치정보를 원하는 시간, 거리마다 갱신해준다.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);

            return change_address(longitude, latitude);

        }
//        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            // 위치정보 설정 Intent
//            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        //}
        return "";
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // 위치 리스너는 위치정보를 전달할 때 호출되므로 onLocationChanged()메소드 안에 위지청보를 처리를 작업을 구현 해야합니다.
            String provider = location.getProvider();  // 위치정보
            longitude = location.getLongitude(); // 위도
            latitude = location.getLatitude(); // 경도
        } public void onStatusChanged(String provider, int status, Bundle extras) {

        } public void onProviderEnabled(String provider) {

        } public void onProviderDisabled(String provider) {

        }
    };

    public String change_address(double lonogitude, double latitude){
        Log.e("Location_GPS_TEST", "test");
        Geocoder geocoder= new Geocoder(mContext, Locale.KOREA);

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, lonogitude,1);

            StringBuffer buffer= new StringBuffer();
            for(Address t : addresses){
                buffer.append(t.getAddressLine(0)+"\n"); //주소 1
                //buffer.append(t.getAddressLine(1)+"\n"); //주소 2 - 없으면 null
                //buffer.append(t.getAddressLine(2)+"\n"); //주소 3 - 없으면 null
            }
            Log.e("Location_GPS", buffer.toString());
            return buffer.toString();
        } catch (IOException e) {
            Toast.makeText(mContext, "검색 실패", Toast.LENGTH_SHORT).show();
            return "검색실패";
        }
    }
}