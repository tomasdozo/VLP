package com.tdozo.vlp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tdozo.vlp.classes.Character;
import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.EnergyType;
import com.tdozo.vlp.enums.Race;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CharacterActivity extends AppCompatActivity {
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

        save.setOnClickListener(v -> {
            if (isComplete()) {
                cha.setName(name.getText().toString().trim());
                cha.setBaseWeight(Integer.parseInt(baseWeight.getText().toString()));
                cha.setDescription(description.getText().toString().trim());
                saveCharacter();
                finish();
                startActivity(new Intent(CharacterActivity.this, MainActivity.class));
            } else {
                toast.show();
            }
        });

        energyType.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Energy_Type)).setItems(EnergyType.getNames(this), (dialog, which) -> {
            cha.setEnergyType(EnergyType.values()[which]);
            energyType.setText(EnergyType.values()[which].getName());
        }).show());

        aptitude.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Aptitude)).setItems(Aptitude.getNames(this), (dialog, which) -> {
            cha.setAptitude(Aptitude.values()[which]);
            aptitude.setText(Aptitude.values()[which].getName());
            if (cha.getAptitude() == Aptitude.VIG) baseWeight.setText("17");
            else baseWeight.setText("15");
        }).show());

        race.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Race)).setItems(Race.getNames(this), (dialog, which) -> {
            cha.setRace(Race.values()[which]);
            race.setText(Race.values()[which].getName());
        }).show());


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