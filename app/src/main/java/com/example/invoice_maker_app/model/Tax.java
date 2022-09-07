package com.example.invoice_maker_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Tax")
public class Tax implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String rate;
    private boolean isSelected;

    public Tax(String name, String rate, boolean isSelected) {
        this.name = name;
        this.rate = rate;
        this.isSelected = isSelected;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
