package cn.nulladev.hybridcreatures.entity;

import cn.nulladev.hybridcreatures.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class LlamaBlaze extends Monster {
    public LlamaBlaze(EntityType<? extends LlamaBlaze> type, Level level) {
        super(type, level);
    }

    public LlamaBlaze(Level level, double x, double y, double z) {
        this(EntityInit.LLAMA_BLAZE.get(), level);
        setPos(x, y, z);
    }

    public LlamaBlaze(Level level, BlockPos position) {
        this(level, position.getX(), position.getY(), position.getZ());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LlamaBlazeAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    static class LlamaBlazeAttackGoal extends Goal {
        private final LlamaBlaze llamaBlaze;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public LlamaBlazeAttackGoal(LlamaBlaze p_32247_) {
            this.llamaBlaze = p_32247_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.llamaBlaze.getTarget();
            return livingentity != null && livingentity.isAlive() && this.llamaBlaze.canAttack(livingentity);
        }

        public void start() {
            this.attackStep = 0;
        }

        public void stop() {
//            this.llamaBlaze.setCharged(false);
            this.lastSeen = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            --this.attackTime;
            LivingEntity livingentity = this.llamaBlaze.getTarget();
            if (livingentity != null) {
                boolean flag = this.llamaBlaze.getSensing().hasLineOfSight(livingentity);
                if (flag) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double d0 = this.llamaBlaze.distanceToSqr(livingentity);
                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.llamaBlaze.doHurtTarget(livingentity);
                    }

                    this.llamaBlaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getX() - this.llamaBlaze.getX();
                    double d2 = livingentity.getY(0.5D) - this.llamaBlaze.getY(0.5D);
                    double d3 = livingentity.getZ() - this.llamaBlaze.getZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
//                            this.llamaBlaze.setCharged(true);
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
//                            this.llamaBlaze.setCharged(false);
                        }

                        if (this.attackStep > 1) {
                            double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
                            if (!this.llamaBlaze.isSilent()) {
                                this.llamaBlaze.level().levelEvent((Player)null, 1018, this.llamaBlaze.blockPosition(), 0);
                            }

                            for(int i = 0; i < 1; ++i) {
//                                LlamaBlazeSpit llamaBlazeSpit = new LlamaBlazeSpit(this.llamaBlaze.level(), this.llamaBlaze);
                                SmallFireball smallfireball = new SmallFireball(this.llamaBlaze.level(), this.llamaBlaze, this.llamaBlaze.getRandom().triangle(d1, 2.297D * d4), d2, this.llamaBlaze.getRandom().triangle(d3, 2.297D * d4));
                                smallfireball.setPos(smallfireball.getX(), this.llamaBlaze.getY(0.5D) + 0.5D, smallfireball.getZ());
                                this.llamaBlaze.level().addFreshEntity(smallfireball);
                            }
                        }
                    }

                    this.llamaBlaze.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.llamaBlaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.llamaBlaze.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
}
