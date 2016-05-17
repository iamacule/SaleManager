package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Sale {
    public int idItem;
    public int idCompany;
    public int idPerson;
    public int price;
    public String note;

    public Sale() {
    }

    public Sale(int idItem, int idCompany, int idPerson, int price, String note) {
        this.idItem = idItem;
        this.idCompany = idCompany;
        this.idPerson = idPerson;
        this.price = price;
        this.note = note;
    }
}
