package com.example.invoice_maker_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoice_maker_app.databinding.ItemRecClientBinding;
import com.example.invoice_maker_app.databinding.ItemRecInvoiceBinding;
import com.example.invoice_maker_app.interfaces.ClientClick;
import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Invoice;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    private List<Client> list;
    private final Context context;
    private final ClientClick clickClient;

    public ClientAdapter(List<Client> list, Context context, ClientClick clickClient) {
        this.list = list;
        this.context = context;
        this.clickClient = clickClient;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecClientBinding binding = ItemRecClientBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    public void setData(List<Client> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecClientBinding binding;

        public ViewHolder(ItemRecClientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Client client) {
            binding.tvName.setText(client.getName());
            binding.tvPhoneNumber.setText(client.getPhoneNumber());
            binding.tvDetail.setText(client.getDetail());
            binding.cvClient.setOnClickListener(v -> clickClient.clickClient(client));
            binding.btnDelete.setOnClickListener(v -> clickClient.deleteClient(client));
            binding.btnEdit.setOnClickListener(v -> clickClient.editClient(client));
        }
    }
}
