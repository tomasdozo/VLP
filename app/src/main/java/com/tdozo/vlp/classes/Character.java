package com.tdozo.vlp.classes;


import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.Energy;
import com.tdozo.vlp.enums.EnergyType;
import com.tdozo.vlp.enums.Health;
import com.tdozo.vlp.enums.Race;
import com.tdozo.vlp.enums.Sanity;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
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
    private final ArrayList<Attribute> skills;
    private final ArrayList<Attribute> weakness;
    private final InventoryWeapons weapons;
    private double actual_weight;
    private final InventoryWearables wearables;
    private final Inventory inventory;
    private int baseWeight;

    public Character() {
        skills = new ArrayList<>();
        weakness = new ArrayList<>();
        coins = 0;
        experience = 100;
        energy = Energy.ENERGETIC;
        health = Health.HEALTHY;
        sanity = Sanity.SANE;
        baseWeight = 0;
        actual_weight = 0;
        weapons = new InventoryWeapons();
        wearables = new InventoryWearables();
        inventory = new Inventory();
    }

    public ArrayList<Attribute> getSkills() {
        return skills;
    }

    public ArrayList<Attribute> getWeakness() {
        return weakness;
    }

    public void removeSkill(Attribute skill) {
        skills.remove(skill);
    }

    public void removeWeakness(Attribute skill) {
        weakness.remove(skill);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Sanity getSanity() {
        return sanity;
    }

    public void setSanity(Sanity sanity) {
        this.sanity = sanity;
    }

    public Aptitude getAptitude() {
        return aptitude;
    }

    public void setAptitude(Aptitude aptitude) {
        this.aptitude = aptitude;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(int baseWeight) {
        this.baseWeight = baseWeight;
    }

    public double getActual_weight() {
        double aux = 0;
        aux += weapons.getWeight() + wearables.getWeight() + inventory.getWeight();
        actual_weight = aux;
        return actual_weight;
    }


    public void addSkill(String name, String description) {
        skills.add(new Attribute(name, description));
    }

    public void addWeakness(String name, String description) {
        weakness.add(new Attribute(name, description));
    }

    public void addItem(String name, int quantity, double weight) {
        inventory.addItem(new Item(name, quantity, weight));
    }

    public void addItem(String name, double quantity, double weight, int value) {
        inventory.addItem(new Item(name, quantity, weight, value));
    }

    public void addWeapon(String name, String damage, Aptitude apt, String properties, int capacity, int defense, double weight, int value) {
        weapons.addItem(new Weapon(name, weight, value, properties, damage, apt, capacity, defense));
    }

    public void addWearable(String name, String properties, double weight, int value) {
        wearables.addItem(new Wearable(name, weight, value, properties));
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

    public void removeWearable(Wearable wearable) {
        wearables.removeItem(wearable);
    }

    public void removeItem(Item item) {
        inventory.removeItem(item);
    }
}
