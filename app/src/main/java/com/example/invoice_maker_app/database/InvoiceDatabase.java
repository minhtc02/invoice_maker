package com.example.invoice_maker_app.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.invoice_maker_app.dao.BusinessDAO;
import com.example.invoice_maker_app.dao.ClientDAO;
import com.example.invoice_maker_app.dao.DiscountDAO;
import com.example.invoice_maker_app.dao.InvoiceDAO;
import com.example.invoice_maker_app.dao.ItemDAO;
import com.example.invoice_maker_app.dao.ItemsDAO;
import com.example.invoice_maker_app.dao.PaymentMethodDAO;
import com.example.invoice_maker_app.dao.TaxDAO;
import com.example.invoice_maker_app.dao.TermDAO;
import com.example.invoice_maker_app.model.Business;
import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Discount;
import com.example.invoice_maker_app.model.Invoice;
import com.example.invoice_maker_app.model.Item;
import com.example.invoice_maker_app.model.Items;
import com.example.invoice_maker_app.model.PaymentMethod;
import com.example.invoice_maker_app.model.Tax;
import com.example.invoice_maker_app.model.Term;

@Database(entities = {Invoice.class, Business.class, Client.class, Discount.class, Item.class, Items.class, PaymentMethod.class, Tax.class, Term.class}, version = 1)
public abstract class InvoiceDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "invoice.db";
    private static InvoiceDatabase instance;

    public static synchronized InvoiceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), InvoiceDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract InvoiceDAO invoiceDAO();

    public abstract BusinessDAO businessDAO();

    public abstract ClientDAO clientDAO();

    public abstract DiscountDAO discountDAO();

    public abstract ItemDAO itemDAO();

    public abstract ItemsDAO itemsDAO();

    public abstract PaymentMethodDAO paymentMethodDAO();

    public abstract TaxDAO taxDAO();

    public abstract TermDAO termDAO();

}
