package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Address {
    public int idAddress;
    public int address;
    public int idPrivate;
    public int idCompany;
    public int isCompany;
    public int idBillAddress;

    public Address(int idAddress, int address, int idPrivate, int idCompany, int isCompany, int idBillAddress) {
        this.idAddress = idAddress;
        this.address = address;
        this.idPrivate = idPrivate;
        this.idCompany = idCompany;
        this.isCompany = isCompany;
        this.idBillAddress = idBillAddress;
    }
}
