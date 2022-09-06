package com.example.invoice_maker_app.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invoice_maker_app.ListInvoice;
import com.example.invoice_maker_app.databinding.ActivityInvoiceInfoBinding;
import com.example.invoice_maker_app.model.Invoice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InvoiceInfoActivity extends AppCompatActivity {
    ActivityInvoiceInfoBinding binding;
    Invoice invoice;
    String startDate, endDate;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();
    Calendar calendar1 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInvoiceInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setClick();
        initalizeView();
    }

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.cvCreationDate.setOnClickListener(v -> {

            DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year, month, dayOfMonth);
                    binding.tvCreationDate.setText(dateFormat.format(calendar.getTime()));
                    setDiff();
                }
            }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DATE));
            pickerDialog.show();

        });
        binding.cvDueDate.setOnClickListener(v -> {
            DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar1.set(year, month, dayOfMonth);
                    binding.tvDueDate.setText(dateFormat.format(calendar1.getTime()));
                    setDiff();
                }
            }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DATE));
            Date date1 = null;
            startDate = binding.tvCreationDate.getText().toString().trim();
            try {
                date1 = dateFormat.parse(startDate);
                calendar.setTime(date1);
                pickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            pickerDialog.show();
        });

        binding.spDueTerms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.tvDueTerm.setText(ListInvoice.listsp.get(position));
                if (position > 0) {
                    if (position < 9) {
                        setDueDate(position - 1);

                    } else if (position == 9) {
                        setDueDate(30);
                    } else if (position == 10) {
                        setDueDate(90);
                    } else if (position == 11) {
                        setDueDate(180);
                    } else {
                        DatePickerDialog pickerDialog = new DatePickerDialog(InvoiceInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                binding.tvDueDate.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                                setDiff();
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                        Date date1 = null;
                        startDate = binding.tvCreationDate.getText().toString().trim();
                        try {
                            date1 = dateFormat.parse(startDate);
                            calendar1.setTime(date1);
                            pickerDialog.getDatePicker().setMinDate(calendar1.getTimeInMillis());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pickerDialog.show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.tvDueTerm.setText(ListInvoice.listsp.get(0));
            }
        });
        binding.btnDone.setOnClickListener(v -> {
            invoice.setNumber(binding.edInvoiceNumber.getText().toString().trim());
            invoice.setCreateDate(binding.tvCreationDate.getText().toString().trim());
            invoice.setDueTerms(binding.tvDueTerm.getText().toString().trim());
            invoice.setDueDate(binding.tvDueDate.getText().toString().trim());
            invoice.setPo(binding.edPO.getText().toString().trim());

            Intent intent = new Intent();
            intent.putExtra("invoice_edit", invoice);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

    }

    private void setDueDate(int day) {
        Calendar calendar = Calendar.getInstance();
        Date date1 = null;
        startDate = binding.tvCreationDate.getText().toString().trim();

        try {
            date1 = dateFormat.parse(startDate);
            calendar.setTime(date1);
            calendar.add(Calendar.DATE, day);
            binding.tvDueDate.setText(dateFormat.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDiff() {
        Date date1 = null;
        Date date2 = null;
        try {
            startDate = binding.tvCreationDate.getText().toString();
            endDate = binding.tvDueDate.getText().toString();
            date1 = dateFormat.parse(startDate);
            date2 = dateFormat.parse(endDate);

            long getDiff = date2.getTime() - date1.getTime();

            if (getDiff < 0) {
                endDate = startDate;
                binding.tvDueDate.setText(startDate);
                getDiff = 0;
            }

            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
            if (getDaysDiff == 0) {
                binding.tvDueTerm.setText(getDaysDiff + " days");
            } else if (getDaysDiff == 1) {
                binding.tvDueTerm.setText("Next day");
            } else {
                binding.tvDueTerm.setText(getDaysDiff + " days");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initalizeView() {
        invoice = (Invoice) getIntent().getSerializableExtra("invoice_edit");
        if (invoice != null) {
            binding.edInvoiceNumber.setText(invoice.getNumber());
            binding.tvCreationDate.setText(invoice.getCreateDate());
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                binding.tvDueTerm.setText(invoice.getDueTerms());
                binding.tvDueDate.setText(invoice.getDueDate());

            }, 300);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListInvoice.listsp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spDueTerms.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}