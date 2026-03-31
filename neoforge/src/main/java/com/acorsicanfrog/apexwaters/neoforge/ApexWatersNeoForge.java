package com.acorsicanfrog.apexwaters.neoforge;

import com.acorsicanfrog.apexwaters.ApexWatersCommon;
import com.acorsicanfrog.apexwaters.config.ApexWatersConfig;
import com.acorsicanfrog.apexwaters.entity.GreatWhiteSharkEntity;
import com.acorsicanfrog.apexwaters.entity.SharkSpawner;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.ModifyCustomSpawnersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(ApexWatersCommon.MOD_ID)
public class ApexWatersNeoForge {

    // Entity Types
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, ApexWatersCommon.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<GreatWhiteSharkEntity>> GREAT_WHITE_SHARK =
            ENTITY_TYPES.register("great_white_shark", () -> EntityType.Builder.of(GreatWhiteSharkEntity::new, MobCategory.WATER_CREATURE)
                    .sized(GreatWhiteSharkEntity.HITBOX_WIDTH, GreatWhiteSharkEntity.HITBOX_HEIGHT)
                    .clientTrackingRange(10)
                    .build(ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "great_white_shark").toString()));

    // Items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ApexWatersCommon.MOD_ID);

    public static final DeferredItem<Item> GREAT_WHITE_SHARK_SPAWN_EGG = ITEMS.register("great_white_shark_spawn_egg",
            () -> new DeferredSpawnEggItem(GREAT_WHITE_SHARK, 0x6B7B8D, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> RAW_SHARK_MEAT = ITEMS.registerSimpleItem("great_white_shark_raw",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()));

    public static final DeferredItem<Item> COOKED_SHARK_MEAT = ITEMS.registerSimpleItem("great_white_shark_cooked",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationModifier(0.8F)
                    .build()));

    public ApexWatersNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        // Initialize config
        ApexWatersConfig.set(new NeoForgeConfig());
        modContainer.registerConfig(ModConfig.Type.COMMON, NeoForgeConfig.SPEC);

        // Populate common suppliers
        ApexWatersCommon.init(GREAT_WHITE_SHARK::get, GREAT_WHITE_SHARK_SPAWN_EGG::get, RAW_SHARK_MEAT::get, COOKED_SHARK_MEAT::get);

        // Mod event bus listeners
        modEventBus.addListener(ApexWatersNeoForge::registerAttributes);
        modEventBus.addListener(ApexWatersNeoForge::registerSpawnPlacements);
        modEventBus.addListener(ApexWatersNeoForge::buildCreativeTabContents);

        // Game event bus listeners
        NeoForge.EVENT_BUS.addListener(ApexWatersNeoForge::onModifyCustomSpawners);
        NeoForge.EVENT_BUS.addListener(ApexWatersNeoForge::onLivingDeath);

        // Register deferred registries
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GREAT_WHITE_SHARK.get(), GreatWhiteSharkEntity.createAttributes().build());
    }

    private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(GREAT_WHITE_SHARK.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ApexWatersCommon::checkSharkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private static void onModifyCustomSpawners(ModifyCustomSpawnersEvent event) {
        event.addCustomSpawner(new SharkSpawner());
    }

    private static void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.insertAfter(new ItemStack(Items.FROG_SPAWN_EGG), new ItemStack(GREAT_WHITE_SHARK_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(new ItemStack(Items.COOKED_SALMON), new ItemStack(COOKED_SHARK_MEAT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.COOKED_SALMON), new ItemStack(RAW_SHARK_MEAT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private static void onLivingDeath(LivingDeathEvent event) {
        ApexWatersCommon.onLivingDeath(event.getEntity(), event.getSource());
    }
}
