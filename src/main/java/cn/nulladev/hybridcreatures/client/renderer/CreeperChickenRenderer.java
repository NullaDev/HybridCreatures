package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.CreeperChickenModel;
import cn.nulladev.hybridcreatures.entity.CreeperChicken;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CreeperChickenRenderer extends MobRenderer<CreeperChicken, CreeperChickenModel<CreeperChicken>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HybridCreatures.MODID, "textures/entity/creeper_chicken.png");

    public CreeperChickenRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new CreeperChickenModel<>(ctx.bakeLayer(CreeperChickenModel.LAYER_LOCATION)), 0.3F);
    }

    public ResourceLocation getTextureLocation(CreeperChicken p_113998_) {
        return TEXTURE;
    }

    protected float getBob(CreeperChicken p_114000_, float p_114001_) {
        float f = Mth.lerp(p_114001_, p_114000_.oFlap, p_114000_.flap);
        float f1 = Mth.lerp(p_114001_, p_114000_.oFlapSpeed, p_114000_.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}