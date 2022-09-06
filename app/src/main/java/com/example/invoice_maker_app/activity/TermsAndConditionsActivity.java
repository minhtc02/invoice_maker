package com.example.invoice_maker_app.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.databinding.ActivityTermsAndConditionsBinding;

public class TermsAndConditionsActivity extends AppCompatActivity {
    ActivityTermsAndConditionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsAndConditionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}