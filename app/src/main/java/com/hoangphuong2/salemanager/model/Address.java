package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Address {
    public int idAddress;
    public String address;
    public int idPrivate;
    public int idCompany;
    public int isCompany;
    public int isBillAddress;
    public String note;

    public Address() {
    }

    public Address(int idAddress, String address, int idPrivate, int idCompany, int isCompany, int isBillAddress,String note) {
        this.idAddress = idAddress;
        this.address = address;
        this.idPrivate = idPrivate;
        this.idCompany = idCompany;
        this.isCompany = isCompany;
        this.isBillAddress = isBillAddress;
        this.note = note;
    }
}
