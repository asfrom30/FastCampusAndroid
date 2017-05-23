package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;

public class DyActivity extends AppCompatActivity {

    ListView detailListView;

    ArrayList<Integer> datas = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dy);

        for(int i=0; i < 100; i++){
            datas.add(i);
        }

        detailListView = (ListView) findViewById(R.id.detailListView);

        // 아답터를 만든다.
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);

        detailListView.setAdapter(arrayAdapter);

    }
}
