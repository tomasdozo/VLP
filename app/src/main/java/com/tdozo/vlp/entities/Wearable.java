package com.tdozo.vlp.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(indices = {@Index("wearables_id"),
}, foreignKeys = {@ForeignKey(entity = InventoryWearables.class,
        parentColumns = "char_id",
        childColumns = "wearables_id",
        onDelete = ForeignKey.CASCADE)
})
public class Wearable extends Stuff {
    private final String property;

    private final int wearables_id;

    public Wearable(String name, double weight, int value, String property, int wearables_id) {
        super(name, weight, value);
        this.property = property;
        this.wearables_id = wearables_id;
    }

    public int getWearables_id() {
        return wearables_id;
    }

    public String getProperty() {
        return property;
    }

    public void createOrUpdate() {
        if (getId() == 0) { //Crear

        } else //Actualizar
        {

        }
    }
}
