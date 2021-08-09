package com.tdozo.vlp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tdozo.vlp.classes.Attribute;
import com.tdozo.vlp.classes.Character;
import com.tdozo.vlp.classes.Item;
import com.tdozo.vlp.classes.Weapon;
import com.tdozo.vlp.classes.Wearable;
import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.Energy;
import com.tdozo.vlp.enums.EnergyType;
import com.tdozo.vlp.enums.Health;
import com.tdozo.vlp.enums.Race;
import com.tdozo.vlp.enums.Sanity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    Character cha;
    TextView view_name;
    TextView view_race;
    TextView view_coins;
    TextView view_energyType;
    TextView view_xp;
    TextView view_health;
    TextView view_sanity;
    TextView view_energy;
    TableLayout view_wearables;
    TableLayout view_weapons;
    TextView view_aptitude;
    LinearLayout view_skills;
    LinearLayout view_weakness;
    TableLayout view_inventory;
    TextView view_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViews();

        loadCharacter();

        setOnClickListeners();

    }

    private void setOnClickListeners() {
        view_health.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Health)).setItems(Health.getNames(this), (dialog, which) -> {
            Health health = Health.values()[which];
            cha.setHealth(health);
            view_health.setText(health.getName());
            saveCharacter();
        }).show());

        view_sanity.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Sanity)).setItems(Sanity.getNames(this), (dialog, which) -> {
            Sanity sanity = Sanity.values()[which];
            cha.setSanity(sanity);
            view_sanity.setText(sanity.getName());
            saveCharacter();
        }).show());

        view_energy.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Energy)).setItems(Energy.getNames(this), (dialog, which) -> {
            Energy energy = Energy.values()[which];
            cha.setEnergy(energy);
            view_energy.setText(energy.getName());
            saveCharacter();
        }).show());

        view_aptitude.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(cha.getAptitude().getName()).setMessage(cha.getAptitude().getDescription())
                .show());

        view_race.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(cha.getRace().getName()).setMessage(cha.getRace().getDescription())
                .show());

        view_energyType.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(cha.getEnergyType().getName()).setMessage(cha.getEnergyType().getDescription())
                .show());

        view_coins.setOnClickListener(v -> {
            EditText text = new EditText(this);
            text.setText(String.valueOf(cha.getCoins()));
            text.setInputType(InputType.TYPE_CLASS_NUMBER);
            text.setGravity(Gravity.CENTER);
            new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Coins)).setView(text).setPositiveButton(R.string.Save, (dialog, which) -> {
                cha.setCoins(Integer.parseInt(text.getText().toString()));
                saveCharacter();
                showCoinsXpLoad();
            }).show();
        });

        view_xp.setOnClickListener(v -> {
            EditText text = new EditText(this);
            text.setText(String.valueOf(cha.getExperience()));
            text.setInputType(InputType.TYPE_CLASS_NUMBER);
            text.setGravity(Gravity.CENTER);
            new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Exp)).setView(text).setPositiveButton(R.string.Save, (dialog, which) -> {
                cha.setExperience(Integer.parseInt(text.getText().toString()));
                saveCharacter();
                showCoinsXpLoad();
            }).show();
        });


    }

    private void loadViews() {
        view_name = findViewById(R.id.name);
        view_race = findViewById(R.id.race);
        view_coins = findViewById(R.id.coins);
        view_energyType = findViewById(R.id.energy_type);
        view_xp = findViewById(R.id.xp);
        view_health = findViewById(R.id.health);
        view_sanity = findViewById(R.id.sanity);
        view_energy = findViewById(R.id.energy);
        view_wearables = findViewById(R.id.wearables);
        view_weapons = findViewById(R.id.weapons);
        view_aptitude = findViewById(R.id.aptitude);
        view_skills = findViewById(R.id.skills);
        view_weakness = findViewById(R.id.weakness);
        view_inventory = findViewById(R.id.inventory);
        view_load = findViewById(R.id.load);
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

    private void loadCharacter() {
        try {
            FileInputStream fis = openFileInput("Character");
            ObjectInputStream ois = new ObjectInputStream(fis);
            cha = (Character) ois.readObject();
            showCharacter();
        } catch (Exception e) {
            e.printStackTrace();
            newCharacter();
        }
        /*
        cha = new Character();
        seed();
        showCharacter();
        saveCharacter();
        */
    }

    private void newCharacter() {
        Intent intent = new Intent(MainActivity.this, CharacterActivity.class);
        startActivity(intent);
        finish();
    }

    private void showCharacter() {
        view_name.setText(cha.getName());
        view_race.setText(cha.getRace().getName());
        view_energyType.setText(cha.getEnergyType().getName());
        showCoinsXpLoad();
        view_health.setText(cha.getHealth().getName());
        view_sanity.setText(cha.getSanity().getName());
        view_energy.setText(cha.getEnergy().getName());
        showWearables();
        showWeapons();
        view_aptitude.setText(cha.getAptitude().getName());
        showSkills();
        showWeakness();
        showInventory();

    }

    private void showCoinsXpLoad() {
        view_xp.setText(String.valueOf(cha.getExperience()));
        view_coins.setText(String.valueOf(cha.getCoins()));
        view_load.setText(getString(R.string.load, cha.getActual_weight(), cha.getBaseWeight()));
    }

    private void showWeakness() {
        for (Attribute weakness : cha.getWeakness()) {
            TextView view = new TextView(getBaseContext());
            view.setText(weakness.getName());
            view.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(weakness.getName()).setMessage(weakness.getDescription())
                    .show());
            view_weakness.addView(view);
        }
    }

    private void showSkills() {
        for (Attribute skill : cha.getSkills()) {
            TextView view = new TextView(getBaseContext());
            view.setText(skill.getName());
            view.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(skill.getName()).setMessage(skill.getDescription())
                    .show());
            view_skills.addView(view);
        }
    }

    private void showWearables() {
        for (Wearable wearable : cha.getWearables().getWearables()) {
            TableRow row = new TableRow(getBaseContext());
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            row.setGravity(Gravity.CENTER_VERTICAL);


            TextView col0 = new TextView(getBaseContext());
            col0.setText(wearable.getName());
            col0.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            col0.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            col0.setGravity(Gravity.CENTER_VERTICAL);
            col0.setPadding(5, 5, 0, 5);
            row.addView(col0);

            view_wearables.addView(row);
        }
    }

    private void showWeapons() {
        for (Weapon weapon : cha.getWeapons().getWeapons()) {
            TableRow row = new TableRow(getBaseContext());
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            row.setGravity(Gravity.CENTER_VERTICAL);


            TextView col0 = new TextView(getBaseContext());
            col0.setText(weapon.getName());
            col0.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            col0.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3.0f));
            col0.setGravity(Gravity.CENTER_VERTICAL);
            col0.setPadding(5, 5, 0, 5);
            row.addView(col0);

            TableRow.LayoutParams param = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);

            TextView col1 = new TextView(getBaseContext());
            col1.setText(String.valueOf(weapon.getDamage()));
            col1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col1.setLayoutParams(param);
            col1.setGravity(Gravity.CENTER_VERTICAL);
            row.addView(col1);

            TextView col2 = new TextView(getBaseContext());
            col2.setText(String.valueOf(weapon.getAptitude()));
            col2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col2.setLayoutParams(param);
            col2.setGravity(Gravity.CENTER_VERTICAL);
            row.addView(col2);

            TextView col3 = new TextView(getBaseContext());
            col3.setText(String.valueOf(weapon.getCapacity()));
            col3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col3.setLayoutParams(param);
            col3.setGravity(Gravity.CENTER_VERTICAL);
            row.addView(col3);

            view_weapons.addView(row);
        }
    }

    private void showInventory() {
        for (Item item : cha.getInventory().getItems()) {
            TableRow row = new TableRow(getBaseContext());
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            row.setGravity(Gravity.CENTER_VERTICAL);


            TextView col0 = new TextView(getBaseContext());
            col0.setText(item.getName());
            col0.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            col0.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3.0f));
            col0.setGravity(Gravity.CENTER_VERTICAL);
            col0.setPadding(5, 5, 0, 5);
            row.addView(col0);

            TableRow.LayoutParams param = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);

            TextView col1 = new TextView(getBaseContext());
            col1.setText(String.valueOf(item.getQuantity()));
            col1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col1.setLayoutParams(param);
            col1.setGravity(Gravity.CENTER_VERTICAL);
            row.addView(col1);

            TextView col2 = new TextView(getBaseContext());
            col2.setText(String.valueOf(item.getWeight()));
            col2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col2.setLayoutParams(param);
            col2.setGravity(Gravity.CENTER_VERTICAL);
            row.addView(col2);

            TextView col3 = new TextView(getBaseContext());
            col3.setText(String.valueOf(item.getValue()));
            col3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col3.setLayoutParams(param);
            col3.setGravity(Gravity.CENTER_VERTICAL);
            row.addView(col3);

            view_inventory.addView(row);
        }
    }

    private void seed() {
        cha.setName("Pitito");
        cha.setRace(Race.HUMAN);
        cha.setDescription("Un humano muy respetado por todos y todas. Unico y especial. Frase inspiracional, el tamaño no lo es todo.");
        cha.setEnergyType(EnergyType.VITAL);
        cha.setAptitude(Aptitude.LOG);
        cha.setCoins(150);
        cha.setExperience(75);
        cha.setBaseWeight(15);

        //Seed debilidades
        cha.addWeakness("Feo", "No hace falta describir.");
        cha.addWeakness("Cleptomano", "Tienes que robar to lo que veas.");

        //Seed habilidades
        cha.addSkill("Volar", "Gastando 2 energias volas por todo el mundi.");
        cha.addSkill("Persuasion", "Si tenes a alguien medio gilipollas adelante lo podes engañar.");
        cha.addSkill("Domador de pugs", "Gastando 0 de energia puedes invocar a un ejercito de pugs para vencer al enemigo de cuteness.");

        //Seed inventario
        cha.addItem("Pildora", 5, 0.1);
        cha.addItem("Caja misteriosa", 1, 10, 50);
        cha.addItem("Llave random random", 1, 0.1);
        cha.addItem("Pocion de salud", 3, 0.5, 10);
        cha.addItem("Pocion de stamina", 2, 0.5, 10);
        for (int i = 1; i <= 20; i++) {
            cha.addItem("Item " + i, i, i * 2, i * 5);
        }

        //seed armas
        cha.addWeapon("Hacha to Pro", "1/2/3", Aptitude.VIG, "Propiedad del super hacha noob", 0, 2, 1.5, 0);
        cha.addWeapon("Baston de mago", "1/1/1", Aptitude.LOG, "Incrementa en 2 el dano de hechizos", 0, 1, 1, 100);
        cha.addWeapon("Pala to Pro", "3/2/3", Aptitude.VIG, "Propiedad del super hacha noob", 0, 2, 1.5, 0);
        cha.addWeapon("Bastoncito", "2/2/2", Aptitude.LOG, "Incrementa en 2 el dano de hechizos", 0, 1, 1, 100);

        //seed armadura
        cha.addWearable("Caperusa roja", "Mision secundaria visitar el bosque", 1, 30);
        cha.addWearable("Calzas termicas", "Para aguantar el frio", 0.5, 10);
        cha.addWearable("Caperusa Azul", "Something", 1, 30);
        cha.addWearable("Calzas atermicas", "Para tener  frio", 0.5, 10);


    }
}