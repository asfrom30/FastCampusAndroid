package com.doyoon.android.fastcampusandroid.week4.database.domain;

import android.util.Log;

import com.doyoon.android.fastcampusandroid.week4.database.DBHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by DOYOON on 6/11/2017.
 */


public class MemoDAO{

    public static final String TAG = MemoDAO.class.getName();

    // 1. 테이블에 연결
    // Generic은 타입제한을 하는 것... DAO<T, id>, T형은 클래스, id의 형타입을 <>안에 선언..
    private final Dao<Memo, Integer> dao;

    /* For singleton instance */
    public static MemoDAO instance;

    public static void init(DBHelper dbHelper){
        /* Already Initialize check */
        if (instance != null) {
            Log.i(TAG, "This class is already initialize");
            return;
        }

        try {
            Dao dao = dbHelper.getDao(Memo.class);
            instance = new MemoDAO(dao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MemoDAO getInstance() {
        if (instance == null) {
            Log.i(TAG, "Can not Initialize yet");
            return null;
        } else {
            return instance;
        }
    }

    public MemoDAO(Dao dao) {
        this.dao = dao;
    }

    // Create
    public void create(Memo memo){
        try {
            this.dao.create(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Memo> readAll(){
        List<Memo> datas = null;
        try {
            datas = this.dao.queryForAll();  // 테이블의 전체 데이터를 가져온다.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public Memo read(int memoId) {
        Memo memo = null;
        try {
            memo = this.dao.queryForId(memoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;
    }
    /* Search */
    // Search를 하면 목록으로가져온다. 하나 이상이 걸린다.
    public List<Memo> search(String word){
        List<Memo> datas = null;
        try {
            String query = "select * from memo where content like '%" + word + "%'";
            GenericRawResults<Memo> temps = dao.queryRaw(query, dao.getRawRowMapper());
            temps.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    /* update */
    // 업데이트의 속성은 기본적으로 데이터가 있어야 한다.
    public void update(Memo memo){
        try {
            this.dao.update(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(Memo memo){
        try {
            this.dao.delete(memo);
            // 참고 : 아이디로 삭제
            // dao.deleteById(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 오버로드
    public void delete(int id) {
        try {
            this.dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(List<Memo> memoList) {
        try {
            this.dao.delete(memoList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
