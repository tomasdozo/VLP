package com.tdozo.vlp.database;

import android.content.Context;

import com.tdozo.vlp.MainActivity;
import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.entities.Inventory;
import com.tdozo.vlp.entities.InventoryWeapons;
import com.tdozo.vlp.entities.InventoryWearables;

import java.util.List;

public class ViewModel {
    Character cha;
    DatabaseVLP db;

    public ViewModel(Context context) {
        db = DatabaseVLP.getDatabase(context);
    }

    public void getCha(MainActivity mainActivity) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            List<Character> characters = db.characterDao().loadCharacters();
            if (characters.size() > 0) {
                cha = characters.get(0);
                loadSkills();
                loadWeaknesses();
                loadInventory();
                loadWeapons();
                loadWearables();
                mainActivity.setCha(cha);
                mainActivity.showCharacter();
            } else {
                mainActivity.newCharacter();
            }
        });
    }

    private void loadSkills() {
        cha.setSkills(db.characterDao().loadSkillsByCharacterId(cha.getId()));
    }

    private void loadWeaknesses() {
        cha.setWeakness(db.characterDao().loadWeaknessesByCharacterId(cha.getId()));
    }

    private void loadInventory() {
        Inventory inventory = db.characterDao().loadInventoryByCharacterId(cha.getId());
        inventory.setItems(db.characterDao().loadItemsByCharacterId(cha.getId()));
        cha.setInventory(db.characterDao().loadInventoryByCharacterId(cha.getId()));
    }

    private void loadWeapons() {
        InventoryWeapons inventory = db.characterDao().loadInventoryWeaponsByCharacterId(cha.getId());
        inventory.setWeapons(db.characterDao().loadWeaponsByCharacterId(cha.getId()));
        cha.setInventory(db.characterDao().loadInventoryByCharacterId(cha.getId()));
    }

    private void loadWearables() {
        InventoryWearables inventory = db.characterDao().loadInventoryWearablesByCharacterId(cha.getId());
        inventory.setWearables(db.characterDao().loadWearablesByCharacterId(cha.getId()));
        cha.setInventory(db.characterDao().loadInventoryByCharacterId(cha.getId()));
    }
}
