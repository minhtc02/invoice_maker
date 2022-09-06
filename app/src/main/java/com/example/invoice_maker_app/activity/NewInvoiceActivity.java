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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.ListInvoice;
import com.example.invoice_maker_app.database.InvoiceDatabase;
import com.example.invoice_maker_app.databinding.ActivityNewInvoiceBinding;
import com.example.invoice_maker_app.databinding.DialogDiscountBinding;
import com.example.invoice_maker_app.databinding.DialogShippingBinding;
import com.example.invoice_maker_app.model.Business;
import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Discount;
import com.example.invoice_maker_app.model.Invoice;
import com.example.invoice_maker_app.model.Tax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class NewInvoiceActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_DATE = 1;
    private static final int REQUEST_CODE_FROM = 2;
    private static final int REQUEST_CODE_TO = 3;
    private static final int REQUEST_CODE_TAX = 4;
    ActivityNewInvoiceBinding binding;
    DialogDiscountBinding dialogDiscountBinding;
    DialogShippingBinding dialogShippingBinding;
    Dialog dialog;
    AlertDialog.Builder builder;
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
    Invoice invoice;
    Business business;
    Client client;
    Discount discount;
    ArrayList<Tax> taxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initalizeView();
        setClick();
    }


    private void initalizeView() {
        taxList = new ArrayList<>();
        builder = new AlertDialog.Builder(this);
        invoice = (Invoice) getIntent().getSerializableExtra("invoice");
        if (invoice == null) {
            Random random = new Random();
            String number = "IVN" + sdf.format(Calendar.getInstance().getTime()) + "_" + sdf1.format(Calendar.getInstance().getTime()) + "_" + random.nextInt(999);
            String creationDate = sdf2.format(Calendar.getInstance().getTime());
            invoice = new Invoice(number, creationDate, "", "", "", "", "", "", "", "", "", "", taxList);
        }
        updateView();

    }

    private void updateView() {
        binding.tvCreatOn.setText(invoice.getCreateDate());
        binding.tvNumber.setText(invoice.getNumber());
        binding.tvDueOn.setText(invoice.getDueDate());
        if (!invoice.getBusinessId().equals("")) {
            List<Business> list = InvoiceDatabase.getInstance(this).businessDAO().checkExist(invoice.getBusinessId());
            business = list.get(0);
            binding.tvBusinessName.setText(business.getName());
        }
        if (!invoice.getClientId().equals("")) {
            List<Client> list = InvoiceDatabase.getInstance(this).clientDAO().checkExist(Integer.parseInt(invoice.getClientId()));
            client = list.get(0);
            binding.tvClientName.setText(client.getName());
        }

    }

    String id;

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.cvInVoice.setOnClickListener(v -> {
            Intent intent = new Intent(this, InvoiceInfoActivity.class);
            intent.putExtra("invoice_edit", invoice);
            startActivityForResult(intent, REQUEST_CODE_DATE);
        });
        binding.cvFrom.setOnClickListener(v -> {
            Intent intent = new Intent(this, BusinessInfoActivity.class);
            startActivityForResult(intent, REQUEST_CODE_FROM);
        });
        binding.cvBillTo.setOnClickListener(v -> {
            Intent intent = new Intent(this, ClientActivity.class);
            intent.putExtra("isInvoice", "yes");
            startActivityForResult(intent, REQUEST_CODE_TO);
        });
        binding.cvItems.setOnClickListener(v -> startActivity(new Intent(this, ItemsActivity.class)));
        binding.cvDiscount.setOnClickListener(v -> {
            discountClick();
        });
        binding.cvTax.setOnClickListener(v -> {
            Intent intent = new Intent(this, TaxActivity.class);
            intent.putExtra("tax", invoice);
            startActivityForResult(intent, REQUEST_CODE_TAX);
        });
        binding.cvShipping.setOnClickListener(v -> {
            dialogShippingBinding = DialogShippingBinding.inflate(LayoutInflater.from(this));
            dialogShippingBinding.edShipping.setText(invoice.getShipping());
            dialogShippingBinding.btnOK.setOnClickListener(v1 -> {
                invoice.setShipping(dialogShippingBinding.edShipping.getText().toString().trim());
                dialog.cancel();
            });
            dialogShippingBinding.btnCancel.setOnClickListener(v1 -> dialog.cancel());
            builder.setView(dialogShippingBinding.getRoot());
            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.show();
        });
        binding.cvTerm.setOnClickListener(v -> startActivity(new Intent(this, TermsAndConditionsActivity.class)));
        binding.cvPaymentMethod.setOnClickListener(v -> startActivity(new Intent(this, PaymentMethodActivity.class)));
        binding.cvAttachments.setOnClickListener(v -> startActivity(new Intent(this, AttachmentsActivity.class)));
        binding.btnPreview.setOnClickListener(v -> {
            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
        });
        binding.btnSave.setOnClickListener(v -> {
            if (checkExist(invoice)) {
                InvoiceDatabase.getInstance(this).invoiceDAO().updateInvoice(invoice);
            } else {
                InvoiceDatabase.getInstance(this).invoiceDAO().insertInvoice(invoice);
            }
            onBackPressed();
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DATE && resultCode == Activity.RESULT_OK) {
            invoice = (Invoice) data.getSerializableExtra("invoice_edit");
            updateView();
        } else if (requestCode == REQUEST_CODE_FROM && resultCode == Activity.RESULT_OK) {
            business = (Business) data.getSerializableExtra("business");
            invoice.setBusinessId(business.getId());
            updateView();
        } else if (requestCode == REQUEST_CODE_TO && resultCode == Activity.RESULT_OK) {
            client = (Client) data.getSerializableExtra("client");
            invoice.setClientId(String.valueOf(client.getId()));
            updateView();
        } else if (requestCode == REQUEST_CODE_TAX && resultCode == Activity.RESULT_OK) {
            Log.d("AAA", "ls: " + ListInvoice.listTaxSelected.size());
            invoice.setListTax(ListInvoice.listTaxSelected);
            ListInvoice.listTaxSelected.clear();
            Log.d("AAA", "lsi: " + invoice.getListTax().size());
        }
    }

    private boolean checkExist(Invoice invoice) {
        List<Invoice> invoiceList = InvoiceDatabase.getInstance(this).invoiceDAO().checkExist(invoice.getNumber());
        return invoiceList != null && !invoiceList.isEmpty();
    }

    private boolean checkExistDiscount(Discount discount) {
        List<Discount> list = InvoiceDatabase.getInstance(this).discountDAO().checkExist(discount.getId());
        return list != null && !list.isEmpty();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "lsi resume: " + invoice.getListTax().size());

    }

    private void discountClick() {
        dialogDiscountBinding = DialogDiscountBinding.inflate(LayoutInflater.from(this));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListInvoice.listspDiscount);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dialogDiscountBinding.spDiscount.setAdapter(adapter);

        if (invoice.getDiscountId().equals("")) {
            Random random = new Random();
            invoice.setDiscountId(String.valueOf(random.nextInt(999)));
            discount = new Discount(invoice.getDiscountId(), "0", "%");
        } else {
            List<Discount> list = InvoiceDatabase.getInstance(this).discountDAO().checkExist(invoice.getDiscountId());
            discount = list.get(0);
            dialogDiscountBinding.edDiscount.setText(discount.getName());
            if (discount.getType().equals("%")) {
                dialogDiscountBinding.spDiscount.setSelection(0);
            } else {
                dialogDiscountBinding.spDiscount.setSelection(1);
            }
        }


        dialogDiscountBinding.btnOK.setOnClickListener(v1 -> {
            discount.setName(dialogDiscountBinding.edDiscount.getText().toString().trim());
            discount.setType(dialogDiscountBinding.tvType.getText().toString().trim());

            if (checkExistDiscount(discount)) {
                InvoiceDatabase.getInstance(this).discountDAO().update(discount);
            } else {
                InvoiceDatabase.getInstance(this).discountDAO().insert(discount);
            }
            dialog.cancel();
        });

        dialogDiscountBinding.btnCancel.setOnClickListener(v1 -> {
            dialog.cancel();
        });

        dialogDiscountBinding.spDiscount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    dialogDiscountBinding.tvType.setText("%");
                } else {
                    dialogDiscountBinding.tvType.setText("$");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setView(dialogDiscountBinding.getRoot());
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}