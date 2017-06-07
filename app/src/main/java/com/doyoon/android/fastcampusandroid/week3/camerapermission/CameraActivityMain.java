package com.doyoon.android.fastcampusandroid.week3.camerapermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.BuildConfig;
import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.PermissionUtil;

import java.io.File;
import java.io.IOException;

public class CameraActivityMain extends AppCompatActivity implements View.OnClickListener{

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

        String permssions[] = {Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionUtil.hasPermissionsAndRequestIfNot(this, permssions, 100);
        }
    }
    public void setView(){
        this.imageView = (ImageView) findViewById(R.id.cameraActivity_main_imageView);
        this.btnCaptuer = (Button) findViewById(R.id.cameraActivity_main_btn);
    }

    public void setLisntener(){
        btnCaptuer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.takePhoto();
    }

    // 파일 Uri를 전역변수로 저장을 한다.
    Uri fileUri;

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 롤리팝 미만 버전에서만 바로 실행
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 카메라를 찍어서 담을 파일을 미리 생성해 두어야 한다.
            File photoFile = null;  // 왜 null을 했냐면 파일 IO를 사용하기 위해서...
            try {
                photoFile = this.createFile();  // 호출한 측에서 try catch 처리..
                if (photoFile != null) {
                    // 마시멜로우 이상 버전은  파일 프로바이더를 통해 권한을 획득(Uri 권한을 획득)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // 이제 권한이 획득이 된것이다
                                                                        // 매니 패스트의 provider authority와 동일하다.
                        fileUri = FileProvider.getUriForFile(getBaseContext(), BuildConfig.APPLICATION_ID+".provider"/* Authority */, photoFile/* 파일을 넘겨준다. */);
                    } else { // 마시멜로우 버전 이하는 권한 없이 획득
                        fileUri = Uri.fromFile(photoFile);
                    }
                    // FileUri에 직접 세팅을 해줘야 한다.
                    // 아래 Intent와 다르다...
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, Const.Camera.REQ_CAMERA);
                }
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "사진파일 저장을 위한 임시파일을 생성할 수 없습니다", Toast.LENGTH_SHORT).show();
                return; // void라고 하더라도 return 처리가 가능. 더이상 진행이 되면 안되니까 return
            }
        } else {
            startActivityForResult(intent, Const.Camera.REQ_CAMERA);
        }
    }

    // 파일생성에 관한 함수를 만들고 Exception에 담는다.
    private File createFile() throws IOException {
        // 임시 파일명 생성
        String tempFilename = "TEMP_" + System.currentTimeMillis();
        // 임시파일 저장용 디렉토리 생성
        // 익스터널 스토리지의 루트 경로(실제 시스템의 루트가 아님)
        // XML에서 설정했던 그 경로에 대한 권한을 얻는 것이다.
        File tempDir = new File(Environment.getExternalStorageDirectory() +"/CameraPath/"); // path라는 것을 알려주기 위해 /로 랩한다

        if(!tempDir.exists()){
            tempDir.mkdir(); // 없으면 모두 생성
        }

        //실제 임시파일을 생성
        File tempFile = File.createTempFile(
                tempFilename,// 파일네임
                ".jpg",     // suffix
                tempDir     // 디렉토리
        );
        return tempFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.Camera.REQ_CAMERA) {
            if (resultCode == RESULT_OK) {
                Uri imageUri = null;
                // 롤리팝 이전 버전에서는 이렇게 하면 됐다.
                // 롤리팝 미만 버전에서는 data 인텐트에 찍은 사진의 uri가 담겨온다.
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    imageUri = data.getData(); // Return URI
                } else {

                    imageUri = fileUri;
//                    startActivityForResult(intent, Const.Camera.REQ_CAMERA);
                }
                imageView.setImageURI(imageUri);
                //보안때문에 코드가 많이 추가됨
                // 카메라를 찍는 것은 문제가 되지 않는데... 저장할때 외부 스토리지 사용해야 되서..
                // 모두 접근이 되어 버린다.
                // 특정 폴더에만 접근하는 권한 file provider
            }
        }
    }
}
