package com.example.invoice_maker_app.interfaces;

import com.example.invoice_maker_app.model.Client;
import com.example.invoice_maker_app.model.Item;

public interface ItemClick {
    void clickItem(Item item);
    void editItem(Item item);
    void deleteItem(Item item);
}
