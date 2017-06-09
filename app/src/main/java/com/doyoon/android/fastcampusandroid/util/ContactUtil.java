package com.doyoon.android.fastcampusandroid.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOYOON on 6/10/2017.
 */

public class ContactUtil {

    public static List<Contact> getContactList(Context context) {

        List<Contact> contactList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();

        // Table Address
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // Column name from table
        String proj[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                , ContactsContract.CommonDataKinds.Phone.NUMBER};

        // Bring data using Content resolver
        Cursor cursor = contentResolver.query(phoneUri, proj, null, null, null);

        // Check data is existed in cursor
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(proj[0]);
                String id = cursor.getString(index);

                index = cursor.getColumnIndex(proj[1]);
                String name = cursor.getString(index);

                index = cursor.getColumnIndex(proj[2]);
                String tel = cursor.getString(index);

                contactList.add(new Contact(id, name, tel));
            }
        }
        // Note : Should be closed
        cursor.close();

        return contactList;
    }

    // TODO : See Nested Class
    // Static Inner Class
    public static class Contact{
        private String id, name, tel;

        public Contact(String id, String name, String tel) {
            this.id = id;
            this.name = name;
            this.tel = tel;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getTel() {
            return tel;
        }
    }
}

