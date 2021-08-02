package com.tdozo.vlp.classes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity
public class Inventory {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Ignore
    protected double weight;
    protected ArrayList<Item> items;

    public Inventory() {
        weight = 0;
        items = new ArrayList<>();
    }

    public double getWeight() {
        calculateWeight();
        return weight;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void calculateWeight(){
        double aux = 0;
        for (Item item : items) {
            aux=+item.getWeight();
        }
        weight = aux;
    }
}
