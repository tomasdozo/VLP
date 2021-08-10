package com.tdozo.vlp.classes;


import com.tdozo.vlp.enums.Aptitude;


public class Weapon extends Stuff {
    private final String properties;
    private final String damage;
    private final Aptitude aptitude;
    private final int capacity;
    private int hardness;

    public Weapon(String name, double weight, int value, String properties, String damage, Aptitude aptitude, int capacity, int hardness) {
        super(name, weight, value);
        this.properties = properties;
        this.damage = damage;
        this.aptitude = aptitude;
        this.capacity = capacity;
        this.hardness = hardness;
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

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }
}
