package com.example.invoice_maker_app.interfaces;

import com.example.invoice_maker_app.model.Invoice;
import com.example.invoice_maker_app.model.RecFilter;

public interface InvoiceClick {
    void clickInvoice(Invoice invoice);
    void longClickInvoice(Invoice invoice);
}
