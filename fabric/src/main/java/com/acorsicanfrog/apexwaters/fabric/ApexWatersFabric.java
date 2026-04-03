package com.acorsicanfrog.apexwaters.fabric;

import com.acorsicanfrog.apexwaters.ApexWatersCommon;
import com.acorsicanfrog.apexwaters.config.ApexWatersConfig;
import com.acorsicanfrog.apexwaters.entity.GreatWhiteSharkEntity;
import com.acorsicanfrog.apexwaters.entity.SharkSpawner;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

public class ApexWatersFabric implements ModInitializer {

    public static EntityType<GreatWhiteSharkEntity> GREAT_WHITE_SHARK;
    public static Item SPAWN_EGG;
    public static Item RAW_SHARK_MEAT;
    public static Item COOKED_SHARK_MEAT;

    @Override
    public void onInitialize() {
        // Load config
        ApexWatersConfig.set(FabricConfig.load());

        // Register entity type
        GREAT_WHITE_SHARK = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "great_white_shark"),
                EntityType.Builder.of(GreatWhiteSharkEntity::new, MobCategory.WATER_CREATURE)
                        .sized(GreatWhiteSharkEntity.HITBOX_WIDTH, GreatWhiteSharkEntity.HITBOX_HEIGHT)
                        .clientTrackingRange(10)
                        .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "great_white_shark")))
        );

        // Register items
        Identifier spawnEggId = Identifier.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "great_white_shark_spawn_egg");
        SPAWN_EGG = Registry.register(
                BuiltInRegistries.ITEM,
                spawnEggId,
                new SpawnEggItem(new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, spawnEggId))
                        .spawnEgg(GREAT_WHITE_SHARK))
        );

        Identifier rawId = Identifier.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "great_white_shark_raw");
        RAW_SHARK_MEAT = Registry.register(
                BuiltInRegistries.ITEM,
                rawId,
                new Item(new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, rawId))
                        .food(new FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.3F)
                        .build()))
        );

        Identifier cookedId = Identifier.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "great_white_shark_cooked");
        COOKED_SHARK_MEAT = Registry.register(
                BuiltInRegistries.ITEM,
                cookedId,
                new Item(new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, cookedId))
                        .food(new FoodProperties.Builder()
                        .nutrition(8)
                        .saturationModifier(0.8F)
                        .build()))
        );

        // Initialize common module
        ApexWatersCommon.init(() -> GREAT_WHITE_SHARK, () -> SPAWN_EGG, () -> RAW_SHARK_MEAT, () -> COOKED_SHARK_MEAT);

        // Register entity attributes
        FabricDefaultAttributeRegistry.register(GREAT_WHITE_SHARK, GreatWhiteSharkEntity.createAttributes());

        // Register spawn placements (no Fabric API alternative exists)
        SpawnPlacements.register(GREAT_WHITE_SHARK,
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ApexWatersCommon::checkSharkSpawnRules);

        // Add to creative tabs
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.addAfter(Items.FROG_SPAWN_EGG, SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.addAfter(Items.COOKED_SALMON, COOKED_SHARK_MEAT);
            entries.addAfter(Items.COOKED_SALMON, RAW_SHARK_MEAT);
        });

        // Death event for hunger reset
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            ApexWatersCommon.onLivingDeath(entity, source);
        });

        // Tick custom spawner for overworld
        SharkSpawner spawner = new SharkSpawner();
        ServerTickEvents.END_WORLD_TICK.register(level -> {
            if (level.dimension() == Level.OVERWORLD) {
                spawner.tick(level, false);
            }
        });
    }
}