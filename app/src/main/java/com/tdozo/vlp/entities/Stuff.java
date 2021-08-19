package com.tdozo.vlp.entities;


import androidx.room.PrimaryKey;

import java.io.Serializable;

public abstract class Stuff implements Serializable {
    private final String name;
    private final double weight;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int value;

    public Stuff(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
