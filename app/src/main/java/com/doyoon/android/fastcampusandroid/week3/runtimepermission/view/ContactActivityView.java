package com.doyoon.android.fastcampusandroid.week3.runtimepermission.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week3.runtimepermission.ContactActivity;
import com.doyoon.android.fastcampusandroid.week3.runtimepermission.domain.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOYOON on 6/1/2017.
 */

public class ContactActivityView {


    public ContactActivityView(ContactActivity contactActivity) {
        contactActivity.setContentView(R.layout.activity_contact);

        RecyclerView recyclerView = (RecyclerView) contactActivity.findViewById(R.id.contact_activity_recycler_view_contactList);

        List<Contact> contactList = contactActivity.getContactList();

        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };
    }

    public class MyRecyclerView extends RecyclerView.Adapter<ViewHolder> {

        private  ArrayList<Contact> contactList;

        public MyRecyclerView(ArrayList<Contact> contactList) {
            this.contactList = contactList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Contact contact = this.contactList.get(position);

//            holder.setName(contact.getName());
//            holder.setNumber(contact.get);
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
