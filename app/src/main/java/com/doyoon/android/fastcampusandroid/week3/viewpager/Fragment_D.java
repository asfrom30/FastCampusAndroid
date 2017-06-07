package com.doyoon.android.fastcampusandroid.week3.viewpager;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doyoon.android.fastcampusandroid.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_D extends Fragment implements OnMapReadyCallback{


    public Fragment_D() {
        // Required empty public constructor
    }

    LocationManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragment_d, container, false);
        // 프래그먼트 map(아이디) 호출하기
        // 프래그먼트 안에 프래그먼트를 넣어둔 형태인데... 그래서 findViewById의 형태가 조금 다르다...
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this); // 맵이 사용할 준비가 되면 밑에 OnMap함수를 사용해 주렴..

        // MainActivity를 바로 꺼내 쓸 수 있다..
        // 상호참조를 통해서 강한 커플링이 일어나도, 인터페이스를 사용하면 강한 커플링이 일어나는 것을 막을 수 있다....
        // TODO 인터페이스를 참조해서 상호참조하라는 이야기인가..
        ViewPagerActivityMain viewPagerActivityMain =  (ViewPagerActivityMain) getActivity();
        manager = viewPagerActivityMain.getManager();

        return view;
    }
    GoogleMap map;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // 좌표만생성
        LatLng sinsa = new LatLng(37.516066, 127.109361);
        // 좌표를 적용
        // 1. 마커를 화면에 그린다.
        MarkerOptions marker = new MarkerOptions();
        marker.position(sinsa);
        marker.title("신사");
        googleMap.addMarker(marker);
        // 2. 맵의 중심을 해당 좌표로 이동시킨다.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sinsa, 17));

    }

    // GPS 사용을 위해서 Listener를 생성
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // 경도
            double lng = location.getLongitude();
            // 위도
            double lat = location.getLatitude();
            // 고도
            double alt = location.getAltitude();
            // 정확도
            float acc = location.getAccuracy();
            // 위치 제공자
            String provider = location.getProvider();

            // 바뀐 현재 좌표
            LatLng current = new LatLng(lat, lng);

            // 현재 좌표로 카메라를 이동.
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 17));

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    /*
        액티비티 생명주기를 이용해야 한다.
        리스너는 항상 동작하는것이 아니라... 프래그먼트가 4로 왔을때 동작하고 4를 벗어나면 중지
    */
    @Override
    public void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // GPS 사용을 위한 권한 획득이 되어 있지 않으면 리스너 등록을 하지 않는다.
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return; // 리턴하면 리스너 등록 안한다.
            }
        }

        // 리스너는 로케이션 매니저를 통해서 등록할 수 있다.
        // 권한처리를 해야 한다.
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 위치제공자
                3000,   // 변경사항 체크 주기 milisecond
                1,      // 변경사항 체크 주기 meter
                locationListener    // On Location Changed를 호출한다.
        );
    }

    @Override
    public void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // GPS 사용을 위한 권한 획득이 되어 있지 않으면 리스너를 해제하지 않는다.
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return; // 리턴하면 리스너 등록 안한다.
            }
        }

        manager.removeUpdates(locationListener);
    }
}
