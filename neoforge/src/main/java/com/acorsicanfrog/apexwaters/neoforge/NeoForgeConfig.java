package com.acorsicanfrog.apexwaters.neoforge;

import com.acorsicanfrog.apexwaters.config.ApexWatersConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfig extends ApexWatersConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Stats
    private static final ModConfigSpec.DoubleValue ATTACK_DAMAGE_VALUE;
    private static final ModConfigSpec.DoubleValue MOVEMENT_SPEED_VALUE;
    private static final ModConfigSpec.DoubleValue MAX_HEALTH_VALUE;
    private static final ModConfigSpec.DoubleValue SCALE_MIN_VALUE;
    private static final ModConfigSpec.DoubleValue SCALE_MAX_VALUE;

    // Hunger
    private static final ModConfigSpec.IntValue HUNGER_MAX_VALUE;
    private static final ModConfigSpec.IntValue HUNGER_DRAIN_RATE_VALUE;
    private static final ModConfigSpec.BooleanValue ALWAYS_AGGRESSIVE_VALUE;
    private static final ModConfigSpec.BooleanValue TARGET_PLAYERS_IN_BOATS_VALUE;
    private static final ModConfigSpec.BooleanValue ATTACK_HURT_PLAYERS_VALUE;
    private static final ModConfigSpec.BooleanValue RANDOM_START_HUNGER_VALUE;

    // Spawning
    private static final ModConfigSpec.IntValue SPAWN_INTERVAL_VALUE;
    private static final ModConfigSpec.IntValue SPAWN_CHANCE_VALUE;
    private static final ModConfigSpec.IntValue SPAWN_MIN_DISTANCE_VALUE;

    static final ModConfigSpec SPEC;

    static {
        BUILDER.push("stats");
        ATTACK_DAMAGE_VALUE = BUILDER
                .comment("Attack damage of the great white shark")
                .defineInRange("attackDamage", DEFAULT_ATTACK_DAMAGE, 1.0, 100.0);
        MOVEMENT_SPEED_VALUE = BUILDER
                .comment("Movement speed of the great white shark")
                .defineInRange("movementSpeed", DEFAULT_MOVEMENT_SPEED, 0.1, 10.0);
        MAX_HEALTH_VALUE = BUILDER
                .comment("Max health of the great white shark")
                .defineInRange("maxHealth", DEFAULT_MAX_HEALTH, 1.0, 200.0);
        SCALE_MIN_VALUE = BUILDER
                .comment("Minimum scale of the great white shark")
                .defineInRange("scaleMin", DEFAULT_SCALE_MIN, 0.1, 5.0);
        SCALE_MAX_VALUE = BUILDER
                .comment("Maximum scale of the great white shark")
                .defineInRange("scaleMax", DEFAULT_SCALE_MAX, 0.1, 5.0);
        BUILDER.pop();

        BUILDER.push("hunger");
        HUNGER_MAX_VALUE = BUILDER
                .comment("Maximum hunger value for the great white shark")
                .defineInRange("hungerMax", DEFAULT_HUNGER_MAX, 1, 10000);
        HUNGER_DRAIN_RATE_VALUE = BUILDER
                .comment("How many hunger units are drained per second")
                .defineInRange("hungerDrainRate", DEFAULT_HUNGER_DRAIN_RATE, 1, 100);
        ALWAYS_AGGRESSIVE_VALUE = BUILDER
                .comment("If true, the shark is always aggressive regardless of hunger")
                .define("alwaysAggressive", DEFAULT_ALWAYS_AGGRESSIVE);
        TARGET_PLAYERS_IN_BOATS_VALUE = BUILDER
                .comment("If true, sharks can target players riding a boat")
                .define("targetPlayersInBoats", DEFAULT_TARGET_PLAYERS_IN_BOATS);
        ATTACK_HURT_PLAYERS_VALUE = BUILDER
                .comment("If true, sharks will target players that are not at full health, even if the shark is not hungry")
                .define("attackHurtPlayers", DEFAULT_ATTACK_HURT_PLAYERS);
        RANDOM_START_HUNGER_VALUE = BUILDER
                .comment("If true, newly spawned sharks start with a random hunger value between 0 and max")
                .define("randomStartHunger", DEFAULT_RANDOM_START_HUNGER);
        BUILDER.pop();

        BUILDER.push("spawning");
        SPAWN_INTERVAL_VALUE = BUILDER
                .comment("How often (in ticks) the custom spawner attempts to spawn sharks per player (default: 1200 = 60 seconds)")
                .defineInRange("spawnInterval", DEFAULT_SPAWN_INTERVAL, 20, 72000);
        SPAWN_CHANCE_VALUE = BUILDER
                .comment("1 in N chance of spawning a shark per attempt per player (higher = rarer, default: 3)")
                .defineInRange("spawnChance", DEFAULT_SPAWN_CHANCE, 1, 100);
        SPAWN_MIN_DISTANCE_VALUE = BUILDER
                .comment("Minimum distance (in blocks) from an existing shark before a new one can spawn (default: 64)")
                .defineInRange("spawnMinDistance", DEFAULT_SPAWN_MIN_DISTANCE, 0, 512);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    @Override
    public double attackDamage() { return ATTACK_DAMAGE_VALUE.getAsDouble(); }

    @Override
    public double movementSpeed() { return MOVEMENT_SPEED_VALUE.getAsDouble(); }

    @Override
    public double maxHealth() { return MAX_HEALTH_VALUE.getAsDouble(); }

    @Override
    public double scaleMin() { return SCALE_MIN_VALUE.getAsDouble(); }

    @Override
    public double scaleMax() { return SCALE_MAX_VALUE.getAsDouble(); }

    @Override
    public int hungerMax() { return HUNGER_MAX_VALUE.getAsInt(); }

    @Override
    public int hungerDrainRate() { return HUNGER_DRAIN_RATE_VALUE.getAsInt(); }

    @Override
    public boolean alwaysAggressive() { return ALWAYS_AGGRESSIVE_VALUE.getAsBoolean(); }

    @Override
    public boolean targetPlayersInBoats() { return TARGET_PLAYERS_IN_BOATS_VALUE.getAsBoolean(); }

    @Override
    public boolean attackHurtPlayers() { return ATTACK_HURT_PLAYERS_VALUE.getAsBoolean(); }

    @Override
    public boolean randomStartHunger() { return RANDOM_START_HUNGER_VALUE.getAsBoolean(); }

    @Override
    public int spawnInterval() { return SPAWN_INTERVAL_VALUE.getAsInt(); }

    @Override
    public int spawnChance() { return SPAWN_CHANCE_VALUE.getAsInt(); }

    @Override
    public int spawnMinDistance() { return SPAWN_MIN_DISTANCE_VALUE.getAsInt(); }
}
