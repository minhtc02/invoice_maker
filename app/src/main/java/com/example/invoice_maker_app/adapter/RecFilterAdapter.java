package com.example.invoice_maker_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoice_maker_app.R;
import com.example.invoice_maker_app.databinding.ItemRecFilterBinding;
import com.example.invoice_maker_app.interfaces.RecFilterClick;
import com.example.invoice_maker_app.model.RecFilter;

import java.util.List;

public class RecFilterAdapter extends RecyclerView.Adapter<RecFilterAdapter.ViewHolder> {
    private final List<RecFilter> list;
    private final Context context;
    private final RecFilterClick recFilterClick;

    public RecFilterAdapter(List<RecFilter> list, Context context, RecFilterClick recFilterClick) {
        this.list = list;
        this.context = context;
        this.recFilterClick = recFilterClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecFilterBinding binding = ItemRecFilterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecFilterBinding binding;

        public ViewHolder(ItemRecFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(RecFilter recFilter) {
            if (recFilter.isSelected()) {
                binding.tvType.setTextColor(ContextCompat.getColor(context, R.color.white));
                binding.tvType.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_rec_filter_on));
            } else {
                binding.tvType.setTextColor(ContextCompat.getColor(context, R.color.color_91B2DB));
                binding.tvType.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_rec_filter_off));
            }
            binding.tvType.setText(recFilter.getType());
            binding.cvType.setOnClickListener(v -> {
                recFilterClick.clickType(recFilter);
            });
        }
    }
}
