package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.ChickenGhastModel;
import cn.nulladev.hybridcreatures.entity.ChickenGhast;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChickenGhastRenderer extends MobRenderer<ChickenGhast, ChickenGhastModel<ChickenGhast>> {
        private static final ResourceLocation TEXTURE =
                new ResourceLocation(HybridCreatures.MODID, "textures/entity/chicken_ghast.png");

    public ChickenGhastRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ChickenGhastModel<>(ctx.bakeLayer(ChickenGhastModel.LAYER_LOCATION)), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenGhast entity) {
        return TEXTURE;
    }
}
