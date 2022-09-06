package com.example.invoice_maker_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoice_maker_app.databinding.ItemClientBinding;
import com.example.invoice_maker_app.interfaces.ItemClick;
import com.example.invoice_maker_app.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> list;
    private final Context context;
    private final ItemClick itemClick;

    public ItemAdapter(List<Item> list, Context context, ItemClick itemClick) {
        this.list = list;
        this.context = context;
        this.itemClick = itemClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemClientBinding binding = ItemClientBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    public void setData(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemClientBinding binding;

        public ViewHolder(ItemClientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Item item) {
            binding.tvName.setText(item.getName());
            binding.tvPhoneNumber.setText(item.getPrice());
            binding.tvDetail.setText(item.getDescription());
            binding.cvClient.setOnClickListener(v -> itemClick.clickItem(item));
            binding.btnDelete.setOnClickListener(v -> itemClick.deleteItem(item));
            binding.btnEdit.setOnClickListener(v -> itemClick.editItem(item));
        }
    }
}
