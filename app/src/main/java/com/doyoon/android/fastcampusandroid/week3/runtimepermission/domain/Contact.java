package com.doyoon.android.fastcampusandroid.week3.runtimepermission.domain;

/**
 * Created by DOYOON on 6/1/2017.
 */

public class Contact {
    private int id;
    private String name;
    private String tel;
    private String address;
    private String email;

    public Contact(int id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
