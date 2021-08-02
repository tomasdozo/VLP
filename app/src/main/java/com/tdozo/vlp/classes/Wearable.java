package com.tdozo.vlp.classes;

public class Wearable extends Stuff {
    private String property;

    public Wearable(String name, double weight, int value, String property) {
        super(name, weight, value);
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
