package com.doyoon.android.fastcampusandroid.week3.runtimepermission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.PermissionControl;

public class CheckPermissionActivity extends AppCompatActivity implements PermissionControl.Callback {

    private final int REQ_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permission);


        String[] permissions = {Manifest.permission.READ_CONTACTS};
        PermissionControl.requestAndRunOrNot(this, this, permissions);
    }

    /* Annotation */
    @TargetApi(Build.VERSION_CODES.M)   /* 해당함수는 마쉬멜로우 버전 이상에서만 실행이 됩니다. */
    private void checkPermission(){

        // API Level이 23레벨 이상일때만 실행

        // 1. 권한체크 - 특정권한이 있는지 시스템에 물어본다.
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED){
            run();
        } else {
            // 2. 권한이 없으면 사용자에 권한을 달라고 요청
            String permissions[] = {Manifest.permission.READ_CONTACTS};
            requestPermissions(permissions, REQ_PERMISSION); // -> 권한을 요구하는 팝업이 사용자 화면에 요청된다.
        }
    }

    // 3. 사용자가 권한체크 팝업에서 권한을 승인 또는 거절하면 아래 메서드가 호출된다.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.postPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void run(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    @Override
    public void cancle(){
        Toast.makeText(this, "권한요청을 승인하셔야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}

