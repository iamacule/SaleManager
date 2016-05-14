package com.hoangphuong2.salemanager.model;

/**
 * Created by MrAn on 13-May-16.
 */
public class ItemType {
    public int idItemType;
    public String name;
    public String note;

    public ItemType() {
    }

    public ItemType(int idItemType, String name, String note) {
        this.idItemType = idItemType;
        this.name = name;
        this.note = note;
    }
}
