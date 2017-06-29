package com.doyoon.android.fastcampusandroid.week6.abstractoop;

/**
 * Created by DOYOON on 6/27/2017.
 */

public class BBS {
    String title;
    String author;
    String content;

    public BBS(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
