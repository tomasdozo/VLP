package com.tdozo.vlp.classes;


import java.io.Serializable;
import java.util.ArrayList;


public class InventoryWearables implements Serializable {
    private final ArrayList<Wearable> wearables;
    private double weight;

    public InventoryWearables() {
        weight = 0;
        wearables = new ArrayList<>();
    }

    public double getWeight() {
        return weight;
    }

    public ArrayList<Wearable> getWearables() {
        return wearables;
    }

    public void addItem(Wearable wearable) {
        weight += wearable.getWeight();
        this.wearables.add(wearable);
    }

    public void removeItem(Wearable wearable) {
        weight -= wearable.getWeight();
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
