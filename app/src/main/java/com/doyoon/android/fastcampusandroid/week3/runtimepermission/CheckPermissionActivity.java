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
import com.doyoon.android.fastcampusandroid.util.PermissionUtil;


public class CheckPermissionActivity extends AppCompatActivity {

    private final int REQ_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permission);

        /* 버전 호환성 체크 */
        // 설치 안드로이드폰의 api level 가져오기, // api level이 23이상일 경우만 실행
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ // 마시멜로우 이상이면 이니셜만 써도 된다. LOLIPOP이면 .LOLIPOP
            String[] permissions = {Manifest.permission.READ_CONTACTS};
            boolean hasPermission = PermissionUtil.hasPermissionsAndRequestIfNot(this, permissions, REQ_PERMISSION);
            if(hasPermission){
                run();
            } else {
                // Nothing to do...
            }
        } else {
            run();
        }
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
        if(requestCode == REQ_PERMISSION){
            // 3.1 사용자가 승인을 했음.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                run();
            // 3.2 사용자가 거절 했음.
            } else {
                cancle();
            }
        }
    }

    public void run(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    public void cancle(){
        Toast.makeText(this, "권한요청을 승인하셔야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}

