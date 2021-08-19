package com.tdozo.vlp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.entities.Wearable;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWearable(Wearable wearable);

    @Update
    void updateWearable(Wearable wearable);

    @Delete
    void deleteWearable(Wearable wearable);

    @Query("SELECT * FROM wearable WHERE id = :id")
    Wearable loadWearableById(int id);

    @Query("SELECT * FROM wearable")
    List<Wearable> loadWearables();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCharacter(Character character);

    @Update
    void updateCharacter(Character character);

    @Delete
    void deleteCharacter(Character character);

    @Query("SELECT * FROM character WHERE id = :id")
    Character loadCharacterById(int id);

    @Query("SELECT * FROM character")
    List<Character> loadCharacters();

}
