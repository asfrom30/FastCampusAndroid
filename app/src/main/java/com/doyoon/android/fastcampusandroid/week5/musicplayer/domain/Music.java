package com.doyoon.android.fastcampusandroid.week5.musicplayer.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DOYOON on 6/14/2017.
 */

public class Music {

    private static Music instance = null;
    /* String과 기본형 빼고 번지수로 비교를 한다. */
    Set<Item> itemSet = null;

    /* static 블럭.....
    * App이 뜨기전에 static을 먼저 실행하는데 이 경우 Context를 사용하는 것을 사용 할 수 없다.
    * 따라서 Dummy처럼 static 블럭을 사용할 수 없고 static method를 만들어야 된다.
    * ContentResolver는 context에서 꺼낼 수 있다.*/

    private Music(){
        itemSet = new HashSet<>();
    }

    public static Music getInstance(){
        if (instance == null) {
            instance = new Music();
        }
        return instance;
    }

    public Set<Item> getItemSet(){
        return itemSet;
    }

    /* 독립적으로 설계, 어떤것도 이것을 가져다가 쓸 수 있게... */
    public void loader(Context context){

        ContentResolver resolver = context.getContentResolver();

        // 1. 테이블 명 정의
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        // 2. 컬럼명 정의
        String proj[] = {
            MediaStore.Audio.Media._ID,         // 음악재생할때..
            MediaStore.Audio.Media.ALBUM_ID,    // 앨범 아트를 조회할때 이 ID를 사용...
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
        };

        // 3. 쿼링~
        Cursor cursor = resolver.query(uri, proj, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {

                // TODO 이 지점에서 새로 생기므로 SET이 정상적으로 작동하지 앟느다.
                Item item = new Item();
                item.id = getValue(cursor, proj[0]);
                item.albumId = getValue(cursor, proj[1]);
                item.title = getValue(cursor, proj[2]);
                item.artist = getValue(cursor, proj[3]);

                item.musicUri = makeMusicUri(item.id);
                item.albumArtUri = makeAlbumUri(item.albumId);

                itemSet.add(item);
            }
        }
        /* Cursor!!!!! 꼭 닫아랑~ */
        cursor.close();
    }

    public String getValue(Cursor cursor, String name){
        int index = cursor.getColumnIndex(name);
        return cursor.getString(index);
    }

    public class Item{
        private String id;
        private String albumId;
        private String artist;
        private String title;

        /* Uri를 미리 조합해서 넣어두면 편하다.. 생성할때... */
        private Uri musicUri;
        private Uri albumArtUri;
        public boolean itemClicked = false;

        public Item() {
            super();
        }

         /*
            String string = "문자열" + "문자열1" + "문자열2"
            hashcode...

            문자열 연산을 하는데 Hashtable을 사용하는데
            Hashtable은 하나밖에 없어서 문자열 연산에 이 Hashtable을 사용한다.

            1. 문자열         => asdkfqweijr 그대로 들어가는게 아니라 해쉬코드로 들어간다.
            2. 문자열문자열1  => oijkndsadf123
            3. 문자열문자열1문자열2
            ....
            마지막. 주소값

         */

//        @Override
//        public int hashCode() {
//            return super.hashCode();
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if(this == obj) return true;
//
//            if (obj == null || getClass() != obj.getClass()) {
//                return false;
//            }
//
//            Item item = (Item) obj;
//            // return id.equals(item.id);
//            return this.id.hashCode() == item.hashCode();
//        }
        /* 하나의 코드를 절약 */
        @Override
        public int hashCode() {
            /* 여기만 바꾸어 주면 캐스팅할 필요가 없다. */
            /* return super.hashCode(); */
            return id.hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null) {
                return false;
            }

            if(this == obj) return true;

            if (!(obj instanceof Item)) {
                return false;
            }

            Item item = (Item) obj;
            // return id.equals(item.id);
//            return this.id.hashCode() == item.hashCode();
            return this.id.hashCode() == obj.hashCode();
        }

        @Override
        public String toString() {
            return super.toString();
        }

        /* 2번째 방법 */
        // toString을 오버라이드 한다. id값을 리턴하도록 해서
        // 비교하는 곳에서 toString으로 비교하도록 한다.


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Uri getMusicUri() {
            return musicUri;
        }

        public void setMusicUri(Uri musicUri) {
            this.musicUri = musicUri;
        }

        public Uri getAlbumArtUri() {
            return albumArtUri;
        }

        public void setAlbumArtUri(Uri albumArtUri) {
            this.albumArtUri = albumArtUri;
        }
    }

    /* URI는 이미 만들어져 있다. ID나 album ID는 안드로이드에서 부여한 것이다....
    *  음악파일을 스토리지에 넣으면서 안드로이드 데이터베이스에 등록하는 것이다.
    *  안에 있는 TAG 정보를 조합해서 등록하는 것. 밑에 있는 함수들은 URI를 만드는 것.
    *  URI형태로 등록하는 것.... */



    public Uri makeMusicUri(String musicId) {
        /* URI끼리 합치는 것이니까.. */
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return Uri.withAppendedPath(contentUri, musicId);
    }

    private Uri makeAlbumUri(String albumId) {
        /* String으로 되어 있기 때문에 Uri로 변경... */
        String albumUri = "content://media/external/audio/albumart/";
        return Uri.parse(albumUri + albumId);
    }


}
