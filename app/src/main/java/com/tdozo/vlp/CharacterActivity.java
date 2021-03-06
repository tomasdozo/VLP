package com.tdozo.vlp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.EnergyType;
import com.tdozo.vlp.enums.Race;

public class CharacterActivity extends AppCompatActivity {
    EditText name;
    Button race;
    EditText description;
    Button energyType;
    Button aptitude;
    EditText baseWeight;
    Button save;
    Character cha;
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        loadViews();
        toast = Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT);

        Character character = (Character) getIntent().getSerializableExtra("Character");

        if (character != null) {
            cha = character;
            name.setText(cha.getName());
            race.setText(cha.getRace().getName());
            description.setText(cha.getDescription());
            energyType.setText(cha.getEnergyType().getName());
            aptitude.setText(cha.getAptitude().getName());
            baseWeight.setText(String.valueOf(cha.getBaseWeight()));
        } else {
            cha = new Character();
        }

        setOnClickListeners();

    }

    private void setOnClickListeners() {
        save.setOnClickListener(v -> {
            if (isComplete()) {
                cha.setName(name.getText().toString().trim());
                cha.setBaseWeight(Integer.parseInt(baseWeight.getText().toString()));
                cha.setDescription(description.getText().toString().trim());
                if (cha.getId() == 0) cha.createAndClose(this);
                else cha.updateAndClose(this);
            } else {
                toast.show();
            }
        });

        energyType.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Energy_Type)).setItems(EnergyType.getNames(this), (dialog, which) -> {
            cha.setEnergyType(EnergyType.values()[which]);
            energyType.setText(EnergyType.values()[which].getName());
        }).show());

        energyType.setOnLongClickListener(v -> {
            if (cha.getEnergyType() != null) {
                new MaterialAlertDialogBuilder(this).setTitle(cha.getEnergyType().getName()).setMessage(cha.getEnergyType().getDescription()).show();
                return true;
            }
            return false;
        });

        aptitude.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Aptitude)).setItems(Aptitude.getNames(this), (dialog, which) -> {
            cha.setAptitude(Aptitude.values()[which]);
            aptitude.setText(Aptitude.values()[which].getName());
            if (cha.getAptitude() == Aptitude.VIG) baseWeight.setText(String.valueOf(17));
            else baseWeight.setText(String.valueOf(15));
        }).show());

        aptitude.setOnLongClickListener(v -> {
            if (cha.getAptitude() != null) {
                new MaterialAlertDialogBuilder(this).setTitle(cha.getAptitude().getName()).setMessage(cha.getAptitude().getDescription()).show();
                return true;
            }
            return false;
        });

        race.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Race)).setItems(Race.getNames(this), (dialog, which) -> {
            cha.setRace(Race.values()[which]);
            race.setText(Race.values()[which].getName());
        }).show());

        race.setOnLongClickListener(v -> {
            if (cha.getRace() != null) {
                new MaterialAlertDialogBuilder(this).setTitle(cha.getRace().getName()).setMessage(cha.getRace().getDescription()).show();
                return true;
            }
            return false;
        });
    }


    private void loadViews() {
        name = findViewById(R.id.name);
        race = findViewById(R.id.race);
        description = findViewById(R.id.description);
        energyType = findViewById(R.id.energyType);
        aptitude = findViewById(R.id.aptitude);
        baseWeight = findViewById(R.id.baseWeight);
        save = findViewById(R.id.save);
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

}