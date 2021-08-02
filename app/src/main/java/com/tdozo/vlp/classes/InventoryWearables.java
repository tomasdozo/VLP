package com.tdozo.vlp.classes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class InventoryWearables {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Ignore
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

    public void removeItem(Wearable wearable){
        wearables.remove(wearable);
    }

    public void calculateWeight(){
        double aux = 0;
        for (Wearable wearable : wearables) {
            aux=+wearable.getWeight();
        }
        weight = aux;
    }
}
