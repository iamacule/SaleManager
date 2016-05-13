package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Phone {
    public String number;
    public String note;
    public int idPrivate;
    public int idCompany;
    public int isCompany;

    public Phone(String number, String note, int idPrivate, int idCompany, int isCompany) {
        this.number = number;
        this.note = note;
        this.idPrivate = idPrivate;
        this.idCompany = idCompany;
        this.isCompany = isCompany;
    }
}
