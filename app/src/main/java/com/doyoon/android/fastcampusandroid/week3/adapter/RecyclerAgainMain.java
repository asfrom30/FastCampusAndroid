package com.doyoon.android.fastcampusandroid.week3.adapter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.PermissionUtil;

public class RecyclerAgainMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_again);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.READ_CONTACTS};
            PermissionUtil.hasPermissionsAndRequestIfNot(this, permissions, 100);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                init();
            } else {
                Toast.makeText(this, "권한을 승인하셔야만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init(){
        /* 권한설정이 있으면 이 함수를 다른곳으로 뺀다. */
        Adapter_C adapter = new Adapter_C(Loader_B.getData(getBaseContext()));
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recyclerAgain_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }
}
