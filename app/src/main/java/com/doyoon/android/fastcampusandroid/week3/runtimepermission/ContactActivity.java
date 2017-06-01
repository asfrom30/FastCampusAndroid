package com.doyoon.android.fastcampusandroid.week3.runtimepermission;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.doyoon.android.fastcampusandroid.week3.runtimepermission.domain.Contact;
import com.doyoon.android.fastcampusandroid.week3.runtimepermission.view.ContactActivityView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    /* 아래코드를 manifest에 추가할것 */
//    <uses-permission android:name="android.permission.READ_CONTACTS"></uses-permission>

    /* MVP 설계로 옮길것*/
    // Activity를 Presenter로 사용하는게 효율성을 훨씬 좋다. new와 형변환을 사용하지 않기 때문에..
    // 구현체에 의지하지 않고 상위 부모클래스나 인터페이스에 의존한다.(ex) List<Contact> contactList = new ArrayList<>();, Acitivity-MainActivity
    // Casting 자체도 리소스를 사용하기 때문에 casting을 하지 않고 그대로 사용하는 것이 어떨까

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Contact> contactList = getContactList();

        ContactActivityView contactActivityView = new ContactActivityView(this);


        for (Contact contact : contactList) {
            Log.i("Contacts", "이름+" + contact.getName() + ", tel" + contact.getTel());
        }
    }

    public List<Contact> getContactList(){
        Log.i("dy", "========================================================");
        // 6. 데이터 저장소를 먼저 정의한다.(구현체에 의지하지 않고 상위 부모 클래스나 인터페이스에 의존한다. )
        List<Contact> contactList = new ArrayList<>();

        // 일종의 Databas 관리툴
        // 전화번호부도 하나의 앱인데, 전화번호부에 이미 만들어져 있는 Content Privider가 있다.
        // 내가 다른 앱에 주고 싶으면 Content Provider 사용
        // 다른 앱에 접근해서 정보를 가져오는 역할을 한다.
        ContentResolver resolver = getContentResolver();

        /* Note : Database의 구조는 비슷하다 */

        // 1. 데이터 컨텐츠 uri (자원의 주소)를 정의
        // 전화번호 URI
        // URI 객체..
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // 2. 데이터에서 가져올 컬럼명을 정의
        String projections[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                , ContactsContract.CommonDataKinds.Phone.NUMBER};

        // 3. ContentResolver로 Query를 날려서 Data를 가져온다.
        // cursor arrayList랑 비슷한 객체.
        Cursor cursor = resolver.query(phoneUri, projections, null/* 정렬 조건식 및 except */, null, null);   // ORM : PARAM TO QUERY

        // 4. 반복문을 돌면서 하나씩 데이터를 꺼내면 된다.
        if(cursor != null){ /* null check를 항상 해주는게 좋다. */   //TODO:Log는 어떻게 확인하는 거지?
            Log.i("dy", "cursor="+cursor);
            while(cursor.moveToNext()){ //TODO:data를 두번 접근하는 것은 아닐까? // cursor로 이미 접근을 했다.??
                // 4.1 위에 정의한 프로젝션의 컬럼명으로 cursor 있는 인데
                int idIndex = cursor.getColumnIndex( projections[0] );
                int id = cursor.getInt(idIndex);

                int nameIndex = cursor.getColumnIndex( projections[1] );
                String name = cursor.getString(nameIndex);

                int telIndex = cursor.getColumnIndex( projections[2] );
                String tel = cursor.getString(telIndex);

                // 5. Domain POJO로 저장
                Contact contact = new Contact(id, name, tel);

                /* NOTE : 데이터 저장소를 먼저 저장한다. */
                contactList.add(contact);
            }
        }
        return contactList;
    }
}
