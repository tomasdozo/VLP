package com.tdozo.vlp.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(foreignKeys = {@ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "char_id",
        onDelete = ForeignKey.CASCADE)
})
public class InventoryWearables implements Serializable {
    @Ignore
    private final ArrayList<Wearable> wearables;
    @PrimaryKey
    private int char_id;

    public InventoryWearables(int char_id) {
        weight = 0;
        wearables = new ArrayList<>();
        this.char_id = char_id;
    }

    public int getChar_id() {
        return char_id;
    }

    private double weight;

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public ArrayList<Wearable> getWearables() {
        return wearables;
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

    public void addItem(String name, double weight, int value, String properties) {
        this.weight += weight;
        this.wearables.add(new Wearable(name, weight, value, properties, char_id));
        //todo persist
    }

    public void create() { //todo

    }

    public void update() { //todo

    }
}
