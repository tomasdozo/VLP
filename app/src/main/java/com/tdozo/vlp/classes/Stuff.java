package com.tdozo.vlp.classes;


import java.io.Serializable;

public abstract class Stuff implements Serializable {

    protected String name;
    protected double weight;
    protected int value;

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
}
