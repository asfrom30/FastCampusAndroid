package com.doyoon.android.fastcampusandroid.week5.musicplayer;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.PermissionControl;

public class MusicPlayerMain extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener, PermissionControl.Callback{
    public static String TAG = MusicPlayerMain.class.getName();
    private FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
        PermissionControl.requestAndRunOrNot(this, this, permissions);
    }

    @Override
    public void run() {
        this.setViews();
        this.setFragment(ListFragment.newInstance(1));
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

    /* 이 포지션 파라메터로 통신을 할 것이다. */
    @Override
    public void goDetailInteraction(int position) {
        addFragment(DetailFragment.newInstance(position));
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

    /* Fragment의 배경색을 하얗게 변경하자. */
    private void addFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.music_frame_layout, fragment);
        transaction.addToBackStack(null);    /*이름을 넣어주면 Stack을 찾을수 있다, Fragment는 스택에 쌓이지 않는데, 이 코드를 추가하면 Fragment도 Stack에 쌓여서 뒤로가기 버튼을 누르면 뒤로 간다. */
        transaction.commit();
    }

}
