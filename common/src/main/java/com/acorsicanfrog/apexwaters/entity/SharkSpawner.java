package com.acorsicanfrog.apexwaters.entity;

import com.acorsicanfrog.apexwaters.ApexWatersCommon;
import com.acorsicanfrog.apexwaters.config.ApexWatersConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;

public class SharkSpawner implements CustomSpawner {
    private int tickCounter;

    @Override
    public int tick(ServerLevel level, boolean spawnEnemies, boolean spawnFriendlies) {
        if (!spawnFriendlies || !level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
            return 0;
        }

        if (++this.tickCounter < ApexWatersConfig.get().spawnInterval()) {
            return 0;
        }

        this.tickCounter = 0;

        int spawned = 0;

        for (Player player : level.players()) {
            if (player.isSpectator()) continue;

            BlockPos playerPos = player.blockPosition();
            if (level.random.nextInt(ApexWatersConfig.get().spawnChance()) != 0) continue;

            int x = playerPos.getX() + level.random.nextIntBetweenInclusive(-48, 48);
            int z = playerPos.getZ() + level.random.nextIntBetweenInclusive(-48, 48);
            int y = level.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z);

            BlockPos.MutableBlockPos spawnPos = new BlockPos.MutableBlockPos(x, y, z);

            for (int attempt = 0; attempt < 10; attempt++) {
                int testY = y + level.random.nextInt(Math.max(1, level.getSeaLevel() - y));
                spawnPos.set(x, testY, z);
                if (level.getFluidState(spawnPos).is(Fluids.WATER)) break;
            }

            if (!level.getBiome(spawnPos).is(BiomeTags.IS_OCEAN)) continue;
            if (!level.getFluidState(spawnPos).is(Fluids.WATER)) continue;
            if (!ApexWatersCommon.checkSharkSpawnRules(ApexWatersCommon.SHARK_ENTITY_TYPE.get(), level, MobSpawnType.NATURAL, spawnPos, level.random)) continue;

            double minDist = ApexWatersConfig.get().spawnMinDistance();
            AABB searchBox = new AABB(spawnPos).inflate(minDist);

            if (!level.getEntitiesOfClass(GreatWhiteSharkEntity.class, searchBox).isEmpty()) continue;

            GreatWhiteSharkEntity shark = ApexWatersCommon.SHARK_ENTITY_TYPE.get().create(level);

            if (shark == null) continue;

            shark.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, level.random.nextFloat() * 360.0F, 0.0F);

            shark.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos), MobSpawnType.NATURAL, null);

            level.addFreshEntityWithPassengers(shark);

            spawned++;
        }
        return spawned;
    }
}