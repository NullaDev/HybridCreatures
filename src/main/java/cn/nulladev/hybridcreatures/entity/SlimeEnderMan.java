package cn.nulladev.hybridcreatures.entity;

import cn.nulladev.hybridcreatures.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class SlimeEnderMan extends Monster {

    public SlimeEnderMan(EntityType<? extends SlimeEnderMan> type, Level level) {
        super(type, level);
    }

    public SlimeEnderMan(Level level, double x, double y, double z) {
        this(EntityInit.SLIME_ENDER_MAN.get(), level);
        setPos(x, y, z);
    }

    public SlimeEnderMan(Level level, BlockPos position) {
        this(level, position.getX(), position.getY(), position.getZ());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_32517_, EntityDimensions p_32518_) {
        return 2.55F;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33631_) {
        return SoundEvents.SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SLIME_DEATH;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_32497_, int p_32498_, boolean p_32499_) {
        super.dropCustomDeathLoot(p_32497_, p_32498_, p_32499_);
        ItemStack itemstack = new ItemStack(Blocks.SLIME_BLOCK);
        this.spawnAtLocation(itemstack);
    }

}
