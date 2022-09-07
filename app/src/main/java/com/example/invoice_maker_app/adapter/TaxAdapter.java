package com.example.invoice_maker_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.invoice_maker_app.databinding.ItemTaxBinding;
import com.example.invoice_maker_app.interfaces.TaxClick;
import com.example.invoice_maker_app.model.Invoice;
import com.example.invoice_maker_app.model.Tax;

import java.util.List;

public class TaxAdapter extends RecyclerView.Adapter<TaxAdapter.ViewHolder> {
    private List<Tax> list;
    private final Context context;
    private final TaxClick taxClick;

    public TaxAdapter(List<Tax> list, Context context, TaxClick taxClick) {
        this.list = list;
        this.context = context;
        this.taxClick = taxClick;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaxBinding binding = ItemTaxBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position), position);

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
        ItemTaxBinding binding;


        public ViewHolder(ItemTaxBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Tax tax, int position) {


            if (tax.isSelected()) {
                binding.checkbox.setChecked(true);
            } else if (!tax.isSelected()) {
                binding.checkbox.setChecked(false);
            }
            binding.tvName.setText(tax.getName() + "(" + tax.getRate() + "%)");
            binding.cvTax.setOnClickListener(v -> taxClick.clickTax(tax, position));
            binding.btnDelete.setOnClickListener(v -> taxClick.deleteTax(tax));
            binding.btnEdit.setOnClickListener(v -> taxClick.editTax(tax));
        }
    }
}
