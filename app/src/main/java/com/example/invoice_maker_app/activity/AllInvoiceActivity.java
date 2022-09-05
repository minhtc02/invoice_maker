package com.example.invoice_maker_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.invoice_maker_app.ListInvoice;
import com.example.invoice_maker_app.R;
import com.example.invoice_maker_app.databinding.ActivityAllInvoiceBinding;
import com.example.invoice_maker_app.fragment.EstimateFragment;
import com.example.invoice_maker_app.fragment.InvoiceFragment;

public class AllInvoiceActivity extends AppCompatActivity {
    ActivityAllInvoiceBinding binding;
    InvoiceFragment invoiceFragment;
    EstimateFragment estimateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initalizeView();
        setClick();
    }


    private void setClick() {

        binding.btnNavigate.setOnClickListener(v -> binding.drawerLayout.openDrawer(Gravity.LEFT));
        binding.btnInvoice.setOnClickListener(v -> {
            replaceFragment(invoiceFragment);
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        });
        binding.btnEstimate.setOnClickListener(v -> {
            replaceFragment(estimateFragment);
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        });
        binding.btnClient.setOnClickListener(v -> {
            startActivity(new Intent(this, ClientActivity.class));
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        });
        binding.btnItem.setOnClickListener(v -> {
            startActivity(new Intent(this, ItemsActivity.class));
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        });


    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initalizeView() {
        ListInvoice.createList();
        invoiceFragment = new InvoiceFragment();
        estimateFragment = new EstimateFragment();

        replaceFragment(invoiceFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }


}