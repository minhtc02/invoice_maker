package com.example.invoice_maker_app.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.adapter.ClientAdapter;
import com.example.invoice_maker_app.adapter.ItemAdapter;
import com.example.invoice_maker_app.database.InvoiceDatabase;
import com.example.invoice_maker_app.databinding.ActivityClientBinding;
import com.example.invoice_maker_app.databinding.ActivityItemsBinding;
import com.example.invoice_maker_app.interfaces.ItemClick;
import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Item;

import java.util.List;

public class ItemsActivity extends AppCompatActivity implements ItemClick {
    ActivityItemsBinding binding;
    private static final int REQUEST_CODE = 1;
    List<Item> list;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initalizeView();
        setClick();
    }

    private void initalizeView() {
        list = InvoiceDatabase.getInstance(this).itemDAO().getAll();
        itemAdapter = new ItemAdapter(list, this, this);
        binding.rexItem.setAdapter(itemAdapter);
    }

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.cvNewItem.setOnClickListener(v -> {

        });
        binding.btnSearch.setOnClickListener(v -> {});
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void clickItem(Item item) {

    }

    @Override
    public void editItem(Item item) {

    }

    @Override
    public void deleteItem(Item item) {

    }
}