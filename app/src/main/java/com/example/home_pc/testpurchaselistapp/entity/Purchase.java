package com.example.home_pc.testpurchaselistapp.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "purchaseTable")
public class Purchase {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int price;
    private int inMyList = 0;
    private int selected = 0;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Purchase(String title, int price, byte[] image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInMyList() {
        return inMyList;
    }

    public void setInMyList(int inMyList) {
        this.inMyList = inMyList;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
