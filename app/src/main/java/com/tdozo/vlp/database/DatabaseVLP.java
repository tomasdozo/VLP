package com.tdozo.vlp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tdozo.vlp.entities.Attribute;
import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.entities.Inventory;
import com.tdozo.vlp.entities.InventoryWeapons;
import com.tdozo.vlp.entities.InventoryWearables;
import com.tdozo.vlp.entities.Item;
import com.tdozo.vlp.entities.Weapon;
import com.tdozo.vlp.entities.Wearable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Attribute.class, Character.class, Inventory.class, InventoryWeapons.class, InventoryWearables.class, Item.class, Weapon.class, Wearable.class}, version = 1, exportSchema = false)
public abstract class DatabaseVLP extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile DatabaseVLP INSTANCE;

    public static DatabaseVLP getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseVLP.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseVLP.class, "vlp_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CharacterDao characterDao();
}
