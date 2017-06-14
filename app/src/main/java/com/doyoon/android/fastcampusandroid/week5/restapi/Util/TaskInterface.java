package com.doyoon.android.fastcampusandroid.week5.restapi.Util;

/**
 * Created by DOYOON on 6/12/2017.
 */

/* 디자인패턴이 아니다. 인터페이스가 객체지향의 끝 */
/* 상당히 많은 코드(Fragment 등) 이런형식으로 만들어진다. */
public interface TaskInterface {
    public String getUrl();
    public void postExecute(String jsonResult);
}
