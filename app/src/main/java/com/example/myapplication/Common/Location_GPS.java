// 작성자 : 김도윤
// 위치서비스 on 일 때, 현재 위치를 위도와 경도로 얻고 이를 주소로 바꿔주는 클래스
// Update : 22.08.18

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
    private Context mContext;
    private Activity activity;
    private double longitude, latitude;
    private Location location;
    private Geocoder geocoder;

    public Location_GPS(Context context, Main_Frame activity) {
        this.mContext = context;
        this.activity = activity;
    }

    public String get_address() {
        Log.e("Location_GPS", "get_address");
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
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

            return change_address(latitude, longitude);

        }
        return "위치 정보 없음";
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

    public String change_address(double latitude, double lonogitude){
        Log.e("Location_GPS_TEST", "test");
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(mContext, "Searched");
        geocoder= new Geocoder(mContext, Locale.KOREA);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, lonogitude,1);

        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(mContext, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(mContext, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(mContext, "주소 미발견", Toast.LENGTH_LONG).show();
            sharedPreferencesUtil.setPreference("location", "위치 정보 없음");
            return "위치 정보 없음";

        } else {
            Address address = addresses.get(0);
            sharedPreferencesUtil.setPreference("location", address.getAddressLine(0).toString());
            return address.getAddressLine(0).toString();
        }
    }

    public String [] getCoordinate(String address){
        //주소로 좌표 추출
        geocoder = new Geocoder(mContext, Locale.KOREA);
        String [] coordinate = null;
        try{
            List<Address> addresses = geocoder.getFromLocationName(address,3);
            StringBuffer buffer= new StringBuffer();
            for(Address t : addresses){
                buffer.append(t.getLatitude()+", "+t.getLongitude()+"\n");
            }
            coordinate = buffer.toString().split(", ");
            return coordinate;
        }
        catch (IOException e) {
            Toast.makeText(mContext, "검색 실패", Toast.LENGTH_SHORT).show();
            return null;
        }

    }

}
