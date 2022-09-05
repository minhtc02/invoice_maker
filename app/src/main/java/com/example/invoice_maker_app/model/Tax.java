package com.example.invoice_maker_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tax")
public class Tax {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String rate;

    public Tax(String name, String rate) {
        this.name = name;
        this.rate = rate;
    }

    public Tax() {
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
