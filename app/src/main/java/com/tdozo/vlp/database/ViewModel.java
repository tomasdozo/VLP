package com.tdozo.vlp.database;

import android.content.Context;

import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.entities.Wearable;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    Character cha;
    DatabaseVLP db;

    public ViewModel(Context context) {
        db = DatabaseVLP.getDatabase(context);
    }

    public Character getCha() {
        List<Character> characters = new ArrayList<Character>();
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            characters.addAll(db.characterDao().loadCharacters());
        });
        return characters.get(0);
    }

    public void setCha(Character cha) {
        this.cha = cha;
    }


    public void insertCharacter() {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            db.characterDao().insertWearable(new Wearable("Cosa", 1, 1, "orio", 10));
        });
    }

    public List<Wearable> loadCharacter() {
        List<Wearable> wearables = new ArrayList<Wearable>();
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            wearables.addAll(db.characterDao().loadWearables());
        });
        return wearables;
    }
}
