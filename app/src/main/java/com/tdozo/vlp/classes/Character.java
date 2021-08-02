package com.tdozo.vlp.classes;


import com.tdozo.vlp.enums.Aptitude;
import com.tdozo.vlp.enums.Energy;
import com.tdozo.vlp.enums.EnergyType;
import com.tdozo.vlp.enums.Health;
import com.tdozo.vlp.enums.Race;
import com.tdozo.vlp.enums.Sanity;

import java.util.ArrayList;

public class Character {
    private final static Character instance = new Character();
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
    private ArrayList<Attribute> skills;
    private ArrayList<Attribute> weakness;
    private int weight;
    private double actual_weight;
    private InventoryWeapons weapons;
    private InventoryWearables wearables;
    private Inventory inventory;

    public Character() {
        skills = new ArrayList<>();
        weakness = new ArrayList<>();
        coins = 0;
        experience = 100;
        energy = Energy.ENERGETIC;
        health = Health.HEALTHY;
        sanity = Sanity.SANE;
        weight = 15;
        actual_weight = 0;
        weapons = new InventoryWeapons();
        wearables = new InventoryWearables();
        inventory = new Inventory();
    }

    public static Character getInstance() {
        return instance;
    }

    public ArrayList<Attribute> getSkills() {
        return skills;
    }

    public ArrayList<Attribute> getWeakness() {
        return weakness;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public void addItem(String name, int quantity, double weight, int value) {
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
}
