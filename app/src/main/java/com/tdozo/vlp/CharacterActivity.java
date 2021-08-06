package com.tdozo.vlp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tdozo.vlp.classes.Character;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CharacterActivity extends AppCompatActivity {
    boolean savable = false;
    EditText name;
    Button race;
    EditText description;
    Button energyType;
    Button aptitude;
    EditText baseWeight;
    Button save;
    Character cha = new Character();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        name = findViewById(R.id.name);
        race = findViewById(R.id.race);
        description = findViewById(R.id.description);
        energyType = findViewById(R.id.energyType);
        aptitude = findViewById(R.id.aptitude);
        baseWeight = findViewById(R.id.baseWeight);
        save = findViewById(R.id.save);
        Toast toast = Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isComplete()) {
                    cha.setName(name.getText().toString().trim());
                    cha.setBaseWeight(Integer.getInteger(baseWeight.getText().toString()));
                    cha.setDescription(description.getText().toString().trim());
                    saveCharacter();
                } else {

                    toast.show();
                }
            }
        });


    }

    private boolean isComplete() {
        boolean aux = true;
        if (name.getText().toString().equals("")) {
            aux = false;

        }
        if (aux && baseWeight.getText().toString().equals("")) {
            aux = false;

        }

        if (aux && description.getText().toString().equals("")) {
            aux = false;

        }
        if (aux && cha.getAptitude() == null) {
            aux = false;
        }
        if (aux && cha.getEnergyType() == null) {
            aux = false;
        }
        if (aux && cha.getRace() == null) {
            aux = false;
        }
        return aux;
    }

    private void saveCharacter() {
        try {
            FileOutputStream fos = openFileOutput("Character", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}