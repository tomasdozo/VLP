package com.tdozo.vlp.classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Attribute {
    @PrimaryKey(autoGenerate = true)
    private int id;
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
