package com.doyoon.android.fastcampusandroid.week3.camerapermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.PermissionControl;

public class GalleryActivityMain extends AppCompatActivity implements View.OnClickListener, PermissionControl.Callback{

    public static final int REQ_PERMISSION = 100;

    Button btnGallery, btnCamera;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnGallery = ((Button) findViewById(R.id.camera_btn_gallery));
        btnCamera = ((Button) findViewById(R.id.camera_btn_camera));
        imageView = ((ImageView) findViewById(R.id.camera_imageView));

        btnCamera.setOnClickListener(this);
        btnGallery.setOnClickListener(this);

        // 버튼 두개를 사용 못하개 막아둔다.(권한이 없을때...)
        btnCamera.setEnabled(false);
        btnGallery.setEnabled(false);


        String permissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        PermissionControl.requestAndRunOrNot(this, permissions);
    }

    // 3. 사용자가 권한체크 팝업에서 권한을 승인 또는 거절하면 아래 메서드가 호출된다.
    // String[] permissions 순서대로 grantREsults[0]가 된다.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.postPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void run() {
        btnGallery.setEnabled(true);
        btnCamera.setEnabled(true);
    }

    @Override
    public void cancle(){
        Toast.makeText(this, "권한요청을 승인하셔야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.camera_btn_gallery :
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Photo"), 100); // 선택한 이미지를 돌려받기위해서 startActivityForResult를 사용한다.
                break;
            case R.id.camera_btn_camera :
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    Uri imageUri = data.getData();
                    imageView.setImageURI(imageUri);
                    break;

            }
        }
    }
}
