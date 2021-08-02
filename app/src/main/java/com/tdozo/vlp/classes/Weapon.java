package com.tdozo.vlp.classes;

import androidx.room.Entity;

import com.tdozo.vlp.enums.Aptitude;

@Entity
public class Weapon extends Stuff {
    private String properties;
    private String damage;
    private Aptitude aptitude;
    private int capacity;
    private int defense;

    public Weapon(String name, double weight, int value, String properties, String damage, Aptitude aptitude, int capacity, int defense) {
        super(name, weight, value);
        this.properties = properties;
        this.damage = damage;
        this.aptitude = aptitude;
        this.capacity = capacity;
        this.defense = defense;
    }

    public String getProperties() {
        return properties;
    }

    public String getDamage() {
        return damage;
    }

    public Aptitude getAptitude() {
        return aptitude;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
