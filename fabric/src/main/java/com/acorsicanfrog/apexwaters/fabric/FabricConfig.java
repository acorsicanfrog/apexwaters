package com.acorsicanfrog.apexwaters.fabric;

import com.acorsicanfrog.apexwaters.config.ApexWatersConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FabricConfig extends ApexWatersConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Path.of("config", "apexwaters.json");

    // Serializable fields
    private double attackDamage = DEFAULT_ATTACK_DAMAGE;
    private double movementSpeed = DEFAULT_MOVEMENT_SPEED;
    private double maxHealth = DEFAULT_MAX_HEALTH;
    private double scaleMin = DEFAULT_SCALE_MIN;
    private double scaleMax = DEFAULT_SCALE_MAX;
    private int hungerMax = DEFAULT_HUNGER_MAX;
    private int hungerDrainRate = DEFAULT_HUNGER_DRAIN_RATE;
    private boolean alwaysAggressive = DEFAULT_ALWAYS_AGGRESSIVE;
    private boolean targetPlayersInBoats = DEFAULT_TARGET_PLAYERS_IN_BOATS;
    private boolean attackHurtPlayers = DEFAULT_ATTACK_HURT_PLAYERS;
    private boolean randomStartHunger = DEFAULT_RANDOM_START_HUNGER;
    private int spawnInterval = DEFAULT_SPAWN_INTERVAL;
    private int spawnChance = DEFAULT_SPAWN_CHANCE;
    private int spawnMinDistance = DEFAULT_SPAWN_MIN_DISTANCE;

    public static FabricConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                FabricConfig config = GSON.fromJson(json, FabricConfig.class);
                if (config != null) {
                    config.save();
                    return config;
                }
            } catch (Exception e) {
                System.err.println("[ApexWaters] Failed to read config, using defaults: " + e.getMessage());
            }
        }

        FabricConfig config = new FabricConfig();
        config.save();
        return config;
    }

    private void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(this));
        } catch (IOException e) {
            System.err.println("[ApexWaters] Failed to save config: " + e.getMessage());
        }
    }

    @Override
    public double attackDamage() { return attackDamage; }

    @Override
    public double movementSpeed() { return movementSpeed; }

    @Override
    public double maxHealth() { return maxHealth; }

    @Override
    public double scaleMin() { return scaleMin; }

    @Override
    public double scaleMax() { return scaleMax; }

    @Override
    public int hungerMax() { return hungerMax; }

    @Override
    public int hungerDrainRate() { return hungerDrainRate; }

    @Override
    public boolean alwaysAggressive() { return alwaysAggressive; }

    @Override
    public boolean targetPlayersInBoats() { return targetPlayersInBoats; }

    @Override
    public boolean attackHurtPlayers() { return attackHurtPlayers; }

    @Override
    public boolean randomStartHunger() { return randomStartHunger; }

    @Override
    public int spawnInterval() { return spawnInterval; }

    @Override
    public int spawnChance() { return spawnChance; }

    @Override
    public int spawnMinDistance() { return spawnMinDistance; }
}