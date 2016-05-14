package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Sale {
    public int idItem;
    public int idCompany;
    public int idPrivate;
    public int price;
    public String note;

    public Sale() {
    }

    public Sale(int idItem, int idCompany, int idPrivate, int price, String note) {
        this.idItem = idItem;
        this.idCompany = idCompany;
        this.idPrivate = idPrivate;
        this.price = price;
        this.note = note;
    }
}
