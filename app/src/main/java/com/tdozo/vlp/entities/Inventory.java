package com.tdozo.vlp.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = {@ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "char_id",
        onDelete = ForeignKey.CASCADE)
})
public class Inventory implements Serializable {
    @Ignore
    private List<Item> items;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @PrimaryKey
    private int char_id;

    public Inventory(int char_id) {
        weight = 0;
        items = new ArrayList<>();
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

    public List<Item> getItems() {
        return items;
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

    public void addItem(String name, double quantity, double weight, int value) {
        weight += weight * quantity;

        this.items.add(new Item(name, quantity, weight, value, char_id));
        //todo persist. metodo createOrUpdate?
    }

    public void create() { //todo

    }

    public void update() { //todo

    }
}
