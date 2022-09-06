package com.example.invoice_maker_app.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Tax;

import java.util.List;

@Dao
public interface TaxDAO {
    @Insert(onConflict = REPLACE)
    void insert(Tax tax);

    @Query("SELECT * FROM Tax")
    List<Tax> getAll();

    @Query("SELECT * FROM Tax WHERE id=:id")
    List<Tax> checkExist(int id);

    @Update
    void update(Tax tax);

    @Delete
    void delete(Tax tax);

    @Query("DELETE FROM Tax")
    void deleteAll();
}
