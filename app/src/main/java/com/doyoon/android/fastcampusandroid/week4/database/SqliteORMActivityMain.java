package com.doyoon.android.fastcampusandroid.week4.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week4.database.domain.Memo;
import com.doyoon.android.fastcampusandroid.week4.database.domain.MemoDAO;

import java.util.List;

public class SqliteORMActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_orm);

        DBHelper dbHelper = DBHelper.getInstance(this.getBaseContext());

        MemoDAO.init(dbHelper);
        MemoDAO memoDAO = MemoDAO.getInstance(); // get Instance 할때마다 변수를 넘겨주는게 부담스러워서..

        for(int i=0; i < 10; i++) {
            Memo memo = new Memo();
            memo.setTitle("제목" + i);
            memo.setContent("내용" + i);
            memoDAO.create(memo);
        }

        // 데이터 하나 읽어오기.
        Memo one = memoDAO.read(3);
        if (one != null) {
            Log.i("Memo", one.getId() + ", title=" + one.getTitle() + ", content=" + one.getContent());
        }

        // 데이터 전체 읽어오기.
        List<Memo> memoList = memoDAO.readAll();
        Log.i("Memo", "===================================================All Memo List" + memoList.size());
        for (Memo memo : memoList) {
            Log.i("Memo", memo.getId() + ", title=" + memo.getTitle() + ", content=" + memo.getContent());
        }

        // 데이터 검색하기
        List<Memo> memoSearchList = memoDAO.search("내용3");

        // 업데이트하기
        Memo tempMemo = memoDAO.read(3);
        if (tempMemo != null) {
            tempMemo.setContent("헬로");
            memoDAO.update(tempMemo);
        }

        // 모두 지우기
        memoDAO.delete(memoDAO.readAll());
        memoList = memoDAO.readAll();
        Log.i("Memo", "===================================================Again All Memo List" + memoList.size());
        for (Memo memo : memoList) {
            Log.i("Memo", memo.getId() + ", title=" + memo.getTitle() + ", content=" + memo.getContent());
        }

    }
}
