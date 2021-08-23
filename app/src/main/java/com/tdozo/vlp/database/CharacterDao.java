package com.tdozo.vlp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tdozo.vlp.entities.Attribute;
import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.entities.Inventory;
import com.tdozo.vlp.entities.InventoryWeapons;
import com.tdozo.vlp.entities.InventoryWearables;
import com.tdozo.vlp.entities.Item;
import com.tdozo.vlp.entities.Weapon;
import com.tdozo.vlp.entities.Wearable;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCharacter(Character character);

    @Update
    void updateCharacter(Character character);

    @Delete
    void deleteCharacter(Character character);

    @Query("SELECT * FROM character WHERE id = :id")
    Character loadCharacterById(int id);

    @Query("SELECT * FROM character")
    LiveData<List<Character>> loadLiveCharacters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(Item item);

    @Update
    void updateItem(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("SELECT * FROM item WHERE inventory_id = :id")
    List<Item> loadItemsByCharacterId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWeapon(Weapon weapon);

    @Update
    void updateWeapon(Weapon weapon);

    @Delete
    void deleteWeapon(Weapon weapon);

    @Query("SELECT * FROM weapon WHERE weapons_id = :id")
    List<Weapon> loadWeaponsByCharacterId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWearable(Wearable wearable);

    @Update
    void updateWearable(Wearable wearable);

    @Delete
    void deleteWearable(Wearable wearable);

    @Query("SELECT * FROM wearable WHERE wearables_id = :id")
    List<Wearable> loadWearablesByCharacterId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAttribute(Attribute attribute);

    @Update
    void updateAttribute(Attribute attribute);

    @Delete
    void deleteAttribute(Attribute attribute);

    @Query("SELECT * FROM attribute WHERE char_id = :id and isSkill = 1")
    List<Attribute> loadSkillsByCharacterId(int id);

    @Query("SELECT * FROM attribute WHERE char_id = :id and isSkill = 0")
    List<Attribute> loadWeaknessesByCharacterId(int id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInventory(Inventory inventory);

    @Update
    void updateInventory(Inventory inventory);

    @Delete
    void deleteInventory(Inventory inventory);

    @Query("SELECT * FROM inventory where char_id = :id")
    Inventory loadInventoryByCharacterId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInventoryWearables(InventoryWearables inventoryWearables);

    @Update
    void updateInventoryWearables(InventoryWearables inventoryWearables);

    @Delete
    void deleteInventoryWearables(InventoryWearables inventoryWearables);

    @Query("SELECT * FROM inventoryWearables where char_id = :id")
    InventoryWearables loadInventoryWearablesByCharacterId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInventoryWeapons(InventoryWeapons inventoryWeapons);

    @Update
    void updateInventoryWeapons(InventoryWeapons inventoryWeapons);

    @Delete
    void deleteInventoryWeapons(InventoryWeapons inventoryWeapons);

    @Query("SELECT * FROM inventoryWeapons where char_id = :id")
    InventoryWeapons loadInventoryWeaponsByCharacterId(int id);


}
