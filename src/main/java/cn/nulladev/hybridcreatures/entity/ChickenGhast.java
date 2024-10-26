package cn.nulladev.hybridcreatures.entity;

import cn.nulladev.hybridcreatures.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ChickenGhast extends FlyingMob {

    public ChickenGhast(EntityType<? extends ChickenGhast> type, Level level) {
        super(type, level);
        this.moveControl = new ChickenGhastMoveControl(this);
    }

    public ChickenGhast(Level level, double x, double y, double z) {
        this(EntityInit.CHICKEN_GHAST.get(), level);
        setPos(x, y, z);
    }

    public ChickenGhast(Level level, BlockPos position) {
        this(level, position.getX(), position.getY(), position.getZ());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FOLLOW_RANGE, 100.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(7, new ChickenGhastLookGoal(this));
        this.goalSelector.addGoal(7, new ChickenGhastShootEggGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_289460_) -> {
            return Math.abs(p_289460_.getY() - this.getY()) <= 4.0D;
        }));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_28262_) {
        return SoundEvents.CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 5.0F;
    }

    @Override
    protected float getStandingEyeHeight(Pose p_32741_, EntityDimensions p_32742_) {
        return 2.6F;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    static class ChickenGhastLookGoal extends Goal {
        private final ChickenGhast chickenGhast;

        public ChickenGhastLookGoal(ChickenGhast p_32762_) {
            this.chickenGhast = p_32762_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.chickenGhast.getTarget() == null) {
                Vec3 vec3 = this.chickenGhast.getDeltaMovement();
                this.chickenGhast.setYRot(-((float) Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                this.chickenGhast.yBodyRot = this.chickenGhast.getYRot();
            } else {
                LivingEntity livingentity = this.chickenGhast.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.chickenGhast) < 4096.0D) {
                    double d1 = livingentity.getX() - this.chickenGhast.getX();
                    double d2 = livingentity.getZ() - this.chickenGhast.getZ();
                    this.chickenGhast.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.chickenGhast.yBodyRot = this.chickenGhast.getYRot();
                }
            }

        }
    }

    static class ChickenGhastMoveControl extends MoveControl {
        private final ChickenGhast chickenGhast;
        private int floatDuration;

        public ChickenGhastMoveControl(ChickenGhast p_32768_) {
            super(p_32768_);
            this.chickenGhast = p_32768_;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.chickenGhast.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.chickenGhast.getX(), this.wantedY - this.chickenGhast.getY(), this.wantedZ - this.chickenGhast.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.chickenGhast.setDeltaMovement(this.chickenGhast.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 p_32771_, int p_32772_) {
            AABB aabb = this.chickenGhast.getBoundingBox();

            for(int i = 1; i < p_32772_; ++i) {
                aabb = aabb.move(p_32771_);
                if (!this.chickenGhast.level().noCollision(this.chickenGhast, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class ChickenGhastShootEggGoal extends Goal {
        private final ChickenGhast chickenGhast;
        public int chargeTime;

        public ChickenGhastShootEggGoal(ChickenGhast p_32776_) {
            this.chickenGhast = p_32776_;
        }

        public boolean canUse() {
            return this.chickenGhast.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void stop() {
//            this.chickenGhast.setCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.chickenGhast.getTarget();
            if (livingentity != null) {
                if (livingentity.distanceToSqr(this.chickenGhast) < 4096.0D && this.chickenGhast.hasLineOfSight(livingentity)) {
                    Level level = this.chickenGhast.level();
                    ++this.chargeTime;
                    if (this.chargeTime == 10 && !this.chickenGhast.isSilent()) {
                        level.levelEvent((Player)null, 1015, this.chickenGhast.blockPosition(), 0);
                    }

                    if (this.chargeTime == 20) {
                        Vec3 vec3 = this.chickenGhast.getViewVector(1.0F);
                        double d2 = livingentity.getX() - (this.chickenGhast.getX() + vec3.x * 4.0D);
                        double d3 = livingentity.getY(0.5D) - (0.5D + this.chickenGhast.getY(0.5D));
                        double d4 = livingentity.getZ() - (this.chickenGhast.getZ() + vec3.z * 4.0D);
                        if (!this.chickenGhast.isSilent()) {
                            level.levelEvent(null, 1016, this.chickenGhast.blockPosition(), 0);
                        }

                        ExplosiveEgg egg = new ExplosiveEgg(level, this.chickenGhast, d2, d3, d4);
                        egg.setPos(this.chickenGhast.getX() + vec3.x * 4.0D, this.chickenGhast.getY(0.5D) + 0.5D, this.chickenGhast.getZ() + vec3.z * 4.0D);
                        level.addFreshEntity(egg);

                        this.chargeTime = -40;
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

//                this.chickenGhast.setCharging(this.chargeTime > 10);
            }
        }
    }

    static class RandomFloatAroundGoal extends Goal {
        private final ChickenGhast chickenGhast;

        public RandomFloatAroundGoal(ChickenGhast p_32783_) {
            this.chickenGhast = p_32783_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.chickenGhast.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.chickenGhast.getX();
                double d1 = movecontrol.getWantedY() - this.chickenGhast.getY();
                double d2 = movecontrol.getWantedZ() - this.chickenGhast.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource randomsource = this.chickenGhast.getRandom();
            double d0 = this.chickenGhast.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.chickenGhast.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.chickenGhast.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.chickenGhast.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

}
