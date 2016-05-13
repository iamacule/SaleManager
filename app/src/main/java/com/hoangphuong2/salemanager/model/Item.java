package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Item {
    public int idItem;
    public String name;
    public int idItemType;
    public String price;
    public String note;

    public Item(int idItem, String name, int idItemType, String price, String note) {
        this.idItem = idItem;
        this.name = name;
        this.idItemType = idItemType;
        this.price = price;
        this.note = note;
    }
}
