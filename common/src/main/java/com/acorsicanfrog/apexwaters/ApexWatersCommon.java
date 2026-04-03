package com.acorsicanfrog.apexwaters;

import com.acorsicanfrog.apexwaters.entity.GreatWhiteSharkEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.fish.WaterAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.function.Supplier;

public class ApexWatersCommon {
    public static final String MOD_ID = "apexwaters";

    public static Supplier<EntityType<GreatWhiteSharkEntity>> SHARK_ENTITY_TYPE;
    public static Supplier<Item> SPAWN_EGG_ITEM;
    public static Supplier<Item> RAW_SHARK_MEAT;
    public static Supplier<Item> COOKED_SHARK_MEAT;

    public static void init(Supplier<EntityType<GreatWhiteSharkEntity>> sharkType,
                            Supplier<Item> spawnEgg,
                            Supplier<Item> rawMeat,
                            Supplier<Item> cookedMeat) {
        SHARK_ENTITY_TYPE = sharkType;
        SPAWN_EGG_ITEM = spawnEgg;
        RAW_SHARK_MEAT = rawMeat;
        COOKED_SHARK_MEAT = cookedMeat;
    }

    public static boolean checkSharkSpawnRules(EntityType<? extends WaterAnimal> type, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        if (spawnType == EntitySpawnReason.NATURAL || spawnType == EntitySpawnReason.CHUNK_GENERATION) {
            if (level.getBiome(pos).is(ModTags.SHARK_SPAWN_BLACKLIST)) {
                return false;
            }
        }
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(type, level, spawnType, pos, random);
    }

    public static void onLivingDeath(LivingEntity entity, DamageSource source) {
        if (source.getEntity() instanceof GreatWhiteSharkEntity shark) {
            shark.resetHunger();
        }
    }
}