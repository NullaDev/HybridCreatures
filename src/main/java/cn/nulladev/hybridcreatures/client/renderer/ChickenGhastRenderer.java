package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.ChickenGhastModel;
import cn.nulladev.hybridcreatures.entity.ChickenGhast;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChickenGhastRenderer extends MobRenderer<ChickenGhast, ChickenGhastModel<ChickenGhast>> {
        private static final ResourceLocation TEXTURE =
                new ResourceLocation(HybridCreatures.MODID, "textures/entity/chicken_ghast.png");

    public ChickenGhastRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ChickenGhastModel<>(ctx.bakeLayer(ChickenGhastModel.LAYER_LOCATION)), 1.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenGhast entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(ChickenGhast p_114757_, PoseStack p_114758_, float p_114759_) {
        p_114758_.scale(4.5F, 4.5F, 4.5F);
    }
}
