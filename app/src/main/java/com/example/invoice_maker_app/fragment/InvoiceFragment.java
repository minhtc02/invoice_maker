package com.example.invoice_maker_app.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import com.example.invoice_maker_app.ListInvoice;
import com.example.invoice_maker_app.activity.AllInvoiceActivity;
import com.example.invoice_maker_app.activity.NewInvoiceActivity;
import com.example.invoice_maker_app.adapter.RecFilterAdapter;
import com.example.invoice_maker_app.adapter.InvoiceAdapter;
import com.example.invoice_maker_app.database.InvoiceDatabase;
import com.example.invoice_maker_app.databinding.DialogDeleteInvoiceBinding;
import com.example.invoice_maker_app.databinding.DialogFilterBinding;
import com.example.invoice_maker_app.databinding.FragmentInvoiceBinding;
import com.example.invoice_maker_app.interfaces.RecFilterClick;
import com.example.invoice_maker_app.interfaces.InvoiceClick;
import com.example.invoice_maker_app.model.Invoice;
import com.example.invoice_maker_app.model.RecFilter;

import java.util.Calendar;
import java.util.List;


public class InvoiceFragment extends Fragment implements InvoiceClick, RecFilterClick {
    FragmentInvoiceBinding fragmentInvoiceBinding;
    DialogFilterBinding dialogFilterBinding;
    AllInvoiceActivity allInvoiceActivity;
    RecFilterAdapter recFilterAdapter;
    InvoiceAdapter invoiceAdapter;
    Context context;
    Dialog dialog;
    AlertDialog.Builder builder;
    List<Invoice> invoiceList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentInvoiceBinding = FragmentInvoiceBinding.inflate(inflater);
        initalizeView();
        setClick();
        return fragmentInvoiceBinding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    private void initalizeView() {
        allInvoiceActivity = (AllInvoiceActivity) getActivity();
        context = allInvoiceActivity.getApplicationContext();

        builder = new AlertDialog.Builder(getContext());

        for (int i = 0; i < ListInvoice.recFilterInvoiceList.size(); i++) {
            ListInvoice.recFilterInvoiceList.get(i).setSelected(false);
        }
        ListInvoice.recFilterInvoiceList.get(0).setSelected(true);
        recFilterAdapter = new RecFilterAdapter(ListInvoice.recFilterInvoiceList, context, this);
        fragmentInvoiceBinding.recFilter.setAdapter(recFilterAdapter);

        invoiceAdapter = new InvoiceAdapter(invoiceList, context, this);
        fragmentInvoiceBinding.recInvoice.setAdapter(invoiceAdapter);
        invoiceAdapter.setData(invoiceList);
    }

    private void updateView() {
        invoiceList = InvoiceDatabase.getInstance(context).invoiceDAO().getAll();
        invoiceAdapter.setData(invoiceList);
    }


    private void setClick() {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        fragmentInvoiceBinding.btnAdd.setOnClickListener(v -> startActivity(new Intent(context, NewInvoiceActivity.class)));
        fragmentInvoiceBinding.btnFilter.setOnClickListener(v -> {
            dialogFilterBinding = DialogFilterBinding.inflate(LayoutInflater.from(this.getContext()));
            dialogFilterBinding.btnDateFrom.setOnClickListener(v1 -> {
                DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dialogFilterBinding.tvDateFrom.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        calendar1.set(year, month, dayOfMonth);

                    }
                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DATE));
                pickerDialog.show();

            });
            dialogFilterBinding.btnDateTo.setOnClickListener(v1 -> {
                DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dialogFilterBinding.tvDateTo.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                pickerDialog.getDatePicker().setMinDate(calendar1.getTimeInMillis());
                pickerDialog.show();
            });
            dialogFilterBinding.btnApply.setOnClickListener(v1 -> {
            });
            dialogFilterBinding.btnCancel.setOnClickListener(v1 -> dialog.cancel());
            builder.setView(dialogFilterBinding.getRoot());
            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.show();
        });
    }

    @Override
    public void clickInvoice(Invoice invoice) {
        Intent intent = new Intent(context, NewInvoiceActivity.class);
        intent.putExtra("invoice", invoice);
        getContext().startActivity(intent);
    }

    @Override
    public void longClickInvoice(Invoice invoice) {
        DialogDeleteInvoiceBinding dialogDeleteInvoiceBinding = DialogDeleteInvoiceBinding.inflate(LayoutInflater.from(this.getContext()));
        dialogDeleteInvoiceBinding.btnOK.setOnClickListener(v1 -> {
            InvoiceDatabase.getInstance(context).invoiceDAO().deleteInvoice(invoice);
            updateView();
            dialog.cancel();
        });
        dialogDeleteInvoiceBinding.btnCancel.setOnClickListener(v1 -> dialog.cancel());
        builder.setView(dialogDeleteInvoiceBinding.getRoot());
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    @Override
    public void clickType(RecFilter recFilter) {
        for (int i = 0; i < ListInvoice.recFilterInvoiceList.size(); i++) {
            ListInvoice.recFilterInvoiceList.get(i).setSelected(false);
        }
        recFilter.setSelected(true);
        recFilterAdapter.notifyDataSetChanged();
    }
}