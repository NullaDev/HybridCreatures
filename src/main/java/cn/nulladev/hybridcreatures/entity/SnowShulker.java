package cn.nulladev.hybridcreatures.entity;

import cn.nulladev.hybridcreatures.init.EntityInit;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class SnowShulker extends Monster {
    protected static final EntityDataAccessor<Byte> DATA_PEEK_ID = SynchedEntityData.defineId(SnowShulker.class, EntityDataSerializers.BYTE);
    private static final UUID COVERED_ARMOR_MODIFIER_UUID = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
    private static final AttributeModifier COVERED_ARMOR_MODIFIER = new AttributeModifier(COVERED_ARMOR_MODIFIER_UUID, "Covered armor bonus", 2.0D, AttributeModifier.Operation.ADDITION);
    private float currentPeekAmountO;
    private float currentPeekAmount;

    public SnowShulker(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.lookControl = new SnowShulkerLookControl(this);
    }

    public SnowShulker(Level level, double x, double y, double z) {
        this(EntityInit.SNOW_SHULKER.get(), level);
        setPos(x, y, z);
    }

    public SnowShulker(Level level, BlockPos position) {
        this(level, position.getX(), position.getY(), position.getZ());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F, 0.02F, true));
        this.goalSelector.addGoal(4, new SnowShulkerAttackGoal());
        this.goalSelector.addGoal(7, new SnowShulkerPeekGoal());
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, this.getClass())).setAlertOthers());
        this.targetSelector.addGoal(2, new SnowShulkerNearestAttackGoal(this));
        this.targetSelector.addGoal(3, new SnowShulkerDefenseAttackGoal(this));
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SNOW_GOLEM_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_29929_) {
        return SoundEvents.SNOW_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PEEK_ID, (byte)0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_PEEK_ID, tag.getByte("Peek"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putByte("Peek", this.entityData.get(DATA_PEEK_ID));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D);
    }

    @Override
    protected BodyRotationControl createBodyControl() {
        return new SnowShulkerBodyRotationControl(this);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.updatePeekAmount()) {
            this.onPeekAmountChange();
        }
    }

    private int getRawPeekAmount() {
        return this.entityData.get(DATA_PEEK_ID);
    }

    private boolean isClosed() {
        return this.getRawPeekAmount() == 0;
    }

    private static float getPhysicalPeek(float p_149769_) {
        return 0.5F - Mth.sin((0.5F + p_149769_) * (float)Math.PI) * 0.5F;
    }

    private boolean updatePeekAmount() {
        this.currentPeekAmountO = this.currentPeekAmount;
        float f = (float)this.getRawPeekAmount() * 0.01F;
        if (this.currentPeekAmount == f) {
            return false;
        } else {
            if (this.currentPeekAmount > f) {
                this.currentPeekAmount = Mth.clamp(this.currentPeekAmount - 0.05F, f, 1.0F);
            } else {
                this.currentPeekAmount = Mth.clamp(this.currentPeekAmount + 0.05F, 0.0F, f);
            }

            return true;
        }
    }

    private void onPeekAmountChange() {
        this.reapplyPosition();
        float f = getPhysicalPeek(this.currentPeekAmount);
        float f1 = getPhysicalPeek(this.currentPeekAmountO);
        Direction direction = Direction.DOWN;
        float f2 = f - f1;
        if (!(f2 <= 0.0F)) {
            for(Entity entity : this.level().getEntities(this, getProgressDeltaAabb(direction, f1, f).move(this.getX() - 0.5D, this.getY(), this.getZ() - 0.5D), EntitySelector.NO_SPECTATORS.and((p_149771_) -> {
                return !p_149771_.isPassengerOfSameVehicle(this);
            }))) {
                if (!(entity instanceof SnowShulker) && !entity.noPhysics) {
                    entity.move(MoverType.SHULKER, new Vec3((double)(f2 * (float)direction.getStepX()), (double)(f2 * (float)direction.getStepY()), (double)(f2 * (float)direction.getStepZ())));
                }
            }

        }
    }

    public static AABB getProgressAabb(Direction p_149791_, float p_149792_) {
        return getProgressDeltaAabb(p_149791_, -1.0F, p_149792_);
    }

    public static AABB getProgressDeltaAabb(Direction p_149794_, float p_149795_, float p_149796_) {
        double d0 = (double)Math.max(p_149795_, p_149796_);
        double d1 = (double)Math.min(p_149795_, p_149796_);
        return (new AABB(BlockPos.ZERO)).expandTowards((double)p_149794_.getStepX() * d0, (double)p_149794_.getStepY() * d0, (double)p_149794_.getStepZ() * d0).contract((double)(-p_149794_.getStepX()) * (1.0D + d1), (double)(-p_149794_.getStepY()) * (1.0D + d1), (double)(-p_149794_.getStepZ()) * (1.0D + d1));
    }

    @Override
    protected AABB makeBoundingBox() {
        float f = getPhysicalPeek(this.currentPeekAmount);
        Direction direction = Direction.DOWN;
        float f1 = this.getType().getWidth() / 2.0F;
        return getProgressAabb(direction, f).move(this.getX() - (double)f1, this.getY(), this.getZ() - (double)f1);
    }

    @Override
    public double getMyRidingOffset() {
        EntityType<?> entitytype = this.getVehicle().getType();
        return !(this.getVehicle() instanceof Boat) && entitytype != EntityType.MINECART ? super.getMyRidingOffset() : 0.1875D - this.getVehicle().getPassengersRidingOffset();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_149780_, DifficultyInstance p_149781_, MobSpawnType p_149782_, @Nullable SpawnGroupData p_149783_, @Nullable CompoundTag p_149784_) {
        this.setYRot(0.0F);
        this.yHeadRot = this.getYRot();
        this.setOldPosAndRot();
        return super.finalizeSpawn(p_149780_, p_149781_, p_149782_, p_149783_, p_149784_);
    }

    @Override
    public Vec3 getDeltaMovement() {
        return Vec3.ZERO;
    }

    @Override
    public void setDeltaMovement(Vec3 p_149804_) {}

    @Override
    public void setPos(double p_33449_, double p_33450_, double p_33451_) {
        BlockPos blockpos = this.blockPosition();
        if (this.isPassenger()) {
            super.setPos(p_33449_, p_33450_, p_33451_);
        } else {
            super.setPos((double)Mth.floor(p_33449_) + 0.5D, (double)Mth.floor(p_33450_ + 0.5D), (double)Mth.floor(p_33451_) + 0.5D);
        }
        if (this.tickCount != 0) {
            BlockPos blockpos1 = this.blockPosition();
            if (!blockpos1.equals(blockpos)) {
                this.entityData.set(DATA_PEEK_ID, (byte)0);
                this.hasImpulse = true;
            }
        }
    }

    @Override
    public void lerpTo(double p_33411_, double p_33412_, double p_33413_, float p_33414_, float p_33415_, int p_33416_, boolean p_33417_) {
        this.lerpSteps = 0;
        this.setPos(p_33411_, p_33412_, p_33413_);
        this.setRot(p_33414_, p_33415_);
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.isAlive();
    }

    void setRawPeekAmount(int p_33419_) {
        if (!this.level().isClientSide) {
            this.getAttribute(Attributes.ARMOR).removeModifier(COVERED_ARMOR_MODIFIER);
            if (p_33419_ == 0) {
                this.getAttribute(Attributes.ARMOR).addPermanentModifier(COVERED_ARMOR_MODIFIER);
                this.playSound(SoundEvents.SHULKER_CLOSE, 1.0F, 1.0F);
                this.gameEvent(GameEvent.CONTAINER_CLOSE);
            } else {
                this.playSound(SoundEvents.SHULKER_OPEN, 1.0F, 1.0F);
                this.gameEvent(GameEvent.CONTAINER_OPEN);
            }
        }

        this.entityData.set(DATA_PEEK_ID, (byte)p_33419_);
    }

    public float getClientPeekAmount(float p_33481_) {
        return Mth.lerp(p_33481_, this.currentPeekAmountO, this.currentPeekAmount);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_33438_, EntityDimensions p_33439_) {
        return 0.5F;
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket p_219067_) {
        super.recreateFromPacket(p_219067_);
        this.yBodyRot = 0.0F;
        this.yBodyRotO = 0.0F;
    }

    @Override
    public int getMaxHeadXRot() {
        return 180;
    }

    @Override
    public int getMaxHeadYRot() {
        return 180;
    }

    @Override
    public void push(Entity p_33474_) {
    }

    @Override
    public float getPickRadius() {
        return 0.0F;
    }

    public void performRangedAttack(LivingEntity le, float p_29913_) {
        PowerfulSnowball snowball = new PowerfulSnowball(this.level(), this);
        double d0 = le.getEyeY() - (double)1.1F;
        double d1 = le.getX() - this.getX();
        double d2 = d0 - snowball.getY();
        double d3 = le.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        snowball.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(snowball);
    }

    static class SnowShulkerBodyRotationControl extends BodyRotationControl {
        public SnowShulkerBodyRotationControl(Mob mob) {
            super(mob);
        }

        public void clientTick() {
        }
    }

    class SnowShulkerAttackGoal extends Goal {
        private int attackTime;

        public SnowShulkerAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = SnowShulker.this.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                return SnowShulker.this.level().getDifficulty() != Difficulty.PEACEFUL;
            } else {
                return false;
            }
        }

        public void start() {
            this.attackTime = 20;
            SnowShulker.this.setRawPeekAmount(100);
        }

        public void stop() {
            SnowShulker.this.setRawPeekAmount(0);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (SnowShulker.this.level().getDifficulty() != Difficulty.PEACEFUL) {
                --this.attackTime;
                LivingEntity livingentity = SnowShulker.this.getTarget();
                if (livingentity != null) {
                    SnowShulker.this.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);
                    double d0 = SnowShulker.this.distanceToSqr(livingentity);
                    if (d0 < this.getFollowDistance() * this.getFollowDistance()) {
                        if (this.attackTime <= 0) {
                            this.attackTime = 20;
                            PowerfulSnowball snowball = new PowerfulSnowball(SnowShulker.this.level(), SnowShulker.this);
                            double d1 = livingentity.getX() - SnowShulker.this.getX();
                            double d2 = livingentity.getEyeY() - (double)1.1F - snowball.getY();
                            double d3 = livingentity.getZ() - SnowShulker.this.getZ();
                            double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
                            snowball.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
                            SnowShulker.this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (SnowShulker.this.getRandom().nextFloat() * 0.4F + 0.8F));
                            SnowShulker.this.level().addFreshEntity(snowball);
                        }
                    } else {
                        SnowShulker.this.setTarget((LivingEntity)null);
                    }
                    super.tick();
                }
            }
        }

        private double getFollowDistance() {
            return SnowShulker.this.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }

    static class SnowShulkerDefenseAttackGoal extends NearestAttackableTargetGoal<LivingEntity> {
        public SnowShulkerDefenseAttackGoal(SnowShulker snowShulker) {
            super(snowShulker, LivingEntity.class, 10, true, false, (p_33501_) -> {
                return p_33501_ instanceof Enemy;
            });
        }

        public boolean canUse() {
            return this.mob.getTeam() == null ? false : super.canUse();
        }

        protected AABB getTargetSearchArea(double p_33499_) {
            Direction direction = Direction.DOWN;
            if (direction.getAxis() == Direction.Axis.X) {
                return this.mob.getBoundingBox().inflate(4.0D, p_33499_, p_33499_);
            } else {
                return direction.getAxis() == Direction.Axis.Z ? this.mob.getBoundingBox().inflate(p_33499_, p_33499_, 4.0D) : this.mob.getBoundingBox().inflate(p_33499_, 4.0D, p_33499_);
            }
        }
    }

    class SnowShulkerLookControl extends LookControl {
        public SnowShulkerLookControl(Mob mob) {
            super(mob);
        }

        protected void clampHeadRotationToBody() {
        }

        protected Optional<Float> getYRotD() {
            Direction direction = Direction.DOWN;
            Vector3f vector3f = direction.getRotation().transform(new Vector3f(Util.make(() -> {
                Vec3i vec3i = Direction.SOUTH.getNormal();
                return new Vector3f((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
            })));
            Vec3i vec3i = direction.getNormal();
            Vector3f vector3f1 = new Vector3f((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
            vector3f1.cross(vector3f);
            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getEyeY();
            double d2 = this.wantedZ - this.mob.getZ();
            Vector3f vector3f2 = new Vector3f((float)d0, (float)d1, (float)d2);
            float f = vector3f1.dot(vector3f2);
            float f1 = vector3f.dot(vector3f2);
            return !(Math.abs(f) > 1.0E-5F) && !(Math.abs(f1) > 1.0E-5F) ? Optional.empty() : Optional.of((float)(Mth.atan2((double)(-f), (double)f1) * (double)(180F / (float)Math.PI)));
        }

        protected Optional<Float> getXRotD() {
            return Optional.of(0.0F);
        }
    }

    class SnowShulkerNearestAttackGoal extends NearestAttackableTargetGoal<Player> {
        public SnowShulkerNearestAttackGoal(SnowShulker p_33505_) {
            super(p_33505_, Player.class, true);
        }

        public boolean canUse() {
            return SnowShulker.this.level().getDifficulty() == Difficulty.PEACEFUL ? false : super.canUse();
        }

        protected AABB getTargetSearchArea(double p_33508_) {
            Direction direction = Direction.DOWN;
            if (direction.getAxis() == Direction.Axis.X) {
                return this.mob.getBoundingBox().inflate(4.0D, p_33508_, p_33508_);
            } else {
                return direction.getAxis() == Direction.Axis.Z ? this.mob.getBoundingBox().inflate(p_33508_, p_33508_, 4.0D) : this.mob.getBoundingBox().inflate(p_33508_, 4.0D, p_33508_);
            }
        }
    }

    class SnowShulkerPeekGoal extends Goal {
        private int peekTime;

        public boolean canUse() {
            return SnowShulker.this.getTarget() == null && SnowShulker.this.random.nextInt(reducedTickDelay(40)) == 0;
        }

        public boolean canContinueToUse() {
            return SnowShulker.this.getTarget() == null && this.peekTime > 0;
        }

        public void start() {
            this.peekTime = this.adjustedTickDelay(20 * (1 + SnowShulker.this.random.nextInt(3)));
            SnowShulker.this.setRawPeekAmount(30);
        }

        public void stop() {
            if (SnowShulker.this.getTarget() == null) {
                SnowShulker.this.setRawPeekAmount(0);
            }

        }

        public void tick() {
            --this.peekTime;
        }
    }

}
