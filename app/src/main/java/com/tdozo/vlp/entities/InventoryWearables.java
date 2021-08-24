package com.tdozo.vlp.entities;


import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.tdozo.vlp.database.CharacterDao;
import com.tdozo.vlp.database.DatabaseVLP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = {@ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "char_id",
        onDelete = ForeignKey.CASCADE)
})
public class InventoryWearables implements Serializable {
    @Ignore
    private List<Wearable> wearables;

    public void setWearables(List<Wearable> wearables) {
        this.wearables = wearables;
    }

    @PrimaryKey
    private int char_id;

    public InventoryWearables(int char_id) {
        weight = 0;
        wearables = new ArrayList<>();
        this.char_id = char_id;
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

    public List<Wearable> getWearables() {
        return wearables;
    }

    public void removeItem(Wearable wearable, Context context) {
        weight -= wearable.getWeight();
        wearables.remove(wearable);
        wearable.delete(context);
        update(context);
    }

    public void calculateWeight() {
        double aux = 0;
        for (Wearable wearable : wearables) {
            aux = +wearable.getWeight();
        }
        weight = aux;
    }

    public void addWearable(String name, double weight, int value, String properties, Context context) {
        this.weight += weight;
        Wearable wearable = new Wearable(name, weight, value, properties, char_id);
        this.wearables.add(wearable);
        wearable.createOrUpdate(context);
        update(context);
    }

    public void create(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(context).characterDao();
            characterDao.insertInventoryWearables(this);
        });
    }

    public void update(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(context).characterDao();
            characterDao.updateInventoryWearables(this);
        });
    }

}
