package com.acorsicanfrog.apexwaters.config;

public abstract class ApexWatersConfig {
    private static ApexWatersConfig instance;

    public static ApexWatersConfig get() {
        return instance;
    }

    public static void set(ApexWatersConfig config) {
        instance = config;
    }

    // Default values
    public static final double DEFAULT_ATTACK_DAMAGE = 10.0;
    public static final double DEFAULT_MOVEMENT_SPEED = 1.0;
    public static final double DEFAULT_MAX_HEALTH = 40.0;
    public static final double DEFAULT_SCALE_MIN = 0.85;
    public static final double DEFAULT_SCALE_MAX = 1.35;
    public static final int DEFAULT_HUNGER_MAX = 120;
    public static final int DEFAULT_HUNGER_DRAIN_RATE = 1;
    public static final boolean DEFAULT_ALWAYS_AGGRESSIVE = false;
    public static final boolean DEFAULT_TARGET_PLAYERS_IN_BOATS = false;
    public static final boolean DEFAULT_ATTACK_HURT_PLAYERS = true;
    public static final boolean DEFAULT_RANDOM_START_HUNGER = true;
    public static final int DEFAULT_SPAWN_INTERVAL = 600;
    public static final int DEFAULT_SPAWN_CHANCE = 3;
    public static final int DEFAULT_SPAWN_MIN_DISTANCE = 64;

    // Stats
    public abstract double attackDamage();
    public abstract double movementSpeed();
    public abstract double maxHealth();
    public abstract double scaleMin();
    public abstract double scaleMax();

    // Hunger
    public abstract int hungerMax();
    public abstract int hungerDrainRate();
    public abstract boolean alwaysAggressive();
    public abstract boolean targetPlayersInBoats();
    public abstract boolean attackHurtPlayers();
    public abstract boolean randomStartHunger();

    // Spawning
    public abstract int spawnInterval();
    public abstract int spawnChance();
    public abstract int spawnMinDistance();
}