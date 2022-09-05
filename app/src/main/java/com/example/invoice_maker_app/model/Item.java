package com.example.invoice_maker_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item")
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String price;
    private String description;


    public Item(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
