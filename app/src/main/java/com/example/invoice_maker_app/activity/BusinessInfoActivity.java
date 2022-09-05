package com.example.invoice_maker_app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.database.InvoiceDatabase;
import com.example.invoice_maker_app.databinding.ActivityBusinessInfoBinding;
import com.example.invoice_maker_app.model.Business;

import java.util.List;
import java.util.Random;

public class BusinessInfoActivity extends AppCompatActivity {
    ActivityBusinessInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setClick();
    }

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnAddImage.setOnClickListener(v -> {
        });
        binding.btnDone.setOnClickListener(v -> {
            Random random = new Random();
            String id = System.currentTimeMillis() + "_" + random.nextInt(999);

            Business business = new Business();
            business.setId(id);
            business.setName(binding.edBusinessName.getText().toString().trim());
            business.setEmailAddress(binding.edEmailBusiness.getText().toString().trim());
            business.setPhoneNumber(binding.edPhoneNumber.getText().toString().trim());
            business.setBillingAddress(binding.edBillingAddress.getText().toString().trim());
            business.setWebsite(binding.edBusinessWebsite.getText().toString().trim());

            if (!checkExist(business)) {
                InvoiceDatabase.getInstance(this).businessDAO().insert(business);
            }

            Intent intent = new Intent();
            intent.putExtra("business", business);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }

    private boolean checkExist(Business business) {
        List<Business> list = InvoiceDatabase.getInstance(this).businessDAO().checkExist(business.getId());
        return list != null && !list.isEmpty();
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}