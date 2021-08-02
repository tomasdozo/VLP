package com.tdozo.vlp.classes;


public class Attribute {
    protected final String name;
    protected final String description;

    public Attribute(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
