package com.doyoon.android.fastcampusandroid.week3.adapter;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.util.PermissionControl;

public class RecyclerAgainMain extends AppCompatActivity implements PermissionControl.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_again);

        String[] permissions = {Manifest.permission.READ_CONTACTS};
        PermissionControl.requestAndRunOrNot(this, this, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.postPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void run() {
         /* 권한설정이 있으면 이 함수를 다른곳으로 뺀다. */
        Adapter_C adapter = new Adapter_C(Loader_B.getData(getBaseContext()));
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recyclerAgain_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    @Override
    public void cancle() {

    }
}
