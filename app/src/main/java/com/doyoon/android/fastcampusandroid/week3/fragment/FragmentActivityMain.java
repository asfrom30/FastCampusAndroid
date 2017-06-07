package com.doyoon.android.fastcampusandroid.week3.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.fastcampusandroid.R;

public class FragmentActivityMain extends AppCompatActivity {

    ListFragment list;
    DetailFragment detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

    }

    private void createFragment(){
        list = new ListFragment();
        list.setActivity(this);
        detail = new DetailFragment();
        detail.setActivity(this);
    }

    public void addList(){
        /*패키지 네임스페이스 v4와 v7이 있는데 API 지원 레벨이 있다.
        * 버전호환성을 위해서 v4를 사용한다.*/
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); /* Support가 붙으면 버전호환성을 위해서 만들어진것이라고 유추해볼 수 있다.*/
        /* Transaction을 올리는 것이 Stack <add와 remove> */
        transaction.add(R.id.container, list);
        transaction.commit();
    }

    public void addDetail(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); /* Support가 붙으면 버전호환성을 위해서 만들어진것이라고 유추해볼 수 있다.*/
        transaction.add(R.id.container, detail);
        // 트랜잭션 처리 전체를 stack에 담아놨다가, 안드로이드의 back 버튼으로 뒤로가기를 할 수 있다.
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void backToList(){
        super.onBackPressed();
    }
}
