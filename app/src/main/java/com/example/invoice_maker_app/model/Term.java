package com.example.invoice_maker_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Term")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Term(String name) {
        this.name = name;
    }

    public Term() {
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
