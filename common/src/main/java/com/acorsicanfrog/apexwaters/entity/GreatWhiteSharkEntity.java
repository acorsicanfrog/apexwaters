package com.acorsicanfrog.apexwaters.entity;

import com.acorsicanfrog.apexwaters.ApexWatersCommon;
import com.acorsicanfrog.apexwaters.config.ApexWatersConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GreatWhiteSharkEntity extends WaterAnimal {

    public static final float HITBOX_WIDTH = 7.0F;
    public static final float HITBOX_HEIGHT = 1.6F;

    public static final int BITE_DURATION_TICKS = 10;

    private static final double IDLE_SWIM_SPEED = 1.0D;
    private static final double TARGET_SWIM_SPEED_MULTIPLIER = 1.0D;

    private static final int PATH_LOOKAHEAD = 8;

    private static final int IDLE_SWIM_RADIUS = 64;
    private static final double IDLE_SWIM_MIN_DISTANCE = 48.0D;

    private static final int IDLE_SWIM_VERTICAL_RANGE = 6;
    private static final int IDLE_REPATH_INTERVAL = 30;

    private static final float IDLE_TURN_CONE_DEGREES = 70.0F;
    private static final float MAX_YAW_CHANGE_PER_TICK = 4.0F;
    private static final float MAX_PITCH_CHANGE_PER_TICK = 2.0F;
    private static final float FULL_THRUST_ALIGNMENT_ANGLE = 35.0F;
    private static final float MIN_THRUST_ALIGNMENT_ANGLE = 115.0F;

    private static final double SURFACE_DIVE_FORCE = 0.045D;
    private static final double NEAR_SURFACE_DIVE_FORCE = 0.02D;
    private static final double SURFACE_BUFFER = 0.75D;
    private static final double MODEL_UNIT_TO_BLOCKS = 1.0D / 16.0D;
    private static final double TAIL_SWAY_MODEL_UNITS = 12.0D;
    private static final double SWIM_ANIMATION_CYCLE_TICKS = 40.0D;
    private static final double CHASE_PARTICLE_ANIMATION_SPEED_MULTIPLIER = 2.5D;

    private static final Vec3 RIGHT_PECTORAL_FIN_TIP = new Vec3(-36.0D, -19.0D, 11.0D);
    private static final Vec3 LEFT_PECTORAL_FIN_TIP = new Vec3(36.0D, -19.0D, 11.0D);
    private static final Vec3 TOP_TAIL_FIN_TIP = new Vec3(0.0D, 27.0D, -80.0D);
    private static final Vec3 BOTTOM_TAIL_FIN_TIP = new Vec3(0.0D, -20.0D, -75.0D);

    private static final EntityDataAccessor<Integer> DATA_HUNGER = SynchedEntityData.defineId(GreatWhiteSharkEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_CHASING = SynchedEntityData.defineId(GreatWhiteSharkEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_LEGENDARY = SynchedEntityData.defineId(GreatWhiteSharkEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_LAST_ATTACK_ANIMATION_TICK = SynchedEntityData.defineId(GreatWhiteSharkEntity.class, EntityDataSerializers.INT);

    private static final double CULLING_INFLATION_HORIZONTAL = 1.25D;
    private static final double CULLING_INFLATION_VERTICAL = 0.75D;

    private static final ResourceLocation CONFIG_HEALTH_ID = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "config_health");
    private static final ResourceLocation CONFIG_SPEED_ID = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "config_speed");
    private static final ResourceLocation CONFIG_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "config_damage");
    private static final ResourceLocation CONFIG_SCALE_ID = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "config_scale");
    private static final ResourceLocation LEGENDARY_HEALTH_ID = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "legendary_health");
    private static final ResourceLocation LEGENDARY_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "legendary_damage");

    private int hungerTickCounter = 0;

    public GreatWhiteSharkEntity(EntityType<? extends GreatWhiteSharkEntity> type, Level level) {
        super(type, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 3, 0.4F, 0.5F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 3);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.SCALE, 1.0);
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData) {
        this.applyConfigAttributes();
        this.applyRandomScale();
        this.setHealth(this.getMaxHealth());

        if (ApexWatersConfig.get().randomStartHunger()) {
            this.setHunger(this.getRandom().nextInt(ApexWatersConfig.get().hungerMax() + 1));
        }

        return spawnData;
    }

    private void applyConfigAttributes() {
        applyModifier(Attributes.MAX_HEALTH, CONFIG_HEALTH_ID, ApexWatersConfig.get().maxHealth() - 30.0, AttributeModifier.Operation.ADD_VALUE);
        applyModifier(Attributes.MOVEMENT_SPEED, CONFIG_SPEED_ID, ApexWatersConfig.get().movementSpeed() - 0.3, AttributeModifier.Operation.ADD_VALUE);
        applyModifier(Attributes.ATTACK_DAMAGE, CONFIG_DAMAGE_ID, ApexWatersConfig.get().attackDamage() - 8.0, AttributeModifier.Operation.ADD_VALUE);
    }

    private void applyRandomScale() {
        double min = ApexWatersConfig.get().scaleMin();
        double max = ApexWatersConfig.get().scaleMax();
        int scaleStep = this.random.nextInt(101);

        boolean isLegendary = scaleStep == 100;

        double scale = Mth.lerp(scaleStep / 100.0D, min, max);

        applyModifier(Attributes.SCALE, CONFIG_SCALE_ID, scale - 1.0, AttributeModifier.Operation.ADD_VALUE);

        this.setLegendaryShark(isLegendary);
        this.setLegendaryAttributes(isLegendary);
    }

    private void setLegendaryAttributes(boolean isLegendary) {
        this.removeModifier(Attributes.MAX_HEALTH, LEGENDARY_HEALTH_ID);
        this.removeModifier(Attributes.ATTACK_DAMAGE, LEGENDARY_DAMAGE_ID);

        if (!isLegendary) {
            return;
        }

        applyModifier(Attributes.MAX_HEALTH, LEGENDARY_HEALTH_ID, 1.0D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        applyModifier(Attributes.ATTACK_DAMAGE, LEGENDARY_DAMAGE_ID, 1.0D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    private void applyModifier(net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        AttributeInstance instance = this.getAttribute(attribute);
        if (instance != null) {
            instance.removeModifier(id);
            instance.addPermanentModifier(new AttributeModifier(id, amount, operation));
        }
    }

    private void removeModifier(net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, ResourceLocation id) {
        AttributeInstance instance = this.getAttribute(attribute);
        if (instance != null) {
            instance.removeModifier(id);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_HUNGER, ApexWatersConfig.get().hungerMax());
        builder.define(DATA_CHASING, false);
        builder.define(DATA_LEGENDARY, false);
        builder.define(DATA_LAST_ATTACK_ANIMATION_TICK, -BITE_DURATION_TICKS);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, TARGET_SWIM_SPEED_MULTIPLIER, true));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, this::shouldTarget));
    }

    private boolean shouldTarget(LivingEntity target) {
        if (!this.isReachableAttackTarget(target)) {
            return false;
        }

        boolean targetInBoat = isPlayerInTargetableBoat(target);

        if (ApexWatersConfig.get().alwaysAggressive()) {
            return targetInBoat || target.isInWater();
        }

        if (ApexWatersConfig.get().attackHurtPlayers()
                && target instanceof Player
                && target.getHealth() < target.getMaxHealth()
                && (targetInBoat || target.isInWater())) {
            return true;
        }

        return getHunger() <= 0 && (targetInBoat || target.isInWater());
    }

    private boolean canRetaliateAgainst(LivingEntity attacker) {
        if (attacker instanceof Player player && (player.isCreative() || player.isSpectator())) {
            return false;
        }
        return this.isReachableAttackTarget(attacker);
    }

    private boolean isReachableAttackTarget(LivingEntity target) {
        if (target instanceof GreatWhiteSharkEntity) {
            return false;
        }

        boolean targetInBoat = isPlayerInTargetableBoat(target);
        if (target instanceof Player
                && target.getVehicle() instanceof Boat
                && !targetInBoat) {
            return false;
        }

        return targetInBoat || target.isInWater();
    }

    private boolean isPlayerInTargetableBoat(LivingEntity target) {
        return target instanceof Player
                && target.getVehicle() instanceof Boat
                && ApexWatersConfig.get().targetPlayersInBoats();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new SharkPathNavigation(this, level);
    }

    @Override
    protected AABB makeBoundingBox() {
        return super.makeBoundingBox().move(0.0D, 0.95D, 0.0D);
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return this.getBoundingBox().inflate(CULLING_INFLATION_HORIZONTAL, CULLING_INFLATION_VERTICAL, CULLING_INFLATION_HORIZONTAL);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean hurt = super.hurt(source, amount);

        if (hurt && !this.level().isClientSide && this.getTarget() == null) {
            LivingEntity attacker = this.resolveAttacker(source);
            if (attacker != null && attacker.isAlive() && this.canRetaliateAgainst(attacker)) {
                this.setTarget(attacker);
                this.setChasing(true);
            }
        }

        return hurt;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double size = this.getBoundingBoxForCulling().getSize();
        if (Double.isNaN(size)) {
            size = 1.0D;
        }

        size = size * 64.0D;
        return distance < size * size;
    }

    @Override
    public void tick() {
        super.tick();

        this.yBodyRot = this.getYRot();
        this.yHeadRot = this.getYRot();

        if (!this.level().isClientSide) {
            this.setChasing(false);

            if (this.isInWater() && this.getTarget() == null && this.getNavigation().isDone() && this.tickCount % IDLE_REPATH_INTERVAL == 0) {
                Vec3 idleTarget = this.findIdleSwimTarget();
                if (idleTarget != null) {
                    this.getNavigation().moveTo(idleTarget.x, idleTarget.y, idleTarget.z, IDLE_SWIM_SPEED);
                }
            }

            Path path = this.getNavigation().getPath();
            if (path != null && !this.getNavigation().isDone()) {
                int lookaheadIndex = Math.min(path.getNextNodeIndex() + PATH_LOOKAHEAD, path.getNodeCount() - 1);
                Vec3 lookaheadPos = Vec3.atBottomCenterOf(path.getNodePos(lookaheadIndex));
                this.getMoveControl().setWantedPosition(lookaheadPos.x, lookaheadPos.y, lookaheadPos.z, IDLE_SWIM_SPEED);
            }
        }

        if (!this.level().isClientSide && this.isInWater() && this.getTarget() != null && this.getTarget().isAlive()) {
            this.setChasing(true);
            LivingEntity target = this.getTarget();
            double dx = target.getX() - this.getX();
            double dy = target.getEyeY() - this.getEyeY();
            double dz = target.getZ() - this.getZ();
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (dist > 1.0) {
                float targetYaw = (float) (Mth.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;
                float targetPitch = (float) (-(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180.0 / Math.PI)));
                float yawDelta = Math.abs(this.turnTowards(targetYaw, targetPitch));

                double speed = this.getAttributeValue(Attributes.MOVEMENT_SPEED) * TARGET_SWIM_SPEED_MULTIPLIER;
                double thrustScale = this.getThrustScaleForTurn(yawDelta);
                Vec3 currentMotion = this.getDeltaMovement();
                Vec3 thrust = Vec3.directionFromRotation(this.getXRot(), this.getYRot()).scale(speed * thrustScale);
                this.setDeltaMovement(
                        currentMotion.x * 0.82 + thrust.x * 0.18,
                        currentMotion.y * 0.82 + thrust.y * 0.18,
                        currentMotion.z * 0.82 + thrust.z * 0.18
                );
            }
        }

        if (this.level().isClientSide && this.isInWater() && this.isChasing()) {
            this.spawnFinTrailParticles();
        }

        if (this.isInWater()) {
            Vec3 motion = this.getDeltaMovement();
            double diveForce = 0.0D;

            if (!this.isUnderWater()) {
                diveForce = SURFACE_DIVE_FORCE;
            }

            BlockPos topOfBody = BlockPos.containing(this.getX(), this.getBoundingBox().maxY + 0.1D, this.getZ());
            if (!this.level().getFluidState(topOfBody).is(FluidTags.WATER)) {
                diveForce = Math.max(diveForce, SURFACE_DIVE_FORCE);
            }

            BlockPos nearTopOfBody = BlockPos.containing(this.getX(), this.getBoundingBox().maxY - SURFACE_BUFFER, this.getZ());
            if (!this.level().getFluidState(nearTopOfBody).is(FluidTags.WATER)) {
                diveForce = Math.max(diveForce, NEAR_SURFACE_DIVE_FORCE);
            }

            if (diveForce > 0.0D) {
                this.setDeltaMovement(motion.x, Math.min(motion.y, 0.0D) - diveForce, motion.z);
            }
        }

        if (!this.level().isClientSide) {
            hungerTickCounter++;
            if (hungerTickCounter >= 20) {
                hungerTickCounter = 0;
                int currentHunger = getHunger();
                int drainRate = ApexWatersConfig.get().hungerDrainRate();
                setHunger(Math.max(0, currentHunger - drainRate));
            }

            // // --- DEBUG CODE: VISUALIZE TARGET AND PATH ---
            // if (this.level() instanceof ServerLevel serverLevel && this.tickCount % 5 == 0) {
            //     Path path = this.getNavigation().getPath();
            //     if (path != null) {
            //         for (int i = path.getNextNodeIndex(); i < path.getNodeCount(); i++) {
            //             BlockPos nodePos = path.getNode(i).asBlockPos();
            //             serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, nodePos.getX() + 0.5, nodePos.getY() + 0.5, nodePos.getZ() + 0.5, 1, 0.0, 0.0, 0.0, 0.0);
            //         }
            //         if (path.getNodeCount() > 0) {
            //             BlockPos endPos = path.getNode(path.getNodeCount() - 1).asBlockPos();
            //             serverLevel.sendParticles(ParticleTypes.HEART, endPos.getX() + 0.5, endPos.getY() + 0.8, endPos.getZ() + 0.5, 2, 0.1, 0.1, 0.1, 0.0);
            //         }
            //     }

            //     if (this.getTarget() != null) {
            //         serverLevel.sendParticles(ParticleTypes.FLAME, this.getTarget().getX(), this.getTarget().getY() + this.getTarget().getBbHeight() + 0.5, this.getTarget().getZ(), 5, 0.1, 0.1, 0.1, 0.01);
            //     }
            // }
            // // -----------------------------------------------
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean hurt = super.doHurtTarget(target);
        if (hurt && target instanceof LivingEntity livingTarget) {
            this.triggerBite();

            if (livingTarget.isDeadOrDying()) {
                resetHunger();
            }
        }
        return hurt;
    }

    public int getHunger() {
        return this.entityData.get(DATA_HUNGER);
    }

    public void setHunger(int hunger) {
        this.entityData.set(DATA_HUNGER, hunger);
    }

    public void resetHunger() {
        setHunger(ApexWatersConfig.get().hungerMax());
    }

    public boolean isChasing() {
        return this.entityData.get(DATA_CHASING);
    }

    public boolean isLegendaryShark() {
        return this.entityData.get(DATA_LEGENDARY);
    }

    public boolean isBiting() {
        return this.tickCount - this.getLastBiteTick() < BITE_DURATION_TICKS;
    }

    public float getBiteProgress(float ageInTicks) {
        float elapsed = ageInTicks - this.getLastBiteTick();
        return Mth.clamp(elapsed / BITE_DURATION_TICKS, 0.0F, 1.0F);
    }

    private int getLastBiteTick() {
        return this.entityData.get(DATA_LAST_ATTACK_ANIMATION_TICK);
    }

    private void triggerBite() {
        this.entityData.set(DATA_LAST_ATTACK_ANIMATION_TICK, this.tickCount);
    }

    private LivingEntity resolveAttacker(DamageSource source) {
        Entity attackerEntity = source.getEntity();
        if (attackerEntity instanceof LivingEntity livingAttacker) {
            return livingAttacker;
        }

        Entity directEntity = source.getDirectEntity();
        if (directEntity instanceof Projectile projectile && projectile.getOwner() instanceof LivingEntity projectileOwner) {
            return projectileOwner;
        }

        return null;
    }

    private void setChasing(boolean chasing) {
        this.entityData.set(DATA_CHASING, chasing);
    }

    private void setLegendaryShark(boolean legendary) {
        this.entityData.set(DATA_LEGENDARY, legendary);
    }

    private float turnTowards(float targetYaw, float targetPitch) {
        float yawDelta = Mth.wrapDegrees(targetYaw - this.getYRot());
        float pitchDelta = Mth.wrapDegrees(targetPitch - this.getXRot());
        this.setYRot(this.getYRot() + Mth.clamp(yawDelta, -MAX_YAW_CHANGE_PER_TICK, MAX_YAW_CHANGE_PER_TICK));
        this.setXRot(this.getXRot() + Mth.clamp(pitchDelta, -MAX_PITCH_CHANGE_PER_TICK, MAX_PITCH_CHANGE_PER_TICK));
        return yawDelta;
    }

    private void spawnFinTrailParticles() {
        double tailSway = this.getTailSwayModelOffset();

        this.spawnTrailParticle(this.modelToWorld(RIGHT_PECTORAL_FIN_TIP.x, RIGHT_PECTORAL_FIN_TIP.y, RIGHT_PECTORAL_FIN_TIP.z));
        this.spawnTrailParticle(this.modelToWorld(LEFT_PECTORAL_FIN_TIP.x, LEFT_PECTORAL_FIN_TIP.y, LEFT_PECTORAL_FIN_TIP.z));
        this.spawnTrailParticle(this.modelToWorld(TOP_TAIL_FIN_TIP.x + tailSway, TOP_TAIL_FIN_TIP.y, TOP_TAIL_FIN_TIP.z));
        this.spawnTrailParticle(this.modelToWorld(BOTTOM_TAIL_FIN_TIP.x + tailSway, BOTTOM_TAIL_FIN_TIP.y, BOTTOM_TAIL_FIN_TIP.z));
    }

    private void spawnTrailParticle(Vec3 position) {
        this.level().addParticle(ParticleTypes.DOLPHIN, position.x, position.y, position.z, 0.0D, 0.0D, 0.0D);
    }

    private Vec3 modelToWorld(double modelX, double modelY, double modelZ) {
        double scale = this.getAttributeValue(Attributes.SCALE) * MODEL_UNIT_TO_BLOCKS;

        double lx = -modelX * scale;
        double ly = modelY * scale;
        double lz = modelZ * scale;

        float pitchRad = this.getXRot() * ((float) Math.PI / 180.0F);
        float cp = Mth.cos(pitchRad);
        float sp = Mth.sin(pitchRad);
        double py = ly * cp - lz * sp;
        double pz = ly * sp + lz * cp;

        float yawRad = this.getYRot() * ((float) Math.PI / 180.0F);
        float cy = Mth.cos(yawRad);
        float sy = Mth.sin(yawRad);
        double wx = lx * cy - pz * sy;
        double wz = lx * sy + pz * cy;

        AABB box = this.getBoundingBox();
        return new Vec3(
                (box.minX + box.maxX) / 2.0 + wx,
                (box.minY + box.maxY) / 2.0 + py,
                (box.minZ + box.maxZ) / 2.0 + wz
        );
    }

    private double getTailSwayModelOffset() {
        double animationSpeedMultiplier = this.isBiting() || this.isChasing()
                ? CHASE_PARTICLE_ANIMATION_SPEED_MULTIPLIER
                : 0.5D;
        double phase = this.tickCount * animationSpeedMultiplier * (Math.PI * 2.0D / SWIM_ANIMATION_CYCLE_TICKS);
        return -Mth.sin((float) phase) * TAIL_SWAY_MODEL_UNITS;
    }

    private double getThrustScaleForTurn(float yawDelta) {
        float absoluteYawDelta = Math.abs(yawDelta);
        if (absoluteYawDelta <= FULL_THRUST_ALIGNMENT_ANGLE) {
            return 1.0D;
        }

        if (absoluteYawDelta >= MIN_THRUST_ALIGNMENT_ANGLE) {
            return 0.2D;
        }

        double progress = (absoluteYawDelta - FULL_THRUST_ALIGNMENT_ANGLE) / (MIN_THRUST_ALIGNMENT_ANGLE - FULL_THRUST_ALIGNMENT_ANGLE);
        return 1.0D - progress * 0.8D;
    }

    private Vec3 findIdleSwimTarget() {
        for (int attempt = 0; attempt < 12; attempt++) {
            float headingOffset = (this.random.nextFloat() * 2.0F - 1.0F) * IDLE_TURN_CONE_DEGREES;
            float targetYaw = this.getYRot() + headingOffset;
            double distance = Mth.lerp(this.random.nextDouble(), IDLE_SWIM_MIN_DISTANCE, IDLE_SWIM_RADIUS);
            Vec3 horizontalDirection = Vec3.directionFromRotation(0.0F, targetYaw);
            double targetX = this.getX() + horizontalDirection.x * distance;
            double targetY = this.getY() + (this.random.nextDouble() * 2.0D - 1.0D) * IDLE_SWIM_VERTICAL_RANGE - 2.0D;
            double targetZ = this.getZ() + horizontalDirection.z * distance;

            BlockPos targetPos = BlockPos.containing(targetX, targetY, targetZ);
            if (!this.level().getFluidState(targetPos).is(FluidTags.WATER)) {
                continue;
            }

            if (!this.level().getFluidState(targetPos.above()).is(FluidTags.WATER)) {
                continue;
            }

            return Vec3.atCenterOf(targetPos);
        }

        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("SharkHunger", getHunger());
        compound.putBoolean("LegendaryShark", this.isLegendaryShark());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("SharkHunger")) {
            setHunger(compound.getInt("SharkHunger"));
        }
        if (compound.contains("LegendaryShark")) {
            boolean legendary = compound.getBoolean("LegendaryShark");
            this.setLegendaryShark(legendary);
            this.setLegendaryAttributes(legendary);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.FISH_SWIM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.HOSTILE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.HOSTILE_DEATH;
    }

    @Override
    public int getMaxAirSupply() {
        return 4800;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 160;
    }
}