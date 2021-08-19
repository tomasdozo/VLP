package com.tdozo.vlp.entities;


import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.tdozo.vlp.database.CharacterDao;
import com.tdozo.vlp.database.DatabaseVLP;
import com.tdozo.vlp.enums.Aptitude;

@Entity(indices = {@Index("weapons_id"),
}, foreignKeys = {@ForeignKey(entity = InventoryWeapons.class,
        parentColumns = "char_id",
        childColumns = "weapons_id",
        onDelete = ForeignKey.CASCADE)
})
public class Weapon extends Stuff {
    private final String properties;
    private final String damage;
    private final Aptitude aptitude;
    private final int capacity;
    private int hardness;

    private int weapons_id;

    public Weapon(String name, double weight, int value, String properties, String damage, Aptitude aptitude, int capacity, int hardness, int weapons_id) {
        super(name, weight, value);
        this.properties = properties;
        this.damage = damage;
        this.aptitude = aptitude;
        this.capacity = capacity;
        this.hardness = hardness;
        this.weapons_id = weapons_id;
    }

    public int getWeapons_id() {
        return weapons_id;
    }

    public void setWeapons_id(int weapons_id) {
        this.weapons_id = weapons_id;
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

    public void createOrUpdate(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(context).characterDao();
            if (getId() == 0) { //Crear
                characterDao.insertWeapon(this);
            } else //Actualizar
            {
                characterDao.updateWeapon(this);
            }
        });
    }

    public void delete(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> DatabaseVLP.getDatabase(context).characterDao().deleteWeapon(this));
    }
}
