package com.doyoon.android.fastcampusandroid.week4.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week4.database.domain.Memo;

import java.util.List;

public class SqliteORMActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_orm);

        DBHelper dbHelper = new DBHelper(this);

        for(int i=0; i < 10; i++) {
            Memo memo = new Memo();
            memo.setTitle("제목" + i);
            memo.setContent("내용" + i);
            dbHelper.create(memo);
        }

        Memo one = dbHelper.read(3);
        Log.i("Memo", one.getId() + ", title=" + one.getTitle() + ", content=" + one.getContent());

        // 데이터 전체 읽어오기.

        // 데이터 검색하기
        List<Memo> memoList = dbHelper.search("내용3");

        // 업데이트하기
        Memo memo = dbHelper.read(3);
        memo.setContent("헬로");
        dbHelper.update(memo);

        // 삭제하기


    }
}
