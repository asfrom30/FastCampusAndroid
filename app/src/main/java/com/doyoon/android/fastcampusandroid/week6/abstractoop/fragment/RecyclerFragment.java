package com.doyoon.android.fastcampusandroid.week6.abstractoop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week6.abstractoop.BBS;

import java.util.List;

/**
 * Created by DOYOON on 6/28/2017.
 */

public abstract class RecyclerFragment extends Fragment {

    private List<Class> dataList;
    private int layoutId;

    public void recylcerInit(List<Class> dataList, int layoutId){
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    public RecyclerFragment setLayoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public void setDataList(List<Class> dataList) {
        this.dataList = dataList;
    }

    public void notifyDataListChanged(){
        this.adapter.notifyDataSetChanged();
    }

    private RecyclerView.Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(this.layoutId, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_bbs_rv);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    public abstract void onClickReyclerItem(int position);

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bbs, null, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Object data = dataList.get(position);
            BBS bbs = (BBS) data;

            holder.getTextViewTitle().setText(bbs.getTitle());
            holder.getTextViewAuthor().setText(bbs.getAuthor());
            holder.getTextViewConent().setText(bbs.getContent());

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        /* private 으로 바꾸기... */
        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textViewAuthor;
            private TextView textViewTitle;
            private TextView textViewConent;

            public ViewHolder(View itemView) {
                super(itemView);
                textViewTitle = (TextView) itemView.findViewById(R.id.item_bbs_title);
                textViewAuthor = (TextView) itemView.findViewById(R.id.item_bbs_author);
                textViewConent = (TextView) itemView.findViewById(R.id.item_bbs_content);
            }

            public TextView getTextViewAuthor() {
                return textViewAuthor;
            }

            public void setTextViewAuthor(TextView textViewAuthor) {
                this.textViewAuthor = textViewAuthor;
            }

            public TextView getTextViewTitle() {
                return textViewTitle;
            }

            public void setTextViewTitle(TextView textViewTitle) {
                this.textViewTitle = textViewTitle;
            }

            public TextView getTextViewConent() {
                return textViewConent;
            }

            public void setTextViewConent(TextView textViewConent) {
                this.textViewConent = textViewConent;
            }
        }
    }



}
