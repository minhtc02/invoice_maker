package com.example.invoice_maker_app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.invoice_maker_app.model.Invoice;

import java.util.List;

@Dao
public interface InvoiceDAO {
    @Insert
    void insertInvoice(Invoice invoice);

    @Query("SELECT * FROM Invoice")
    List<Invoice> getAll();

    @Query("SELECT * FROM Invoice WHERE number=:number")
    List<Invoice> checkExist(String number);

    @Update
    void updateInvoice(Invoice invoice);

    @Delete
    void deleteInvoice(Invoice invoice);

    @Query("DELETE FROM Invoice")
    void deleteAll();
}
