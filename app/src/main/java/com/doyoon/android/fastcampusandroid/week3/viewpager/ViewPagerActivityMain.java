package com.doyoon.android.fastcampusandroid.week3.viewpager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivityMain extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Fragment fragmentA, fragmentB, fragmentC, fragmentD;

    LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


        // 프래그먼트에서 실행할 수도 있지만, 액티비티에서 한번만 생성하기 위해서 여기서 생성한다.
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        viewPager = (ViewPager) findViewById(R.id.viewpager_viewpager);
        /*Tab Layout */
        tabLayout = (TabLayout) findViewById(R.id.viewpager_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("One"));
        tabLayout.addTab(tabLayout.newTab().setText("TWo"));
        tabLayout.addTab(tabLayout.newTab().setText("Three"));
        tabLayout.addTab(tabLayout.newTab().setText("Four"));

        fragmentA = new Fragment_A();
        fragmentB = new Fragment_B();
        fragmentC = new Fragment_C();
        fragmentD = new Fragment_D();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragmentA);
        fragmentList.add(fragmentB);
        fragmentList.add(fragmentC);
        fragmentList.add(fragmentD);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);

        /* Tab Layout Listener */
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }

    public void getLocationPermission(){
        String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            PermissionUtil.hasPermissionsAndRequestIfNot(this, permissions, 101);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // 권한이 있으면 그냥 진행...
        } else {

        }

    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragmentList;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    public LocationManager getManager() {
        return manager;
    }
}
