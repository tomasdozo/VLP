package com.tdozo.vlp.classes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity
public class InventoryWeapons {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Ignore
    protected double weight;
    protected ArrayList<Weapon> weapons;

    public InventoryWeapons() {
        weight = 0;
        weapons = new ArrayList<>();
    }

    public double getWeight() {
        calculateWeight();
        return weight;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addItem(Weapon weapon) {
        this.weapons.add(weapon);
    }

    public void removeItem(Weapon weapon){
        weapons.remove(weapon);
    }

    public void calculateWeight(){
        double aux = 0;
        for (Weapon weapon : weapons) {
            aux=+weapon.getWeight();
        }
        weight = aux;
    }
}
