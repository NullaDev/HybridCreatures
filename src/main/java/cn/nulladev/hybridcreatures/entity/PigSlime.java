package cn.nulladev.hybridcreatures.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class PigSlime extends Slime {
    public PigSlime(EntityType<? extends PigSlime> type, Level level) {
        super(type, level);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33631_) {
        return SoundEvents.PIG_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PIG_DEATH;
    }

}
