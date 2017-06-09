package com.doyoon.android.fastcampusandroid.week4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.doyoon.android.fastcampusandroid.week4.database.domain.BBS;
import com.doyoon.android.fastcampusandroid.week4.database.domain.Memo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by DOYOON on 6/9/2017.
 */

/* 그래서 이런 DBhelper 계열들은 메모리에 하나만 존재하게 하기 위해서 싱글턴으로 만든다. */
/* 생성자를 private으로 선언한다. */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    // databaseName
    // factory
    // databaseVersion은 final로 지정

    public static final String DATABASE_NAME = "test_database.db";
    /* Database 버전이 업그레이드 되면 onCreate를 생성하지 않고 OnUpgrade를 사용한다. */
    // OnUpgrade를 사용한다.. 무엇인가 컬럼이 추가 되었을때 db구조에 변경이 생기면 버전업
    // 최초로 설치한 사람은 Oncreate가 호출되고, 최초 설치하지 않은 사람은 Onupgrade가 호출이 된다.
    public static final int DATABASE_VERSION = 1;


    /* 싱글턴 */
    private static DBHelper instance = null;
    public static DBHelper getInstance(Context context) {
        /* 부가적으로 syncronize도 필요하다... */
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    // DB 헬퍼를 만들때는 필요한 것은 Context밖에 필요가 없다.
    // db 파일은 /data/data/패키지명/database/test_database.db 가 생성이 된다.
    private DBHelper(Context context) {
        // Factory는 connection pool 같은거임(
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 최초에 생성되면 버전 체크를 해서 onCreate를 호출한다.
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // Connection Source는 System으로 부터 온다.
        try {
            /* 처음 설치하는 사람 && 버전업에서 테이블 추가 */
            /* OnCreate에는 OnUpgrade에 있는 내용이 그대로 있어야 된다. */
            TableUtils.createTable(connectionSource, Memo.class);
            TableUtils.createTable(connectionSource, BBS.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            /* 업그레이드 하는 사람 && 버전업에서 테이블 구조 변경*/
            /* 1. 기존 테이블을 백업하기 위한 임시테이블을 생성하고 데이터를 담아둔다.
            *   예) create table temp_memo, <- 기존데이터
            * 2. Memo 테이블을 삭제하고 다시 생성한다.
            * 3. 백업된 데이터를 다시 입력합니다.
            * 4. 임시테이블 삭제 */

            /* 업그레이드 하는 사람 && 버전업에서 테이블 추가*/
            /* 버전업이 되면서 BBS를 테이블이 추가 되었다. */
            TableUtils.createTable(connectionSource, BBS.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create
    public void create(Memo memo){
        try {
            // 1. 테이블에 연결
            // Generic은 타입제한을 하는 것... DAO<T, id>
            // T형은 클래스, id의 형타입을 <>안에 선언..
            Dao<Memo, Integer> dao = getDao(Memo.class);
            // 2. 데이터를 입력
            dao.create(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Memo> readAll(){
        List<Memo> datas = null;
        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            datas = dao.queryForAll();  // 테이블의 전체 데이터를 가져온다.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public Memo read(int memoId) {
        Dao<Memo, Integer> dao = null;
        Memo memo = null;
        try {
            dao = getDao(Memo.class);
            memo = dao.queryForId(memoId);
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
            Dao<Memo, Integer> dao = getDao(Memo.class);
            String query = "select * from memo where content like '%" + word + "%";
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
            Dao<Memo, Integer> dao = getDao(Memo.class);
            dao.update(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(Memo memo){
        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            dao.delete(memo);
            // 참고 : 아이디로 삭제
            // dao.deleteById(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 오버로드
    public void delete(int id) {
        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
