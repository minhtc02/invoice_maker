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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.invoice_maker_app.ListInvoice;
import com.example.invoice_maker_app.activity.AllInvoiceActivity;
import com.example.invoice_maker_app.activity.NewInvoiceActivity;
import com.example.invoice_maker_app.adapter.RecFilterAdapter;
import com.example.invoice_maker_app.databinding.DialogFilterBinding;
import com.example.invoice_maker_app.databinding.FragmentEstimateBinding;
import com.example.invoice_maker_app.interfaces.RecFilterClick;
import com.example.invoice_maker_app.model.RecFilter;

import java.util.Calendar;


public class EstimateFragment extends Fragment implements RecFilterClick {
    FragmentEstimateBinding fragmentEstimateBinding;
    DialogFilterBinding dialogFilterBinding;
    AllInvoiceActivity allInvoiceActivity;
    RecFilterAdapter recFilterAdapter;
    Context context;
    Dialog dialog;
    AlertDialog.Builder builder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentEstimateBinding = FragmentEstimateBinding.inflate(inflater);
        initalizeView();
        setClick();
        return fragmentEstimateBinding.getRoot();

    }

    private void initalizeView() {
        for (int i = 0; i < ListInvoice.recFilterEstimateList.size(); i++) {
            ListInvoice.recFilterEstimateList.get(i).setSelected(false);
        }
        ListInvoice.recFilterEstimateList.get(0).setSelected(true);
        allInvoiceActivity = (AllInvoiceActivity) getActivity();
        context = allInvoiceActivity.getApplicationContext();
        recFilterAdapter = new RecFilterAdapter(ListInvoice.recFilterEstimateList, context, this);
        fragmentEstimateBinding.recFilter.setAdapter(recFilterAdapter);
        builder = new AlertDialog.Builder(getContext());
    }

    private void setClick() {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        fragmentEstimateBinding.btnAdd.setOnClickListener(v -> startActivity(new Intent(context, NewInvoiceActivity.class)));
        fragmentEstimateBinding.btnFilter.setOnClickListener(v -> {
            dialogFilterBinding = DialogFilterBinding.inflate(LayoutInflater.from(this.getContext()));
            dialogFilterBinding.btnDateFrom.setOnClickListener(v1 -> {
                DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
                    dialogFilterBinding.tvDateFrom.setText(dayOfMonth + "/" + month + "/" + year);
                    calendar1.set(year, month, dayOfMonth);

                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DATE));
                pickerDialog.show();

            });
            dialogFilterBinding.btnDateTo.setOnClickListener(v1 -> {
                DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dialogFilterBinding.tvDateTo.setText(dayOfMonth + "/" + month + "/" + year);
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
    public void clickType(RecFilter recFilter) {
        for (int i = 0; i < ListInvoice.recFilterEstimateList.size(); i++) {
            ListInvoice.recFilterEstimateList.get(i).setSelected(false);
        }
        recFilter.setSelected(true);
        recFilterAdapter.notifyDataSetChanged();
    }
}