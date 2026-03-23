package com.acorsicanfrog.apexwaters;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Stats
    static {
        BUILDER.push("stats");
    }

    public static final ModConfigSpec.DoubleValue ATTACK_DAMAGE = BUILDER
            .comment("Attack damage of the great white shark")
            .defineInRange("attackDamage", 10.0, 1.0, 100.0);

    public static final ModConfigSpec.DoubleValue MOVEMENT_SPEED = BUILDER
            .comment("Movement speed of the great white shark")
            .defineInRange("movementSpeed", 1.0, 0.1, 10.0);

    public static final ModConfigSpec.DoubleValue MAX_HEALTH = BUILDER
            .comment("Max health of the great white shark")
            .defineInRange("maxHealth", 40.0, 1.0, 200.0);

    public static final ModConfigSpec.DoubleValue SCALE_MIN = BUILDER
            .comment("Minimum scale of the great white shark")
            .defineInRange("scaleMin", 0.85, 0.1, 5.0);

    public static final ModConfigSpec.DoubleValue SCALE_MAX = BUILDER
            .comment("Maximum scale of the great white shark")
            .defineInRange("scaleMax", 1.35, 0.1, 5.0);

    static {
        BUILDER.pop();
    }

    // Hunger
    static {
        BUILDER.push("hunger");
    }

    public static final ModConfigSpec.IntValue HUNGER_MAX = BUILDER
            .comment("Maximum hunger value for the great white shark")
            .defineInRange("hungerMax", 120, 1, 10000);

    public static final ModConfigSpec.IntValue HUNGER_DRAIN_RATE = BUILDER
            .comment("How many hunger units are drained per second")
            .defineInRange("hungerDrainRate", 1, 1, 100);

    public static final ModConfigSpec.BooleanValue ALWAYS_AGGRESSIVE = BUILDER
            .comment("If true, the shark is always aggressive regardless of hunger")
            .define("alwaysAggressive", false);

    public static final ModConfigSpec.BooleanValue TARGET_PLAYERS_IN_BOATS = BUILDER
            .comment("If true, sharks can target players riding a boat")
            .define("targetPlayersInBoats", false);

    public static final ModConfigSpec.BooleanValue ATTACK_HURT_PLAYERS = BUILDER
            .comment("If true, sharks will target players that are not at full health, even if the shark is not hungry")
            .define("attackHurtPlayers", true);

    public static final ModConfigSpec.BooleanValue RANDOM_START_HUNGER = BUILDER
            .comment("If true, newly spawned sharks start with a random hunger value between 0 and max")
            .define("randomStartHunger", true);

    static {
        BUILDER.pop();
    }

    // Spawning
    static {
        BUILDER.push("spawning");
    }

    public static final ModConfigSpec.IntValue SPAWN_INTERVAL = BUILDER
            .comment("How often (in ticks) the custom spawner attempts to spawn sharks per player (default: 1200 = 60 seconds)")
            .defineInRange("spawnInterval", 1200, 20, 72000);

    public static final ModConfigSpec.IntValue SPAWN_CHANCE = BUILDER
            .comment("1 in N chance of spawning a shark per attempt per player (higher = rarer, default: 3)")
            .defineInRange("spawnChance", 3, 1, 100);

    public static final ModConfigSpec.IntValue SPAWN_MIN_DISTANCE = BUILDER
            .comment("Minimum distance (in blocks) from an existing shark before a new one can spawn (default: 64)")
            .defineInRange("spawnMinDistance", 64, 0, 512);

    static {
        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();
}