package com.tdozo.vlp.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.tdozo.vlp.enums.Aptitude;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(foreignKeys = {@ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "char_id",
        onDelete = ForeignKey.CASCADE)
})
public class InventoryWeapons implements Serializable {
    @Ignore
    private final ArrayList<Weapon> weapons;
    @PrimaryKey
    private int char_id;

    public InventoryWeapons(int char_id) {
        this.char_id = char_id;
        weight = 0;
        weapons = new ArrayList<>();
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

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addItem(Weapon weapon) {
        weight += weapon.getWeight();
        this.weapons.add(weapon);
    }


    public void removeItem(Weapon weapon) {
        weight -= weapon.getWeight();
        weapons.remove(weapon);
    }

    public void calculateWeight() {
        double aux = 0;
        for (Weapon weapon : weapons) {
            aux = +weapon.getWeight();
        }
        weight = aux;
    }

    public void addItem(String name, double weight, int value, String properties, String damage, Aptitude apt, int capacity, int hardness) {
        weight += weight;
        this.weapons.add(new Weapon(name, weight, value, properties, damage, apt, capacity, hardness, char_id));
        //todo persist

    }

    public void create() { //todo

    }

    public void update() { //todo

    }
}
