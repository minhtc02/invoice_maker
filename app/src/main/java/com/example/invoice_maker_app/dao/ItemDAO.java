package com.example.invoice_maker_app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void insert(Item item);

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Query("SELECT * FROM Item WHERE id=:id")
    List<Item> checkExist(int id);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM Item")
    void deleteAll();
}
