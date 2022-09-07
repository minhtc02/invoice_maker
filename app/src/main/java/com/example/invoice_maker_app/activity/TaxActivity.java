package com.example.invoice_maker_app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.adapter.TaxAdapter;
import com.example.invoice_maker_app.database.InvoiceDatabase;
import com.example.invoice_maker_app.databinding.ActivityTaxBinding;
import com.example.invoice_maker_app.databinding.DialogTaxBinding;
import com.example.invoice_maker_app.interfaces.TaxClick;
import com.example.invoice_maker_app.model.Invoice;
import com.example.invoice_maker_app.model.Tax;

import java.util.List;

public class TaxActivity extends AppCompatActivity implements TaxClick {
    private static final int REQUEST_CODE = 1;
    ActivityTaxBinding binding;
    List<Tax> list;
    TaxAdapter taxAdapter;
    DialogTaxBinding dialogTaxBinding;
    Dialog dialog;
    AlertDialog.Builder builder;
    Tax tax;
    Invoice invoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initalizeView();
        setClick();
    }


    private void initalizeView() {
        invoice = (Invoice) getIntent().getSerializableExtra("tax");
        builder = new AlertDialog.Builder(this);
        list = InvoiceDatabase.getInstance(this).taxDAO().getAll();
        for (Tax tax : list) {
            for (Tax invoiceTax : invoice.getListTax()) {
                if (tax.getName().equals(invoiceTax.getName())) {
                    tax.setSelected(true);
                }
            }
        }
        taxAdapter = new TaxAdapter(list, this, this);
        binding.recTax.setAdapter(taxAdapter);

    }

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.cvNewTax.setOnClickListener(v -> {
            dialogTaxBinding = DialogTaxBinding.inflate(LayoutInflater.from(this));
            tax = new Tax("", "", false);
            dialogTaxBinding.edTaxName.setText(tax.getName());
            dialogTaxBinding.edTaxRate.setText(tax.getRate());

            dialogTaxBinding.btnOK.setOnClickListener(v1 -> {
                tax.setName(dialogTaxBinding.edTaxName.getText().toString().trim());
                tax.setRate(dialogTaxBinding.edTaxRate.getText().toString().trim());

                if (checkExist(tax)) {
                    InvoiceDatabase.getInstance(this).taxDAO().update(tax);
                } else {
                    InvoiceDatabase.getInstance(this).taxDAO().insert(tax);
                }
                updateView();
                dialog.cancel();
            });

            dialogTaxBinding.btnCancel.setOnClickListener(v1 -> {
                dialog.cancel();
            });
            builder.setView(dialogTaxBinding.getRoot());
            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.show();
        });
        binding.btnDone.setOnClickListener(v -> {
            invoice.getListTax().clear();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isSelected()) {
                    invoice.getListTax().add(list.get(i));
                }
            }

            Intent intent = new Intent();
            intent.putExtra("invoice_tax", invoice);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }

    private boolean checkExist(Tax tax) {
        List<Tax> list = InvoiceDatabase.getInstance(this).taxDAO().checkExist(tax.getId());
        return list != null && !list.isEmpty();
    }

    private void updateView() {
        list = InvoiceDatabase.getInstance(this).taxDAO().getAll();
        for (Tax tax : list) {
            for (Tax invoiceTax : invoice.getListTax()) {
                if (tax.getName().equals(invoiceTax.getName())) {
                    tax.setSelected(true);
                }
            }
        }
        taxAdapter.setData(list);
    }

    @Override
    public void clickTax(Tax tax, int position) {
        if (!tax.isSelected()) {
            list.get(position).setSelected(true);

        } else {
            list.get(position).setSelected(false);
        }
        Log.d("AAA", "" + tax.isSelected());
        taxAdapter.notifyDataSetChanged();
    }

    @Override
    public void editTax(Tax tax) {
        dialogTaxBinding = DialogTaxBinding.inflate(LayoutInflater.from(this));
        dialogTaxBinding.edTaxName.setText(tax.getName());
        dialogTaxBinding.edTaxRate.setText(tax.getRate());
        dialogTaxBinding.btnOK.setOnClickListener(v1 -> {
            tax.setName(dialogTaxBinding.edTaxName.getText().toString().trim());
            tax.setRate(dialogTaxBinding.edTaxRate.getText().toString().trim());

            if (checkExist(tax)) {
                InvoiceDatabase.getInstance(this).taxDAO().update(tax);
            } else {
                InvoiceDatabase.getInstance(this).taxDAO().insert(tax);
            }
            updateView();

            dialog.cancel();
        });

        dialogTaxBinding.btnCancel.setOnClickListener(v1 -> {
            dialog.cancel();
        });
        builder.setView(dialogTaxBinding.getRoot());
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    @Override
    public void deleteTax(Tax tax) {
        InvoiceDatabase.getInstance(this).taxDAO().delete(tax);
        updateView();
    }
}