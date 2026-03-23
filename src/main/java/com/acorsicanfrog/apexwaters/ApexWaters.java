package com.acorsicanfrog.apexwaters;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.level.ModifyCustomSpawnersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.acorsicanfrog.apexwaters.entity.GreatWhiteSharkEntity;
import com.acorsicanfrog.apexwaters.entity.SharkSpawner;

@Mod(ApexWaters.MODID)
public class ApexWaters {
    public static final String MODID = "apexwaters";

    // Entity Types
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<GreatWhiteSharkEntity>> GREAT_WHITE_SHARK =
            ENTITY_TYPES.register("great_white_shark", () -> EntityType.Builder.of(GreatWhiteSharkEntity::new, MobCategory.WATER_CREATURE)
                .sized(GreatWhiteSharkEntity.HITBOX_WIDTH, GreatWhiteSharkEntity.HITBOX_HEIGHT)
                    .clientTrackingRange(10)
                    .build(ResourceLocation.fromNamespaceAndPath(MODID, "great_white_shark").toString()));

    // Items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> GREAT_WHITE_SHARK_SPAWN_EGG = ITEMS.register("great_white_shark_spawn_egg",
            () -> new DeferredSpawnEggItem(GREAT_WHITE_SHARK, 0x6B7B8D, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> RAW_SHARK_MEAT = ITEMS.registerSimpleItem("great_white_shark_raw",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()));

    public static final DeferredItem<Item> COOKED_SHARK_MEAT = ITEMS.registerSimpleItem("great_white_shark_cooked",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationModifier(0.8F)
                    .build()));

    public ApexWaters(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(ApexWaters::registerAttributes);
        modEventBus.addListener(ApexWaters::registerSpawnPlacements);
        modEventBus.addListener(ApexWaters::buildCreativeTabContents);

        NeoForge.EVENT_BUS.addListener(ApexWaters::onModifyCustomSpawners);

        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GREAT_WHITE_SHARK.get(), GreatWhiteSharkEntity.createAttributes().build());
    }

    public static boolean checkSharkSpawnRules(EntityType<? extends WaterAnimal> type, net.minecraft.world.level.ServerLevelAccessor level, net.minecraft.world.entity.MobSpawnType spawnType, net.minecraft.core.BlockPos pos, net.minecraft.util.RandomSource random) {
        if (spawnType == net.minecraft.world.entity.MobSpawnType.NATURAL || spawnType == net.minecraft.world.entity.MobSpawnType.CHUNK_GENERATION) {
            if (level.getBiome(pos).is(ModTags.SHARK_SPAWN_BLACKLIST)) {
                return false;
            }
        }
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(type, level, spawnType, pos, random);
    }

    private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(GREAT_WHITE_SHARK.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ApexWaters::checkSharkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private static void onModifyCustomSpawners(ModifyCustomSpawnersEvent event) {
        event.addCustomSpawner(new SharkSpawner());
    }

    private static void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(GREAT_WHITE_SHARK_SPAWN_EGG);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(RAW_SHARK_MEAT);
            event.accept(COOKED_SHARK_MEAT);
        }
    }
}