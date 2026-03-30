package com.acorsicanfrog.apexwaters.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;

public class SharkSwimNodeEvaluator extends SwimNodeEvaluator {

    private final int pathfindingWidth;

    public SharkSwimNodeEvaluator(boolean allowBreaching, int pathfindingWidth) {
        super(allowBreaching);
        this.pathfindingWidth = pathfindingWidth;
    }

    @Override
    public void prepare(PathNavigationRegion level, Mob mob) {
        super.prepare(level, mob);
        this.entityWidth = this.pathfindingWidth;
        this.entityDepth = this.pathfindingWidth;
    }
}