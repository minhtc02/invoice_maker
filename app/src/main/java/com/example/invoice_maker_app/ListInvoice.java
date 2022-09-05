package com.example.invoice_maker_app;

import com.example.invoice_maker_app.model.RecFilter;

import java.util.ArrayList;
import java.util.List;

public class ListInvoice {
    public static List<RecFilter> recFilterInvoiceList = new ArrayList<>();
    public static List<RecFilter> recFilterEstimateList = new ArrayList<>();
    public static List<String> listsp = new ArrayList<>();
    public static List<String> listspDiscount = new ArrayList<>();

    public static void createList() {
        recFilterInvoiceList.add(new RecFilter("All", true));
        recFilterInvoiceList.add(new RecFilter("Paid", false));
        recFilterInvoiceList.add(new RecFilter("Unpaid", false));
        recFilterInvoiceList.add(new RecFilter("Overdue", false));

        recFilterEstimateList.add(new RecFilter("All", true));
        recFilterEstimateList.add(new RecFilter("Pending", false));
        recFilterEstimateList.add(new RecFilter("Approved", false));
        recFilterEstimateList.add(new RecFilter("Overdue", false));

        listsp.add("None");
        listsp.add("Due on receipt");
        listsp.add("Next day");
        listsp.add("2 days");
        listsp.add("3 days");
        listsp.add("4 days");
        listsp.add("5 days");
        listsp.add("6 days");
        listsp.add("7 days");
        listsp.add("30 days");
        listsp.add("90 days");
        listsp.add("180 days");
        listsp.add("Custom");

        listspDiscount.add("%");
        listspDiscount.add("$");
    }

}
