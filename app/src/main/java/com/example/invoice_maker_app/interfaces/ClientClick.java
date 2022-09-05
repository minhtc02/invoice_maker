package com.example.invoice_maker_app.interfaces;

import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.RecFilter;

public interface ClientClick {
    void clickClient(Client client);
    void editClient(Client client);
    void deleteClient(Client client);
}
