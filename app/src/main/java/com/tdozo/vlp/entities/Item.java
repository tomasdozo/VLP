package com.tdozo.vlp.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {@ForeignKey(entity = Inventory.class,
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

    public void createOrUpdate() { //todo
        if (getId() == 0) { //Crear

        } else //Actualizar
        {

        }
    }
}
