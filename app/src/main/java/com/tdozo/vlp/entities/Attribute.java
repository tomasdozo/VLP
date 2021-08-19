package com.tdozo.vlp.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "char_id",
        onDelete = ForeignKey.CASCADE)
})
public class Attribute implements Serializable {
    private final String name;
    private final String description;
    private final boolean isSkill;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int char_id;

    public Attribute(String name, String description, boolean isSkill, int char_id) {
        this.name = name;
        this.description = description;
        this.isSkill = isSkill;
        this.char_id = char_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSkill() {
        return isSkill;
    }

    public int getChar_id() {
        return char_id;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
