package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class Item {
    public int idItem;
    public String name;
    public int idItemType;
    public String specifications;
    public String note;

    public Item() {
    }

    public Item(int idItem, String name, int idItemType, String specifications, String note) {
        this.idItem = idItem;
        this.name = name;
        this.idItemType = idItemType;
        this.specifications = specifications;
        this.note = note;
    }
}
