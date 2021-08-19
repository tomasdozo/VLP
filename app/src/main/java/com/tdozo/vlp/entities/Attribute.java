package com.tdozo.vlp.entities;


import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.tdozo.vlp.database.CharacterDao;
import com.tdozo.vlp.database.DatabaseVLP;

import java.io.Serializable;

@Entity(indices = {@Index("char_id"),
}, foreignKeys = {@ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "char_id",
        onDelete = ForeignKey.CASCADE)
})
public class Attribute implements Serializable {
    private final String name;
    private final String description;
    private final boolean isSkill;
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int char_id;

    public Attribute(String name, String description, boolean isSkill, int char_id) {
        this.name = name;
        this.description = description;
        this.isSkill = isSkill;
        this.char_id = char_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSkill() {
        return isSkill;
    }

    public int getChar_id() {
        return char_id;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void createOrUpdate(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(context).characterDao();
            if (getId() == 0) { //Crear
                setId((int) characterDao.insertAttribute(this));
            } else //Actualizar
            {
                characterDao.updateAttribute(this);
            }
        });
    }

    public void delete(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> DatabaseVLP.getDatabase(context).characterDao().deleteAttribute(this));
    }
}
