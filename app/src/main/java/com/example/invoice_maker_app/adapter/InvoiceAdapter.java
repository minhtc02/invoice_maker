package com.example.invoice_maker_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoice_maker_app.databinding.ItemRecInvoiceBinding;
import com.example.invoice_maker_app.interfaces.InvoiceClick;
import com.example.invoice_maker_app.model.Invoice;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {
    private List<Invoice> list;
    private final Context context;
    private final InvoiceClick recInvoiceClick;

    public InvoiceAdapter(List<Invoice> list, Context context, InvoiceClick recInvoiceClick) {
        this.list = list;
        this.context = context;
        this.recInvoiceClick = recInvoiceClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecInvoiceBinding binding = ItemRecInvoiceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    public void setData(List<Invoice> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecInvoiceBinding binding;

        public ViewHolder(ItemRecInvoiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Invoice invoice) {
            binding.tvNumber.setText(invoice.getNumber());
            binding.tvCreationDate.setText(invoice.getCreateDate());
            binding.cvInvoice.setOnClickListener(v -> {
                recInvoiceClick.clickInvoice(invoice);
            });
            binding.cvInvoice.setOnLongClickListener(v -> {
                recInvoiceClick.longClickInvoice(invoice);
                return false;
            });
        }
    }
}
