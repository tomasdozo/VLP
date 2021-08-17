package com.tdozo.vlp.classes;


public class Item extends Stuff {
    private double quantity;

    public Item(String name, double quantity, double weight, int value) {
        super(name, weight, value);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalWeight() {
        return super.getWeight() * quantity;
    }
}
