package com.tdozo.vlp.entities;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.tdozo.vlp.database.CharacterDao;
import com.tdozo.vlp.database.DatabaseVLP;

@Entity(indices = {@Index("inventory_id"),
}, foreignKeys = {@ForeignKey(entity = Inventory.class,
        parentColumns = "char_id",
        childColumns = "inventory_id",
        onDelete = ForeignKey.CASCADE)
})
public class Item extends Stuff {
    private double quantity;

    private int inventory_id;

    public Item(String name, double quantity, double weight, int value, int inventory_id) {
        super(name, weight, value);
        this.quantity = quantity;
        this.inventory_id = inventory_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalWeight() {
        return super.getWeight() * quantity;
    }

    public void createOrUpdate(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(context).characterDao();
            if (getId() == 0) { //Crear
                characterDao.insertItem(this);
            } else //Actualizar
            {
                characterDao.updateItem(this);
            }
        });
    }
}
