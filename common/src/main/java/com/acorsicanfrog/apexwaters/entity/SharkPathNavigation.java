package com.acorsicanfrog.apexwaters.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.pathfinder.PathFinder;

public class SharkPathNavigation extends WaterBoundPathNavigation {

    public static final int PATHFINDING_WIDTH = 4;

    public SharkPathNavigation(Mob mob, Level level) {
        super(mob, level);
    }

    @Override
    protected PathFinder createPathFinder(int maxVisitedNodes) {
        this.nodeEvaluator = new SharkSwimNodeEvaluator(false, PATHFINDING_WIDTH);
        this.nodeEvaluator.setCanPassDoors(false);
        return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
    }
}