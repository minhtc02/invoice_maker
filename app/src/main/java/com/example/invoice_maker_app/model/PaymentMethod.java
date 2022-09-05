package com.example.invoice_maker_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PaymentMethod")
public class PaymentMethod {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public PaymentMethod(String name) {
        this.name = name;
    }

    public PaymentMethod() {
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
}
