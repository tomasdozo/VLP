package com.tdozo.vlp.classes;


import java.io.Serializable;
import java.util.ArrayList;

public class InventoryWeapons implements Serializable {
    private final ArrayList<Weapon> weapons;
    private double weight;

    public InventoryWeapons() {
        weight = 0;
        weapons = new ArrayList<>();
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
}
