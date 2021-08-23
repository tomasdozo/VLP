package com.tdozo.vlp.database;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;

import com.tdozo.vlp.CharacterViewActivity;
import com.tdozo.vlp.MainActivity;
import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.entities.Inventory;
import com.tdozo.vlp.entities.InventoryWeapons;
import com.tdozo.vlp.entities.InventoryWearables;

import java.util.List;

public class CharacterViewModel {
    LiveData<List<Character>> characters;
    DatabaseVLP db;

    public CharacterViewModel(Context context) {
        db = DatabaseVLP.getDatabase(context);
        characters = db.characterDao().loadLiveCharacters();
    }

    public LiveData<List<Character>> getCharacters() {
        return characters;
    }

    public void loadCharacter(Character cha, MainActivity mainActivity) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            loadSkills(cha);
            loadWeaknesses(cha);
            loadInventory(cha);
            loadWeapons(cha);
            loadWearables(cha);
            Intent intent = new Intent(mainActivity, CharacterViewActivity.class);
            intent.putExtra("Character", cha);
            mainActivity.startActivity(intent);
        });
    }

    LiveData<List<Character>> getAllCharacters() {
        return characters;
    }

    private void loadSkills(Character cha) {
        cha.setSkills(db.characterDao().loadSkillsByCharacterId(cha.getId()));
    }

    private void loadWeaknesses(Character cha) {
        cha.setWeaknesses(db.characterDao().loadWeaknessesByCharacterId(cha.getId()));
    }

    private void loadInventory(Character cha) {
        Inventory inventory = db.characterDao().loadInventoryByCharacterId(cha.getId());
        inventory.setItems(db.characterDao().loadItemsByCharacterId(cha.getId()));
        inventory.calculateWeight();
        cha.setInventory(inventory);
    }

    private void loadWeapons(Character cha) {
        InventoryWeapons inventory = db.characterDao().loadInventoryWeaponsByCharacterId(cha.getId());
        inventory.setWeapons(db.characterDao().loadWeaponsByCharacterId(cha.getId()));
        inventory.calculateWeight();
        cha.setWeapons(inventory);
    }

    private void loadWearables(Character cha) {
        InventoryWearables inventory = db.characterDao().loadInventoryWearablesByCharacterId(cha.getId());
        inventory.setWearables(db.characterDao().loadWearablesByCharacterId(cha.getId()));
        inventory.calculateWeight();
        cha.setWearables(inventory);
    }
}
