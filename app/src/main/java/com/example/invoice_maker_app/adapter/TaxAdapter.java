package com.example.invoice_maker_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoice_maker_app.ListInvoice;
import com.example.invoice_maker_app.databinding.ItemPaymentMethodBinding;
import com.example.invoice_maker_app.interfaces.TaxClick;
import com.example.invoice_maker_app.model.Invoice;
import com.example.invoice_maker_app.model.Tax;

import java.util.List;

public class TaxAdapter extends RecyclerView.Adapter<TaxAdapter.ViewHolder> {
    private List<Tax> list;
    private final Context context;
    private final TaxClick taxClick;
    Invoice invoice;

    public TaxAdapter(List<Tax> list, Invoice invoice, Context context, TaxClick taxClick) {
        this.list = list;
        this.invoice = invoice;
        this.context = context;
        this.taxClick = taxClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPaymentMethodBinding binding = ItemPaymentMethodBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    public void setData(List<Tax> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPaymentMethodBinding binding;

        public ViewHolder(ItemPaymentMethodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Tax tax) {
            if (invoice.getListTax() != null) {
                for (int i = 0; i < invoice.getListTax().size(); i++) {
                    if (tax.getId() == invoice.getListTax().get(i).getId()) {
                        binding.checkbox.setChecked(true);
                    }
                }
            }
            binding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    ListInvoice.listTaxSelected.add(tax);
                } else {
                    ListInvoice.listTaxSelected.remove(tax);
                }
            });
            binding.tvName.setText(tax.getName() + "(" + tax.getRate() + "%)");
            binding.cvTax.setOnClickListener(v -> taxClick.clickTax(tax));
            binding.btnDelete.setOnClickListener(v -> taxClick.deleteTax(tax));
            binding.btnEdit.setOnClickListener(v -> taxClick.editTax(tax));
        }
    }
}
