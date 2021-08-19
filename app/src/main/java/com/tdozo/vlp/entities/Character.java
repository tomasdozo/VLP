package com.tdozo.vlp.entities;


import android.content.Context;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.tdozo.vlp.CharacterActivity;
import com.tdozo.vlp.database.CharacterDao;
import com.tdozo.vlp.database.DatabaseVLP;
import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.Energy;
import com.tdozo.vlp.enums.EnergyType;
import com.tdozo.vlp.enums.Health;
import com.tdozo.vlp.enums.Race;
import com.tdozo.vlp.enums.Sanity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Character implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private int experience;
    private int coins;
    private EnergyType energyType;
    private Energy energy;
    private Health health;
    private Sanity sanity;
    private Aptitude aptitude;
    private Race race;
    private int baseWeight;

    @Ignore
    private List<Attribute> skills;
    @Ignore
    private List<Attribute> weakness;
    @Ignore
    private InventoryWeapons weapons;
    @Ignore
    private InventoryWearables wearables;
    @Ignore
    private Inventory inventory;

    public Character() {
        skills = new ArrayList<>();
        weakness = new ArrayList<>();
        coins = 0;
        experience = 100;
        energy = Energy.ENERGETIC;
        health = Health.HEALTHY;
        sanity = Sanity.SANE;
        baseWeight = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getExperience() {
        return experience;
    }

    public int getCoins() {
        return coins;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public Energy getEnergy() {
        return energy;
    }

    public Health getHealth() {
        return health;
    }

    public Sanity getSanity() {
        return sanity;
    }

    public Aptitude getAptitude() {
        return aptitude;
    }

    public Race getRace() {
        return race;
    }

    public List<Attribute> getSkills() {
        return skills;
    }

    public List<Attribute> getWeakness() {
        return weakness;
    }

    public InventoryWearables getWearables() {
        return wearables;
    }

    public InventoryWeapons getWeapons() {
        return weapons;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getActual_weight() {
        return weapons.getWeight() + wearables.getWeight() + inventory.getWeight();
    }

    public int getBaseWeight() {
        return baseWeight;
    }

    public void setSkills(List<Attribute> skills) {
        this.skills = skills;
    }

    public void setWeakness(List<Attribute> weakness) {
        this.weakness = weakness;
    }

    public void setWeapons(InventoryWeapons weapons) {
        this.weapons = weapons;
    }

    public void setWearables(InventoryWearables wearables) {
        this.wearables = wearables;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public void setSanity(Sanity sanity) {
        this.sanity = sanity;
    }

    public void setAptitude(Aptitude aptitude) {
        this.aptitude = aptitude;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setBaseWeight(int baseWeight) {
        this.baseWeight = baseWeight;
    }

    public void addSkill(String name, String description, Context context) {
        Attribute attribute = new Attribute(name, description, true, id);
        skills.add(attribute);
        attribute.createOrUpdate(context);
    }

    public void addWeakness(String name, String description, Context context) {
        Attribute attribute = new Attribute(name, description, false, id);
        weakness.add(attribute);
        attribute.createOrUpdate(context);
    }

    public void addItem(String name, double quantity, double weight, int value, Context context) {
        inventory.addItem(name, quantity, weight, value, context);
    }

    public void addWeapon(String name, String damage, Aptitude apt, String properties, int capacity, int hardness, double weight, int value, Context context) {
        weapons.addItem(name, weight, value, properties, damage, apt, capacity, hardness, context);
    }

    public void addWearable(String name, String properties, double weight, int value, Context context) {
        wearables.addWearable(name, weight, value, properties, context);
    }

    public void removeWeapon(Weapon weapon) {
        weapons.removeItem(weapon);
        //todo persist
    }

    public void removeWearable(Wearable wearable) {
        wearables.removeItem(wearable);
        //todo persist
    }

    public void removeItem(Item item) {
        inventory.removeItem(item);
        //todo persist
    }

    public void removeSkill(Attribute skill) {
        skills.remove(skill);
        //todo persist
    }

    public void removeWeakness(Attribute skill) {
        weakness.remove(skill);
        //todo persist
    }

    public void update(Context context) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            DatabaseVLP.getDatabase(context).characterDao().updateCharacter(this);
            inventory.update(context);
            weapons.update(context);
            wearables.update(context);

        });
    }

    public void create(CharacterActivity activity) {
        DatabaseVLP.databaseWriteExecutor.execute(() -> {
            CharacterDao characterDao = DatabaseVLP.getDatabase(activity).characterDao();

            id = (int) characterDao.insertCharacter(this);
            weapons = new InventoryWeapons(id);
            wearables = new InventoryWearables(id);
            inventory = new Inventory(id);
            inventory.create(activity);
            weapons.create(activity);
            wearables.create(activity);

            activity.saved();
        });
    }
}
