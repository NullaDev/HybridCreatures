package cn.nulladev.hybridcreatures.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ExplosiveEgg extends ThrownEgg {
    public double xPower;
    public double yPower;
    public double zPower;

    public ExplosiveEgg(EntityType<? extends ExplosiveEgg> type, Level level) {
        super(type, level);
    }

    public ExplosiveEgg(Level level, LivingEntity entity, double vx, double vy, double vz) {
        super(level, entity);
        this.moveTo(vx, vy, vz, this.getYRot(), this.getXRot());
        this.reapplyPosition();
        double d0 = Math.sqrt(vx * vx + vy * vy + vz * vz);
        if (d0 != 0.0D) {
            this.xPower = vx / d0 * 0.1D;
            this.yPower = vy / d0 * 0.1D;
            this.zPower = vz / d0 * 0.1D;
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void onHit(HitResult p_37218_) {
        super.onHit(p_37218_);
        if (!this.level().isClientSide) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1F, flag, Level.ExplosionInteraction.MOB);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37216_) {
        super.onHitEntity(p_37216_);
        if (!this.level().isClientSide) {
            Entity entity = p_37216_.getEntity();
            Entity entity1 = this.getOwner();
            entity.hurt(this.damageSources().thrown(this, entity1), 2.0F);
            if (entity1 instanceof LivingEntity) {
                this.doEnchantDamageEffects((LivingEntity)entity1, entity);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.add(this.xPower, this.yPower, this.zPower));
    }
}
