package com.doyoon.android.fastcampusandroid.week5.musicplayer;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.PermissionControl;
import com.doyoon.android.fastcampusandroid.util.PermissionUtil;
import com.doyoon.android.fastcampusandroid.week5.musicplayer.dummy.DummyContent;

public class MusicPlayerMain extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener, PermissionControl.Callback, PermissionUtil.CallBack {
    public static String TAG = MusicPlayerMain.class.getName();
    private FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
        PermissionControl.requestAndRunOrNot(this, this, permissions);
        //PermissionUtil.checkVersion(this);
    }

    @Override
    public void init() {
        this.setViews();
        this.setFragment(ListFragment.newInstance(1));
        Log.e("================", "init");
    }

    @Override
    public void run() {
        this.setViews();
        this.setFragment(ListFragment.newInstance(1));
        Log.e("================", "run");
    }

    public void cancle(){
        Toast.makeText(this, "권한요청을 승인하셔야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.postPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    private void setViews(){
        layout = (FrameLayout) findViewById(R.id.music_frame_layout);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.music_frame_layout, fragment);
        transaction.commit();
    }


}
