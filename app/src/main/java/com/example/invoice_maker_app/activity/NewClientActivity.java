package com.example.invoice_maker_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.invoice_maker_app.R;
import com.example.invoice_maker_app.database.InvoiceDatabase;
import com.example.invoice_maker_app.databinding.ActivityNewClientBinding;
import com.example.invoice_maker_app.model.Business;
import com.example.invoice_maker_app.model.Client;

import java.util.List;

public class NewClientActivity extends AppCompatActivity {
    ActivityNewClientBinding binding;
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initalizeView();
        setClick();
    }

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnDone.setOnClickListener(v -> {
            client.setName(binding.edBusinessName.getText().toString().trim());
            client.setEmailAddress(binding.edEmailAddress.getText().toString().trim());
            client.setPhoneNumber(binding.edPhoneNumber.getText().toString().trim());
            client.setBillingAddress(binding.edBillingAddress.getText().toString().trim());
            client.setShippingAddress(binding.edShippingAddress.getText().toString().trim());
            client.setDetail(binding.edDetail.getText().toString().trim());
            if (checkExist(client)) {
                InvoiceDatabase.getInstance(this).clientDAO().update(client);
            } else {
                InvoiceDatabase.getInstance(this).clientDAO().insert(client);
            }
            onBackPressed();
        });
    }
    private boolean checkExist(Client client) {
        List<Client> list = InvoiceDatabase.getInstance(this).clientDAO().checkExist(client.getId());
        return list != null && !list.isEmpty();
    }

    private void initalizeView() {
        client = (Client) getIntent().getSerializableExtra("client");
        if (client==null){
            client = new Client("","","","","","");
        }
        updateView();

    }
    private void updateView(){
        binding.edBusinessName.setText(client.getName());
        binding.edEmailAddress.setText(client.getEmailAddress());
        binding.edPhoneNumber.setText(client.getPhoneNumber());
        binding.edBillingAddress.setText(client.getBillingAddress());
        binding.edShippingAddress.setText(client.getShippingAddress());
        binding.edDetail.setText(client.getDetail());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}