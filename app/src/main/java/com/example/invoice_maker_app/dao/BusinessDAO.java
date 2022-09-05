package com.example.invoice_maker_app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.invoice_maker_app.model.Business;
import com.example.invoice_maker_app.model.Invoice;

import java.util.List;

@Dao
public interface BusinessDAO {
    @Insert
    void insert(Business business);

    @Query("SELECT * FROM Business")
    List<Business> getAll();

    @Query("SELECT * FROM Business WHERE id=:id")
    List<Business> checkExist(String id);

    @Update
    void update(Business business);

    @Delete
    void delete(Business business);

    @Query("DELETE FROM Business")
    void deleteAll();
}
