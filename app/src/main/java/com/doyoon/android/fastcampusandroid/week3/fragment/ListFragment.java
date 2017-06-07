package com.doyoon.android.fastcampusandroid.week3.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.doyoon.android.fastcampusandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    FragmentActivityMain activity;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list, container, false);

//        Return을 바로 하지 않고 view 로 받는다.
//        LayoutInflater.from() 으로 inflater를 만드는 것이였다.
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button btnGoDetail = (Button) view.findViewById(R.id.fragment_btn_go_detail);
        btnGoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addDetail();
            }
        });

        return view;
    }


    public void setActivity(FragmentActivityMain fragmentActivityMain) {
        this.activity = activity;
    }
}
