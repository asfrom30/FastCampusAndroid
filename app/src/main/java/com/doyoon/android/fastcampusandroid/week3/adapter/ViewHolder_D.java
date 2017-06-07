package com.doyoon.android.fastcampusandroid.week3.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

/**
 * Created by DOYOON on 6/2/2017.
 */

public class ViewHolder_D extends RecyclerView.ViewHolder{

    private TextView textViewName;
    private TextView textViewNumber;

    public ViewHolder_D(View itemView) {
        super(itemView);

        textViewName = (TextView) itemView.findViewById(R.id.recyclerAgain_tv_name);
        textViewNumber = (TextView) itemView.findViewById(R.id.recyclerAgain_tv_number);
    }

    public String getTextViewName() {
        return textViewName.getText().toString();
    }

    public void setTextViewName(String name) {
        this.textViewName.setText(name);
    }

    public String getTextViewNumber() {
        return textViewNumber.getText().toString();
    }

    public void setTextViewNumber(String tel) {
        this.textViewNumber.setText(tel);
    }
}
