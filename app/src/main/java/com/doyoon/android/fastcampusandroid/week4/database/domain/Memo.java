package com.doyoon.android.fastcampusandroid.week4.database.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by DOYOON on 6/9/2017.
 */
// table 이름을 memo야..
@DatabaseTable(tableName = "memo")
public class Memo {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String title;
    @DatabaseField(columnDefinition = "content")
//    @DatabaseField(columnDefinition = "content", generatedId = true)      // and 조건은 쉼표로 구분
    String content;
    // 데이터베이스 컬럼으로 사용하지 않는다.
    String myReamrk;

    @DatabaseField
    private Date date;

    public Memo() {
        // 2월 버전까지는 ORM LITE는 기본 생성자가 없으면 동작하지 않습니다.
        setDate();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMyReamrk() {
        return myReamrk;
    }

    public void setMyReamrk(String myReamrk) {
        this.myReamrk = myReamrk;
    }

    public Date getDate() {
        return date;
    }

    // 안에서만 쓰니까 private으로 막아논다.
    private void setDate() {
        Date date = new Date(System.currentTimeMillis());
        this.date = date;
    }
}
