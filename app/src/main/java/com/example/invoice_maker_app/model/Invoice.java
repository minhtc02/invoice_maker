package com.example.invoice_maker_app.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Invoice")
public class Invoice implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String number;
    private String createDate;
    private String dueTerms;
    private String dueDate;
    private String po;
    private String businessId;
    private String clientId;
    private String shipping;
    private String discountId;
    private String totalMoney;
    private String totalTax;
    private String status;
    @TypeConverters(ConverterTax.class)
    private List<Tax> listTax = null;


    public Invoice(String number, String createDate, String dueTerms, String dueDate, String po, String businessId, String clientId, String shipping, String discountId, String totalMoney, String totalTax, String status, ArrayList<Tax> listTax) {
        this.number = number;
        this.createDate = createDate;
        this.dueTerms = dueTerms;
        this.dueDate = dueDate;
        this.po = po;
        this.businessId = businessId;
        this.clientId = clientId;
        this.shipping = shipping;
        this.discountId = discountId;
        this.totalMoney = totalMoney;
        this.totalTax = totalTax;
        this.status = status;
        this.listTax = listTax;
    }


    public Invoice() {
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDueTerms() {
        return dueTerms;
    }

    public void setDueTerms(String dueTerms) {
        this.dueTerms = dueTerms;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public List<Tax> getListTax() {
        return listTax;
    }

    public void setListTax(List<Tax> listTax) {
        this.listTax = listTax;
    }
}
