package com.example.invoice_maker_app.model;

public class RecFilter {
    private String type;
    private boolean isSelected;

    public RecFilter(String type, boolean isSelected) {
        this.type = type;
        this.isSelected = isSelected;
    }

    public RecFilter() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
