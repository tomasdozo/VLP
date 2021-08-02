package com.tdozo.vlp.classes;

import androidx.room.Entity;

@Entity
public class Item extends Stuff {
    private double quantity;

    public Item(String name, double quantity, double weight, int value) {
        super(name, weight, value);
        this.quantity = quantity;
    }

    public Item(String name, double quantity, double weight) {
        super(name, weight, 0);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getWeight() {
        return super.getWeight() * quantity;
    }
}
