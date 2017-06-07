package com.doyoon.android.fastcampusandroid.week3.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOYOON on 6/2/2017.
 */

public class Loader_B {

    public static List<Data_A> getData(Context context) {

        List<Data_A> datas = new ArrayList<>();
        // Logic

        ContentResolver resolver = context.getContentResolver();

        // Table Address
        Uri phoneURi = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // Column name from table
        String proj[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                , ContactsContract.CommonDataKinds.Phone.NUMBER};

        // Bring data using Content resolver
        Cursor cursor = resolver.query(phoneURi, proj, null, null, null);/*null null null 정렬값을 여기에 넣는다. */

        // Check data is existed in cursor
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(proj[0]);
                String id = cursor.getString(index);

                index = cursor.getColumnIndex(proj[1]);
                String name = cursor.getString(index);

                index = cursor.getColumnIndex(proj[2]);
                String tel = cursor.getString(index);

                datas.add(new Data_A());
            }
        }
        /* 넌블로킹, 블로킹, 채널, 스트림, 데이터 베이스 계열들은 메모리 할당이 해제되어도 자동으로 닫히지 않는다. */
        /* 반드시 강제로 닫아줘야 한다.*/
        cursor.close();

        return datas;
    }
}
