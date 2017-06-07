package com.doyoon.android.fastcampusandroid.week3.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doyoon.android.fastcampusandroid.R;

import java.util.List;

/**
 * Created by DOYOON on 6/2/2017.
 */

public class Adapter_C extends RecyclerView.Adapter<ViewHolder_D> {

    private List<Data_A> datas;

    public Adapter_C(List<Data_A> datas) {
        this.datas = datas;


    }

    @Override
    public ViewHolder_D onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_again_rv, parent, false);
        return new ViewHolder_D(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_D holder, int position) {
        Data_A data = datas.get(position);
        holder.setTextViewName(data.getName());
        holder.setTextViewNumber(data.getTel());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
