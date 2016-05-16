package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Phone {
    public String number;
    public int note;
    public int idPrivate;
    public int idCompany;
    public int isCompany;

    public Phone() {
    }

    public Phone(String number, int note, int idPrivate, int idCompany, int isCompany) {
        this.number = number;
        this.note = note;
        this.idPrivate = idPrivate;
        this.idCompany = idCompany;
        this.isCompany = isCompany;
    }
}
