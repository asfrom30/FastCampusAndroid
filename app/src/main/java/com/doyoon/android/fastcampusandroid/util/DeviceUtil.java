package com.doyoon.android.fastcampusandroid.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.doyoon.android.fastcampusandroid.BuildConfig;

import java.io.File;
import java.io.IOException;

/**
 * Created by DOYOON on 6/10/2017.
 */

public class DeviceUtil {

    public static String TAG = DeviceUtil.class.getName();

    /* Camera Util */
    /* Note : 세개가 모두 일치해야됨.*/
    //        1. Path 일치
    //            -. 임시 생성파일과
    //            -. xml 파일패스가 일치
    //        2. Request 코드일치,
    //            -. Send Intent
    //            -. Result Intent
    //        3. Authority 일치
    //            -. Manifest안의 Provider 태그의 Authorites와
    //            -. 코드안에 String Autority 변수가 일치.

    /* Description */
    //    롤리팝 이하 버전에서는 카메라 캡쳐 파일이 인텐트로 들어왔고 => onActivityResult()함수 안에서 Intent.getdata()로 바로 획득
    //    이상버전부터는 URI를 통해서만 접근이 가능하다. => URI를 전역변수로 선언해서 넘겨준다.
    //
    //    근데 이 URI가 또 왜 그러냐면
    //    마시멜로우 이하부터는 FileProvider없이 File만 있으면 권한 없이 그냥 URI를 획득할 수 있는데 => fileURI = Uri.fromFile(tempFile);
    //    마시멜로우 이상 버전부터는 FileProvider를 통해서만 권한을 획득할 수 있다. => fileURI = FileProvider.getUriForFile((activity.getBaseContext()), authority, tempFile) */

    public static class Camera{
        public static Uri takePhoto(Activity activity, String xmlExternalPath/* Folder Name */ , int requestCode) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            /* Under Lollipop Version Doesn't need Convert File to URI*/
            /* Just send Intent and In onActivityResult, You can get simply File's URI using Intent.getData(); */
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                activity.startActivityForResult(intent, requestCode);
                return null;
            }

            Uri fileURI = null;
            File tempFile = null;

            try {
                /* Create temp file for saving photo */
                tempFile = createFileForCamera(xmlExternalPath); //TODO : Need to change File Util
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (tempFile == null) {
                Log.e(TAG, "Created File is null for Camera(Photo Capture)");
                return null;
            }

            /* Under Marshmallow Version doesn't need File Provider during access URI*/
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                fileURI = Uri.fromFile(tempFile);
            } else { /* You have to access Using File Privider */
                // File Provider need Authority
                // Authority should be set in manifest file using <provider> tag for run below code
                String authority = BuildConfig.APPLICATION_ID + ".provider"; // get app ID from Gradle
                fileURI = FileProvider.getUriForFile((activity.getBaseContext()), authority, tempFile);
            }

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileURI);
            activity.startActivityForResult(intent, requestCode); // NOTE : Context doesn't have startActivityForResult() method.

            return fileURI;
        }

        // 파일생성에 관한 함수를 만들고 Exception에 담는다.
        private static File createFileForCamera(String xmlExternalPath) throws IOException {
            // 임시 파일명 생성
            String tempFilename = "TEMP_" + System.currentTimeMillis();
            // 임시파일 저장용 디렉토리 생성
            // 익스터널 스토리지의 루트 경로(실제 시스템의 루트가 아님)
            // XML에서 설정했던 그 경로에 대한 권한을 얻는 것이다.
            File tempDir = new File(Environment.getExternalStorageDirectory() +"/" + xmlExternalPath + "/"); // path라는 것을 알려주기 위해 /로 랩한다
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
    }


}
