package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Bill {
    public int number;
    public int idCompany;
    public int idPerson;
    public long date;
    public int total;
    public int payed;
    public int payType;
    public String note;

    public Bill() {
    }

    public Bill(int number, int idCompany, int idPerson, long date, int total, int payed, int payType, String note) {
        this.number = number;
        this.idCompany = idCompany;
        this.idPerson = idPerson;
        this.date = date;
        this.total = total;
        this.payed = payed;
        this.payType = payType;
        this.note = note;
    }
}
