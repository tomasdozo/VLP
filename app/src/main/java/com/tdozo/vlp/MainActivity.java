package com.tdozo.vlp;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tdozo.vlp.classes.Attribute;
import com.tdozo.vlp.classes.Character;
import com.tdozo.vlp.classes.Item;
import com.tdozo.vlp.classes.Weapon;
import com.tdozo.vlp.classes.Wearable;
import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.EnergyType;
import com.tdozo.vlp.enums.Race;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
    LinearLayout view_wearables;
    LinearLayout view_weapons;
    TextView view_aptitude;
    LinearLayout view_skills;
    LinearLayout view_weakness;
    LinearLayout view_inventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cha = Character.getInstance();
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

        seed();
        loadCharacter();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            FileOutputStream fos = openFileOutput("personaje", Context.MODE_PRIVATE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCharacter() {
        view_name.setText(cha.getName());
        view_race.setText(cha.getRace().getName());
        view_coins.setText(String.valueOf(cha.getCoins()));
        view_energyType.setText(cha.getEnergyType().getName());
        view_xp.setText(String.valueOf(cha.getExperience()));
        view_health.setText(cha.getHealth().getName());
        view_sanity.setText(cha.getSanity().getName());
        view_energy.setText(cha.getEnergy().getName());
        for (Wearable wearable : cha.getWearables().getWearables()) {
            TextView view = new TextView(getBaseContext());
            view.setText(wearable.getName());
            view_wearables.addView(view);
        }
        ;
        for (Weapon weapon : cha.getWeapons().getWeapons()) {
            TextView view = new TextView(getBaseContext());
            view.setText(weapon.getName());
            view_weapons.addView(view);
        }
        view_aptitude.setText(cha.getAptitude().getName());
        for (Attribute skill : cha.getSkills()) {
            TextView view = new TextView(getBaseContext());
            view.setText(skill.getName());
            view_skills.addView(view);
        }
        for (Attribute weakness : cha.getWeakness()) {
            TextView view = new TextView(getBaseContext());
            view.setText(weakness.getName());
            view_weakness.addView(view);
        }
        for (Item item : cha.getInventory().getItems()) {
            TableRow row = new TableRow(getBaseContext());

            TextView col0 = new TextView(getBaseContext());
            col0.setText(item.getName());
            row.addView(col0);
            TextView col1 = new TextView(getBaseContext());
            col1.setText(String.valueOf(item.getQuantity()));
            row.addView(col1);
            TextView col2 = new TextView(getBaseContext());
            col2.setText(String.valueOf(item.getWeight()));
            row.addView(col2);
            TextView col3 = new TextView(getBaseContext());
            col3.setText(String.valueOf(item.getValue()));
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
        cha.addItem("Llave random", 1, 0.1);
        cha.addItem("Pocion de salud", 3, 0.5, 10);
        cha.addItem("Pocion de stamina", 2, 0.5, 10);

        //seed armas
        cha.addWeapon("Hacha to Pro", "1/2/3", Aptitude.VIG, "Propiedad del super hacha noob", 0, 2, 1.5, 0);
        cha.addWeapon("Baston de mago", "1/1/1", Aptitude.LOG, "Incrementa en 2 el dano de hechizos", 0, 1, 1, 100);

        //seed armadura
        cha.addWearable("Caperusa roja", "Mision secundaria visitar el bosque", 1, 30);
        cha.addWearable("Calzas termicas", "Para aguantar el frio", 0.5, 10);
    }
}