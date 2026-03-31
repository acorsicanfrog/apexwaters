package com.acorsicanfrog.apexwaters;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {
    public static final TagKey<Biome> SHARK_SPAWN_BLACKLIST = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "shark_spawn_blacklist"));
}