package com.example.invoice_maker_app.interfaces;

import com.example.invoice_maker_app.model.Tax;

public interface TaxClick {
    void clickTax(Tax tax, int position);

    void editTax(Tax tax);

    void deleteTax(Tax tax);

}
