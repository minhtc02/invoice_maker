package com.example.invoice_maker_app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.invoice_maker_app.model.Discount;

import java.util.List;

@Dao
public interface DiscountDAO {
    @Insert
    void insert(Discount discount);

    @Query("SELECT * FROM Discount")
    List<Discount> getAll();

    @Query("SELECT * FROM Discount WHERE id=:id")
    List<Discount> checkExist(String id);

    @Update
    void update(Discount discount);

    @Delete
    void delete(Discount discount);

    @Query("DELETE FROM Discount")
    void deleteAll();
}
