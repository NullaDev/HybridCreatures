package cn.nulladev.hybridcreatures.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.level.Level;

public class LlamaBlazeSpit extends LlamaSpit {
    public LlamaBlazeSpit(EntityType<? extends LlamaSpit> type, Level level) {
        super(type, level);
    }

    public LlamaBlazeSpit(Level level, LlamaBlaze llamaBlaze) {
        this(EntityType.LLAMA_SPIT, level);
        this.setOwner(llamaBlaze);
        this.setPos(
                llamaBlaze.getX() - (double)(llamaBlaze.getBbWidth() + 1.0F) * 0.5D * (double) Mth.sin(llamaBlaze.yBodyRot * ((float)Math.PI / 180F)),
                llamaBlaze.getEyeY() - (double)0.1F,
                llamaBlaze.getZ() + (double)(llamaBlaze.getBbWidth() + 1.0F) * 0.5D * (double)Mth.cos(llamaBlaze.yBodyRot * ((float)Math.PI / 180F))
        );
    }
}
