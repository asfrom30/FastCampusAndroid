package com.doyoon.android.fastcampusandroid.week3.camerapermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.Const;
import com.doyoon.android.fastcampusandroid.util.DeviceUtil;
import com.doyoon.android.fastcampusandroid.util.PermissionControl;

public class CameraActivityMain extends AppCompatActivity implements View.OnClickListener, PermissionControl.Callback{

    public static String TAG = CameraActivityMain.class.getName();

    Uri fileUri;

    ImageView imageView;
    Button btnCaptuer;

    // URI를 사용해서 자원을 사용하려면 파일이 URI에 자원등록이 되어 있어야 한다.
    // 파일만 있고 Uri에 등록되지 앟으면 자원에 접근할 수 없다.
    // 파일 그냥 읽고 쓰는것은 storage 권한만 있으면된다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_sub);

        this.setView();
        this.setLisntener();
        this.btnCaptuer.setEnabled(false);

        String permssions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        PermissionControl.requestAndRunOrNot(this, this, permssions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.postPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        this.fileUri = DeviceUtil.Camera.takePhoto(this, "CameraPath", Const.Device.REQ_CODE_CAMERA);
    }

    /* Above the Lollipop version, Capture Camera Intent be returned as null(Intent is empty)
         * That's why FileURI set to be global variable and call Image using this variable */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.Device.REQ_CODE_CAMERA) {
            if (resultCode == RESULT_OK) {
                Uri imageUri = null;
                // 롤리팝 이전 버전에서는 이렇게 하면 됐다.
                // 롤리팝 미만 버전에서는 data 인텐트에 찍은 사진의 uri가 담겨온다.
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    imageUri = data.getData(); // Return URI
                } else {
                    imageUri = fileUri;
                }
                imageView.setImageURI(imageUri);
                // 카메라를 찍는 것은 문제가 되지 않는데... 저장할때 외부 스토리지 사용해야 되서..
                // 모두 접근이 되어 버린다.
                // 특정 폴더에만 접근하는 권한 file provider

                /* Update Gallery */
                /*
                MediaScannerConnection.scanFile(this, new String[] { photoFile.getAbsolutePath() }, null,new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
                */
            }
        }
    }

    @Override
    public void run() {
        this.btnCaptuer.setEnabled(true);
    }

    @Override
    public void cancle() {
//        this.finish();
    }

    private void setView(){
        this.imageView = (ImageView) findViewById(R.id.cameraActivity_main_imageView);
        this.btnCaptuer = (Button) findViewById(R.id.cameraActivity_main_btn);
    }

    private void setLisntener(){
        btnCaptuer.setOnClickListener(this);
    }
}
