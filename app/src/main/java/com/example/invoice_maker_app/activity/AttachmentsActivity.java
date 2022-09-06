package com.example.invoice_maker_app.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.databinding.ActivityAttachmentsBinding;
import com.example.invoice_maker_app.databinding.DialogAddImageBinding;
import com.example.invoice_maker_app.databinding.DialogDiscountBinding;
import com.example.invoice_maker_app.databinding.DialogShippingBinding;

public class AttachmentsActivity extends AppCompatActivity {
    ActivityAttachmentsBinding binding;
    DialogAddImageBinding dialogAddImageBinding;
    Dialog dialog;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttachmentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initalizeView();
        setClick();

    }
    private void initalizeView() {
        builder = new AlertDialog.Builder(this);
        dialogAddImageBinding = DialogAddImageBinding.inflate(LayoutInflater.from(this));
    }
    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnDone.setOnClickListener(v -> onBackPressed());
        binding.cvNewAttachment.setOnClickListener(v -> {
            dialogAddImageBinding.btnCancel.setOnClickListener(v1 -> dialog.cancel());
            builder.setView(dialogAddImageBinding.getRoot());
            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.show();
        });
    }

}