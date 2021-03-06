package com.tdozo.vlp;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tdozo.vlp.entities.Attribute;
import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.entities.Item;
import com.tdozo.vlp.entities.Weapon;
import com.tdozo.vlp.entities.Wearable;
import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.Energy;
import com.tdozo.vlp.enums.Health;
import com.tdozo.vlp.enums.Sanity;

public class CharacterViewActivity extends AppCompatActivity {

    Character cha;
    TextView view_name;
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
        setContentView(R.layout.activity_character_view);

        loadViews();

        showCharacter();

        setOnClickListeners();

    }


    private void setOnClickListeners() {
        view_name.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(cha.getName()).setMessage(cha.getDescription()).show());

        view_health.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Health)).setItems(Health.getNames(this), (dialog, which) -> {
            Health health = Health.values()[which];
            cha.setHealth(health);
            view_health.setText(health.getName());
            cha.update(this);
        }).show());

        view_health.setOnLongClickListener(v -> {
            new MaterialAlertDialogBuilder(this).setTitle(cha.getHealth().getName()).setMessage(cha.getHealth().getDescription()).show();
            return true;
        });

        view_sanity.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Sanity)).setItems(Sanity.getNames(this), (dialog, which) -> {
            Sanity sanity = Sanity.values()[which];
            cha.setSanity(sanity);
            view_sanity.setText(sanity.getName());
            cha.update(this);
        }).show());

        view_sanity.setOnLongClickListener(v -> {
            new MaterialAlertDialogBuilder(this).setTitle(cha.getSanity().getName()).setMessage(cha.getSanity().getDescription()).show();
            return true;
        });

        view_energy.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Energy)).setItems(Energy.getNames(this), (dialog, which) -> {
            Energy energy = Energy.values()[which];
            cha.setEnergy(energy);
            view_energy.setText(energy.getName());
            cha.update(this);
        }).show());

        view_aptitude.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(cha.getAptitude().getName()).setMessage(cha.getAptitude().getDescription())
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
                cha.update(this);
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
                cha.update(this);
                showCoinsXpLoad();
            }).show();
        });


        newSkillClicListener(findViewById(R.id.skills_text));

        newWeaknessClicListener(findViewById(R.id.weaknesses_text));

        newWearableClicListener(findViewById(R.id.wearables_text));

        newItemClicListener(findViewById(R.id.inventory_text));

        newWeaponClicListener(findViewById(R.id.weapons_text));


    }

    private void newSkillClicListener(View viewById) {
        viewById.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.dialog_new_attribute, null);
            new MaterialAlertDialogBuilder(this).setTitle(R.string.New_Skill).setView(view).setPositiveButton(R.string.Save, (dialog, which) -> {
                EditText name = view.findViewById(R.id.new_attribute_name);
                EditText description = view.findViewById(R.id.new_attribute_description);
                if (!name.getText().toString().equals("") && !description.getText().toString().equals("")) {
                    cha.addSkill(name.getText().toString(), description.getText().toString(), this);
                    showSkills();
                } else {
                    Toast.makeText(this, getString(R.string.Fields_Incomplete), Toast.LENGTH_SHORT).show();
                }
            }).show();

        });
    }

    private void newWeaknessClicListener(View viewById) {
        viewById.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.dialog_new_attribute, null);
            new MaterialAlertDialogBuilder(this).setTitle(R.string.New_Weakness).setView(view).setPositiveButton(R.string.Save, (dialog, which) -> {
                EditText name = view.findViewById(R.id.new_attribute_name);
                EditText description = view.findViewById(R.id.new_attribute_description);
                if (!name.getText().toString().equals("") && !description.getText().toString().equals("")) {
                    cha.addWeakness(name.getText().toString(), description.getText().toString(), this);
                    showWeaknesses();
                } else {
                    Toast.makeText(this, getString(R.string.Fields_Incomplete), Toast.LENGTH_SHORT).show();
                }
            }).show();

        });
    }

    private void newItemClicListener(View vu) {
        vu.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.dialog_new_item, null);
            new MaterialAlertDialogBuilder(this).setTitle(R.string.New_Item).setView(view).setPositiveButton(R.string.Save, (dialog, which) -> {
                EditText name = view.findViewById(R.id.new_item_name);
                EditText quantity = view.findViewById(R.id.new_item_quantity);
                EditText weight = view.findViewById(R.id.new_item_weight);
                EditText value = view.findViewById(R.id.new_item_value);
                if (!name.getText().toString().equals("") && !weight.getText().toString().equals("") && !value.getText().toString().equals("") && !quantity.getText().toString().equals("")) {
                    cha.addItem(name.getText().toString(), Double.parseDouble(quantity.getText().toString()), Double.parseDouble(weight.getText().toString()), Integer.parseInt(value.getText().toString()), this);
                    showCoinsXpLoad();
                    showInventory();
                } else {
                    Toast.makeText(this, getString(R.string.Fields_Incomplete), Toast.LENGTH_SHORT).show();
                }
            }).show();
        });
    }

    private void newWearableClicListener(View view1) {
        view1.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.dialog_new_wearable, null);
            new MaterialAlertDialogBuilder(this).setTitle(R.string.New_Wearable).setView(view).setPositiveButton(R.string.Save, (dialog, which) -> {
                EditText name = view.findViewById(R.id.new_wearable_name);
                EditText property = view.findViewById(R.id.new_wearable_property);
                EditText weight = view.findViewById(R.id.new_wearable_weight);
                EditText value = view.findViewById(R.id.new_wearable_value);
                if (!name.getText().toString().equals("") && !weight.getText().toString().equals("") && !value.getText().toString().equals("")) {
                    cha.addWearable(name.getText().toString(), property.getText().toString(), Double.parseDouble(weight.getText().toString()), Integer.parseInt(value.getText().toString()), this);
                    showCoinsXpLoad();
                    showWearables();
                } else {
                    Toast.makeText(this, getString(R.string.Fields_Incomplete), Toast.LENGTH_SHORT).show();
                }
            }).show();
        });
    }

    private void newWeaponClicListener(View view1) {
        view1.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.dialog_new_weapon, null);
            Button aptitude = view.findViewById(R.id.new_weapon_aptitude);
            final Aptitude[] apt = new Aptitude[1];
            aptitude.setOnClickListener(h -> new MaterialAlertDialogBuilder(this).setTitle(getString(R.string.Aptitude)).setItems(Aptitude.getNames(this), (dialog, which) -> {
                aptitude.setText(Aptitude.values()[which].getName());
                apt[0] = Aptitude.values()[which];
            }).show());
            new MaterialAlertDialogBuilder(this).setTitle(R.string.New_Weapon).setView(view).setPositiveButton(R.string.Save, (dialog, which) -> {
                EditText name = view.findViewById(R.id.new_weapon_name);
                EditText damage = view.findViewById(R.id.new_weapon_damage);
                EditText property = view.findViewById(R.id.new_weapon_property);
                EditText capacity = view.findViewById(R.id.new_weapon_capacity);
                EditText hardness = view.findViewById(R.id.new_weapon_hardness);
                EditText weight = view.findViewById(R.id.new_weapon_weight);
                EditText value = view.findViewById(R.id.new_weapon_value);
                if (!name.getText().toString().equals("") && !damage.getText().toString().equals("") && apt[0] != null && !capacity.getText().toString().equals("") && !hardness.getText().toString().equals("") && !weight.getText().toString().equals("") && !value.getText().toString().equals("")) {
                    cha.addWeapon(name.getText().toString(),
                            damage.getText().toString(),
                            apt[0],
                            property.getText().toString(),
                            Integer.parseInt(capacity.getText().toString()),
                            Integer.parseInt(hardness.getText().toString()),
                            Double.parseDouble(weight.getText().toString()),
                            Integer.parseInt(value.getText().toString()), this);
                    showCoinsXpLoad();
                    showWeapons();
                } else {
                    Toast.makeText(this, getString(R.string.Fields_Incomplete), Toast.LENGTH_SHORT).show();
                }
            }).show();
        });
    }

    private void loadViews() {
        view_name = findViewById(R.id.name);
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

    public void showCharacter() {
        cha = (Character) getIntent().getSerializableExtra("Character");
        if (cha != null) {
            view_name.setText(cha.getName());
            view_energyType.setText(cha.getEnergyType().getName());
            showCoinsXpLoad();
            view_health.setText(cha.getHealth().getName());
            view_sanity.setText(cha.getSanity().getName());
            view_energy.setText(cha.getEnergy().getName());
            showWearables();
            showWeapons();
            view_aptitude.setText(cha.getAptitude().getName());
            showSkills();
            showWeaknesses();
            showInventory();
        }
    }

    private void showCoinsXpLoad() {
        view_xp.setText(String.valueOf(cha.getExperience()));
        view_coins.setText(String.valueOf(cha.getCoins()));
        view_load.setText(getString(R.string.load, cha.getActual_weight(), cha.getBaseWeight()));
    }

    private void showSkills() {
        view_skills.removeAllViews();
        for (Attribute skill : cha.getSkills()) {
            TextView view = new TextView(getBaseContext());
            view.setText(skill.getName());
            view.setGravity(Gravity.CENTER);
            view.setTextColor(getColor(R.color.black));
            view.setTextSize(16);
            view.setPadding(5, 5, 5, 5);
            view.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_thin));

            view.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(skill.getName()).setMessage(skill.getDescription()).setNegativeButton(R.string.Remove, (dialog, which) -> {
                cha.removeSkill(skill, this);
                showSkills();
            }).show());
            view_skills.addView(view);
        }

        TableRow row = generateAddRow();
        newSkillClicListener(row);
        view_skills.addView(row);
    }

    private void showWeaknesses() {
        view_weakness.removeAllViews();

        for (Attribute weakness : cha.getWeaknesses()) {
            TextView view = new TextView(getBaseContext());
            view.setText(weakness.getName());
            view.setGravity(Gravity.CENTER);
            view.setTextColor(getColor(R.color.black));
            view.setTextSize(16);
            view.setPadding(5, 5, 5, 5);
            view.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_thin));

            view.setOnClickListener(v -> new MaterialAlertDialogBuilder(this).setTitle(weakness.getName()).setMessage(weakness.getDescription()).setNegativeButton(R.string.Remove, (dialog, which) -> {
                cha.removeWeakness(weakness, this);
                showWeaknesses();
            })
                    .show());
            view_weakness.addView(view);
        }

        TableRow row = generateAddRow();
        newWeaknessClicListener(row);
        view_weakness.addView(row);
    }

    private void showWearables() {
        view_wearables.removeAllViews();
        for (Wearable wearable : cha.getWearables().getWearables()) {
            TableRow row = new TableRow(getBaseContext());
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border_thin));
            row.setGravity(Gravity.CENTER_VERTICAL);


            TextView col0 = new TextView(getBaseContext());
            col0.setText(wearable.getName());
            col0.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            col0.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            col0.setGravity(Gravity.CENTER_VERTICAL);
            col0.setPadding(10, 5, 5, 5);
            col0.setTextColor(getColor(R.color.black));
            col0.setTextSize(16);
            row.addView(col0);

            row.setOnClickListener(v -> {
                View view = getLayoutInflater().inflate(R.layout.dialog_view_wearable, null);
                if (wearable.getProperty().equals(""))
                    view.findViewById(R.id.layout_properties).setVisibility(View.GONE);
                else
                    ((TextView) view.findViewById(R.id.view_wearable_property)).setText(wearable.getProperty());
                ((TextView) view.findViewById(R.id.view_wearable_weight)).setText(String.valueOf(wearable.getWeight()));
                ((TextView) view.findViewById(R.id.view_wearable_value)).setText(String.valueOf(wearable.getValue()));

                new MaterialAlertDialogBuilder(this).setTitle(wearable.getName()).setView(view).setNegativeButton(R.string.Remove, (dialog, which) -> {
                    cha.removeWearable(wearable, this);
                    showCoinsXpLoad();
                    showWearables();
                }).show();

            });

            view_wearables.addView(row);
        }

        TableRow row = generateAddRow();
        newWearableClicListener(row);
        view_wearables.addView(row);
    }

    private void showWeapons() {
        view_weapons.removeAllViews();

        for (Weapon weapon : cha.getWeapons().getWeapons()) {
            TableRow row = new TableRow(getBaseContext());
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border_thin));
            row.setGravity(Gravity.CENTER_VERTICAL);


            TextView col0 = new TextView(getBaseContext());
            col0.setText(weapon.getName());
            col0.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            col0.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3.0f));
            col0.setGravity(Gravity.CENTER_VERTICAL);
            col0.setPadding(10, 5, 5, 5);
            col0.setTextColor(getColor(R.color.black));
            col0.setTextSize(16);
            row.addView(col0);

            TableRow.LayoutParams param = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);

            TextView col1 = new TextView(getBaseContext());
            col1.setText(String.valueOf(weapon.getDamage()));
            col1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col1.setLayoutParams(param);
            col1.setGravity(Gravity.CENTER_VERTICAL);
            col1.setTextColor(getColor(R.color.black));
            col1.setTextSize(16);
            row.addView(col1);

            TextView col2 = new TextView(getBaseContext());
            col2.setText(String.valueOf(weapon.getAptitude()));
            col2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col2.setLayoutParams(param);
            col2.setGravity(Gravity.CENTER_VERTICAL);
            col2.setTextColor(getColor(R.color.black));
            col2.setTextSize(16);
            row.addView(col2);

            TextView col3 = new TextView(getBaseContext());
            if (weapon.getCapacity() == 0) col3.setText("-");
            else col3.setText(String.valueOf(weapon.getCapacity()));
            col3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col3.setLayoutParams(param);
            col3.setGravity(Gravity.CENTER_VERTICAL);
            col3.setTextColor(getColor(R.color.black));
            col3.setTextSize(16);

            row.addView(col3);

            row.setOnClickListener(v -> {
                View view = getLayoutInflater().inflate(R.layout.dialog_view_weapon, null);
                ((TextView) view.findViewById(R.id.view_weapon_damage)).setText(weapon.getDamage());
                ((TextView) view.findViewById(R.id.view_weapon_aptitude)).setText(weapon.getAptitude().getName());
                if (weapon.getProperties().equals(""))
                    view.findViewById(R.id.layout_properties).setVisibility(View.GONE);
                else
                    ((TextView) view.findViewById(R.id.view_weapon_property)).setText(weapon.getProperties());
                ((TextView) view.findViewById(R.id.view_weapon_capacity)).setText(String.valueOf(weapon.getCapacity()));
                ((TextView) view.findViewById(R.id.view_weapon_hardness)).setText(String.valueOf(weapon.getHardness()));
                ((TextView) view.findViewById(R.id.view_weapon_weight)).setText(String.valueOf(weapon.getWeight()));
                ((TextView) view.findViewById(R.id.view_weapon_value)).setText(String.valueOf(weapon.getValue()));

                new MaterialAlertDialogBuilder(this).setTitle(weapon.getName()).setView(view).setNegativeButton(R.string.Remove, (dialog, which) -> {
                    cha.removeWeapon(weapon, this);
                    showCoinsXpLoad();
                    showWeapons();
                }).show();

            });

            view_weapons.addView(row);
        }
        TableRow row = generateAddRow();
        newWeaponClicListener(row);
        view_weapons.addView(row);
    }

    private void showInventory() {
        view_inventory.removeAllViews();

        for (Item item : cha.getInventory().getItems()) {
            TableRow row = new TableRow(getBaseContext());
            row.setGravity(Gravity.CENTER_VERTICAL);

            TextView col0 = new TextView(getBaseContext());
            col0.setText(item.getName());
            col0.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3.0f));
            col0.setGravity(Gravity.CENTER);
            col0.setPadding(5, 5, 5, 5);
            col0.setTextColor(getColor(R.color.black));
            col0.setTextSize(16);
            col0.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_thin));
            row.addView(col0);

            TableRow.LayoutParams param = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);

            TextView col1 = new TextView(getBaseContext());
            col1.setText(String.valueOf(item.getQuantity()));
            col1.setLayoutParams(param);
            col1.setGravity(Gravity.CENTER);
            col1.setTextColor(getColor(R.color.black));
            col1.setTextSize(16);
            col1.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_thin));
            row.addView(col1);

            row.setOnClickListener(v -> {
                View view = getLayoutInflater().inflate(R.layout.dialog_view_item, null);
                ((EditText) view.findViewById(R.id.view_item_quantity)).setText(String.valueOf(item.getQuantity()));
                ((EditText) view.findViewById(R.id.view_item_value)).setText(String.valueOf(item.getValue()));
                ((TextView) view.findViewById(R.id.view_item_weight)).setText(String.valueOf(item.getWeight()));

                new MaterialAlertDialogBuilder(this).setTitle(item.getName()).setView(view).setPositiveButton(R.string.Save, (dialog, which) -> {
                    EditText value = view.findViewById(R.id.view_item_value);
                    EditText quantity = view.findViewById(R.id.view_item_quantity);
                    if (!value.getText().toString().equals("") && !quantity.getText().toString().equals("")) {
                        item.setValue(Integer.parseInt(value.getText().toString()));
                        item.setQuantity(Double.parseDouble(quantity.getText().toString()));
                        cha.getInventory().calculateWeight();
                        cha.getInventory().update(this);
                        item.createOrUpdate(this);
                        showCoinsXpLoad();
                        showInventory();
                    } else {
                        Toast.makeText(this, getString(R.string.Fields_Incomplete), Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton(R.string.Remove, (dialog, which) -> {
                    cha.removeItem(item, this);
                    showCoinsXpLoad();
                    showInventory();
                }).show();

            });

            view_inventory.addView(row);
        }

        TableRow row = generateAddRow();
        newItemClicListener(row);
        view_inventory.addView(row);
    }

    private TableRow generateAddRow() {
        TableRow row = new TableRow(getBaseContext());
        row.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_thin_grey));
        row.setPadding(10, 10, 50, 10);
        row.setGravity(Gravity.END);


        ImageView btn = new ImageView(this);
        btn.setLayoutParams(new TableRow.LayoutParams(47, 47));
        btn.setBackground(AppCompatResources.getDrawable(this, R.drawable.add_btn));
        btn.setScaleType(ImageView.ScaleType.FIT_XY);

        row.addView(btn);
        return row;
    }

}