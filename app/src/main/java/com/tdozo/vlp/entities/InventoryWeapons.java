package com.tdozo.vlp.entities;


import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.tdozo.vlp.database.CharacterDao;
import com.tdozo.vlp.database.DatabaseVLP;
import com.tdozo.vlp.enums.Aptitude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = {@ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "char_id",
        onDelete = ForeignKey.CASCADE)
})
public class InventoryWeapons implements Serializable {
    @Ignore
    private List<Weapon> weapons;

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @PrimaryKey
    private int char_id;

    public InventoryWeapons(int char_id) {
        this.char_id = char_id;
        weight = 0;
        weapons = new ArrayList<>();
    }

    public int getChar_id() {
        return char_id;
    }

    private double weight;

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void addItem(Weapon weapon) {
        weight += weapon.getWeight();
        this.weapons.add(weapon);
    }


    public void removeItem(Weapon weapon, Context context) {
        weight -= weapon.getWeight();
        weapons.remove(weapon);
        weapon.delete(context);
        update(context);
    }

    public void calculateWeight() {
        double aux = 0;
        for (Weapon weapon : weapons) {
            aux = +weapon.getWeight();
        }
        weight = aux;
    }

    public void addItem(String name, double weight, int value, String properties, String damage, Aptitude apt, int capacity, int hardness, Context context) {
        this.weight += weight;
        Weapon weapon = new Weapon(name, weight, value, properties, damage, apt, capacity, hardness, char_id);
        this.weapons.add(weapon);
        weapon.createOrUpdate(context);
        update(context);

    }

    public void create(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(context).characterDao();
            characterDao.insertInventoryWeapons(this);
        });
    }

    public void update(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(context).characterDao();
            characterDao.updateInventoryWeapons(this);
        });
    }
}
