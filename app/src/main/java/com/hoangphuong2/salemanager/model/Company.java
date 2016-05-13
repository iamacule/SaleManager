package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Company {
    public int idCompany;
    public String name;
    public String tax;
    public String note;

    public Company(int idCompany, String name, String tax,String note) {
        this.idCompany = idCompany;
        this.name = name;
        this.tax = tax;
        this.note = note;
    }
}
