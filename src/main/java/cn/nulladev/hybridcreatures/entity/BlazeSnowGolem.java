package cn.nulladev.hybridcreatures.entity;

import cn.nulladev.hybridcreatures.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class BlazeSnowGolem extends AbstractGolem implements Shearable, RangedAttackMob, net.minecraftforge.common.IForgeShearable {

    private static final EntityDataAccessor<Byte> DATA_HEAD_ID = SynchedEntityData.defineId(BlazeSnowGolem.class, EntityDataSerializers.BYTE);

    public BlazeSnowGolem(EntityType<BlazeSnowGolem> type, Level level) {
        super(type, level);
    }

    public BlazeSnowGolem(Level level, double x, double y, double z) {
        this(EntityInit.BLAZE_SNOW_GOLEM.get(), level);
        setPos(x, y, z);
    }

    public BlazeSnowGolem(Level level, BlockPos position) {
        this(level, position.getX(), position.getY(), position.getZ());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, e -> e instanceof Enemy));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public boolean hasHead() {
        return (this.entityData.get(DATA_HEAD_ID) & 16) != 0;
    }

    public void setHead(boolean p_29937_) {
        byte b0 = this.entityData.get(DATA_HEAD_ID);
        if (p_29937_) {
            this.entityData.set(DATA_HEAD_ID, (byte)(b0 | 16));
        } else {
            this.entityData.set(DATA_HEAD_ID, (byte)(b0 & -17));
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HEAD_ID, (byte)16);
    }

    public void addAdditionalSaveData(CompoundTag p_29923_) {
        super.addAdditionalSaveData(p_29923_);
        p_29923_.putBoolean("Head", this.hasHead());
    }

    public void readAdditionalSaveData(CompoundTag p_29915_) {
        super.readAdditionalSaveData(p_29915_);
        if (p_29915_.contains("Head")) {
            this.setHead(p_29915_.getBoolean("Head"));
        }

    }

    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_29929_) {
        return SoundEvents.BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this)) {
                return;
            }

            BlockState blockstate = Blocks.FIRE.defaultBlockState();

            for(int i = 0; i < 4; ++i) {
                int j = Mth.floor(this.getX() + (double)((float)(i % 2 * 2 - 1) * 0.25F));
                int k = Mth.floor(this.getY());
                int l = Mth.floor(this.getZ() + (double)((float)(i / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(j, k, l);
                if (this.level().isEmptyBlock(blockpos) && blockstate.canSurvive(this.level(), blockpos)) {
                    this.level().setBlockAndUpdate(blockpos, blockstate);
                    this.level().gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this, blockstate));
                }
            }
        }
        if (this.level().isClientSide) {
            if (this.random.nextInt(24) == 0 && !this.isSilent()) {
                this.level().playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            }

            for(int i = 0; i < 2; ++i) {
                this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity livingentity, float p_33318_) {
        double d0 = this.distanceToSqr(livingentity);
        double d1 = livingentity.getX() - this.getX();
        double d2 = livingentity.getY(0.5D) - this.getY(0.5D);
        double d3 = livingentity.getZ() - this.getZ();
        double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
        SmallFireball smallfireball = new SmallFireball(this.level(), this, this.getRandom().triangle(d1, 2.297D * d4), d2, this.getRandom().triangle(d3, 2.297D * d4));
        smallfireball.setPos(smallfireball.getX(), this.getY(0.5D) + 0.5D, smallfireball.getZ());
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(smallfireball);
    }

    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, (double)(0.75F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    @Override
    protected float getStandingEyeHeight(Pose p_29917_, EntityDimensions p_29918_) {
        return 1.7F;
    }

    @Override
    public void shear(SoundSource source) {
        this.level().playSound((Player)null, this, SoundEvents.SNOW_GOLEM_SHEAR, source, 1.0F, 1.0F);
        if (!this.level().isClientSide()) {
            this.setHead(false);
            this.spawnAtLocation(new ItemStack(Items.CARVED_PUMPKIN), 1.7F);
        }
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && this.hasHead();
    }

    @Override
    public boolean isShearable(ItemStack item, Level world, BlockPos pos) {
        return readyForShearing();
    }

    @Override
    public java.util.List<ItemStack> onSheared(Player player, ItemStack item, Level world, BlockPos pos, int fortune) {
        world.playSound(null, this, SoundEvents.SNOW_GOLEM_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        this.gameEvent(GameEvent.SHEAR, player);
        if (!world.isClientSide()) {
            setHead(false);
            return java.util.Collections.singletonList(new ItemStack(Items.SHROOMLIGHT));
        }
        return java.util.Collections.emptyList();
    }
}
