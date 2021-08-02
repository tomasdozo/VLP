package com.tdozo.vlp.classes;


import java.io.Serializable;
import java.util.ArrayList;


public class InventoryWearables implements Serializable {
    protected double weight;
    protected ArrayList<Wearable> wearables;

    public InventoryWearables() {
        weight = 0;
        wearables = new ArrayList<>();
    }

    public double getWeight() {
        calculateWeight();
        return weight;
    }

    public ArrayList<Wearable> getWearables() {
        return wearables;
    }

    public void addItem(Wearable wearable) {
        this.wearables.add(wearable);
    }

    public void removeItem(Wearable wearable) {
        wearables.remove(wearable);
    }

    public void calculateWeight() {
        double aux = 0;
        for (Wearable wearable : wearables) {
            aux = +wearable.getWeight();
        }
        weight = aux;
    }
}
