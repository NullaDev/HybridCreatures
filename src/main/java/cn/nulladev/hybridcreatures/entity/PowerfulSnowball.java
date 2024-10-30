package cn.nulladev.hybridcreatures.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class PowerfulSnowball extends Snowball {
    public PowerfulSnowball(EntityType<? extends Snowball> type, Level level) {
        super(type, level);
    }

    public PowerfulSnowball(Level level, LivingEntity entity) {
        super(level, entity);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37404_) {
        super.onHitEntity(p_37404_);
        Entity entity = p_37404_.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 2F);
    }
}
