package com.example.invoice_maker_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.invoice_maker_app.R;
import com.example.invoice_maker_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}