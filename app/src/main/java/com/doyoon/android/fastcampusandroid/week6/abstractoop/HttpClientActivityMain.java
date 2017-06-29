package com.doyoon.android.fastcampusandroid.week6.abstractoop;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week6.abstractoop.fragment.BbsListFragment;

import java.util.ArrayList;
import java.util.List;

public class HttpClientActivityMain extends AppCompatActivity {

    BbsListFragment bbsListFragment;
    List myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client_main);





        /* Test OK */

        bbsListFragment = new BbsListFragment();
        myList = new ArrayList<BBS>();
        BBS bbs = new BBS("제목1", "저자1", "내용1");
        BBS bbs2 = new BBS("제목2", "저자2", "내용2");
        myList.add(bbs);
        myList.add(bbs2);
        bbsListFragment.recylcerInit(myList, R.layout.fragment_recycler);

        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.httpclient_frame_layout, bbsListFragment);
        transaction.commit();

        Button button = (Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BBS bbs3 = new BBS("제목3", "저자3", "내용3");
                myList.add(bbs3);
                bbsListFragment.notifyDataListChanged();
            }
        });
    }
}
