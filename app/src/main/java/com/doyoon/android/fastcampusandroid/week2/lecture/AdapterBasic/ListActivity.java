package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;

    // 키를 정의한다.
    public static final String DATA_KEY = "ListActivityData";   // 다른 액티비티와 데이터를 주고 받을때 사용하는 키를 먼저 정의해둔다.

    ArrayList<String> datas = new ArrayList<>();    // 초기화를 밖에서 해주는것 좋지 않지만,
                                                    // 안드로이드에서는 생성자가 없어서 여기에 선언한다.
                                                    // Oncreate에 선언해버리면 View가 불러올때마다 계속해서 new를 하기 때문에, 밖에서 해준다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listView);

        // 1. Data
        for(int i=0; i<100; i++){
            datas.add("데이터" + i);
        }
        // 2. Adapter
        //  SimpleAdapter adapter = new SimpleAdapter(this, datas, android.R.layout.simple_list_item_1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);

        // 2.1 실제로는 Array Activity가 아닌 custom activity를 사용한다.
        // CustomListActivity 참조

        // 3. 뷰 > 연결 < 아답터
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra(ListActivity.DATA_KEY, datas.get(position));  // Position
                startActivity(intent);
            }
        });
    }
}
