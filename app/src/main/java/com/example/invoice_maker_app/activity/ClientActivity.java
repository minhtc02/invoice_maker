package com.example.invoice_maker_app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.adapter.ClientAdapter;
import com.example.invoice_maker_app.database.InvoiceDatabase;
import com.example.invoice_maker_app.databinding.ActivityClientBinding;
import com.example.invoice_maker_app.interfaces.ClientClick;
import com.example.invoice_maker_app.model.Client;

import java.util.List;

public class ClientActivity extends AppCompatActivity implements ClientClick {
    private static final int REQUEST_CODE = 1;
    ActivityClientBinding binding;
    List<Client> list;
    ClientAdapter clientAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initalizeView();
        setClick();
    }

    private void initalizeView() {
        list = InvoiceDatabase.getInstance(this).clientDAO().getAll();
        clientAdapter = new ClientAdapter(list, this, this);
        binding.recClient.setAdapter(clientAdapter);

    }

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.cvNewClient.setOnClickListener(v -> startActivity(new Intent(this, NewClientActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        list = InvoiceDatabase.getInstance(this).clientDAO().getAll();
        clientAdapter.setData(list);
    }
    @Override
    public void clickClient(Client client) {
        String isInvoice =(String) getIntent().getSerializableExtra("isInvoice");
        if (isInvoice!=null){
            Intent intent = new Intent();
            intent.putExtra("client", client);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void editClient(Client client) {
        Intent intent = new Intent(this, NewClientActivity.class);
        intent.putExtra("client", client);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void deleteClient(Client client) {
        InvoiceDatabase.getInstance(this).clientDAO().delete(client);
        updateView();
    }
}