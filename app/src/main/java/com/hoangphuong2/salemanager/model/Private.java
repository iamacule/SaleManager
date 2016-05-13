package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Private {
    public int idPrivate;
    public String name;
    public String address;
    public String email;
    public String note;
    public int sex;
    public int idCompany;

    public Private() {
    }

    public Private(int idPrivate, String name, String address, String email, String note, int sex, int idCompany) {
        this.idPrivate = idPrivate;
        this.name = name;
        this.address = address;
        this.email = email;
        this.note = note;
        this.sex = sex;
        this.idCompany = idCompany;
    }
}
