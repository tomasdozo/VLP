package com.tdozo.vlp.classes;

import androidx.room.Entity;

@Entity
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
