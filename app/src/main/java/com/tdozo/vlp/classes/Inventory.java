package com.tdozo.vlp.classes;


import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {

    private final ArrayList<Item> items;
    private double weight;

    public Inventory() {
        weight = 0;
        items = new ArrayList<>();
    }

    public double getWeight() {
        return weight;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        weight += item.getTotalWeight();
        this.items.add(item);
    }

    public void removeItem(Item item) {
        weight -= item.getTotalWeight();
        items.remove(item);
    }

    public void calculateWeight() {
        double aux = 0;
        for (Item item : items) {
            aux += item.getTotalWeight();
        }
        weight = aux;
    }
}
