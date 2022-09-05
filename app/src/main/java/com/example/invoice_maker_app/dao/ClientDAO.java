package com.example.invoice_maker_app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Invoice;

import java.util.List;

@Dao
public interface ClientDAO {
    @Insert
    void insert(Client client);

    @Query("SELECT * FROM Client")
    List<Client> getAll();

    @Query("SELECT * FROM Client WHERE id=:id")
    List<Client> checkExist(int id);

    @Update
    void update(Client client);

    @Delete
    void delete(Client client);

    @Query("DELETE FROM Client")
    void deleteAll();
}
