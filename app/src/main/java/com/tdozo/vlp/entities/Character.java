package com.tdozo.vlp.entities;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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
    @Ignore
    private List<Attribute> skills;
    @Ignore
    private List<Attribute> weakness;
    @Ignore
    private InventoryWeapons weapons;

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
        weapons = new InventoryWeapons(id);
        wearables = new InventoryWearables(id);
        inventory = new Inventory(id);
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

    public void addSkill(String name, String description) {
        skills.add(new Attribute(name, description, true, id));
        //todo persist
    }

    public void addWeakness(String name, String description) {
        weakness.add(new Attribute(name, description, false, id));
        //todo persist
    }

    public void addItem(String name, double quantity, double weight, int value) {
        inventory.addItem(name, quantity, weight, value);
        //todo persist
    }

    public void addWeapon(String name, String damage, Aptitude apt, String properties, int capacity, int hardness, double weight, int value) {
        weapons.addItem(name, weight, value, properties, damage, apt, capacity, hardness);
        //todo persist
    }

    public void addWearable(String name, String properties, double weight, int value) {
        wearables.addItem(name, weight, value, properties);
        //todo persist
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
}
