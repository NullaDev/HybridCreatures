package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.SnowShulkerModel;
import cn.nulladev.hybridcreatures.entity.SnowShulker;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class SnowShulkerRenderer extends MobRenderer<SnowShulker, SnowShulkerModel<SnowShulker>> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(HybridCreatures.MODID, "textures/entity/snow_shulker.png");

    public SnowShulkerRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SnowShulkerModel<>(ctx.bakeLayer(SnowShulkerModel.LAYER_LOCATION)), 0f);
        this.addLayer(new SnowShulkerHeadLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SnowShulker entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(SnowShulker p_115907_, PoseStack p_115908_, float p_115909_, float p_115910_, float p_115911_) {
        super.setupRotations(p_115907_, p_115908_, p_115909_, p_115910_ + 180.0F, p_115911_);
        p_115908_.translate(0.0D, 0.5D, 0.0D);
        p_115908_.mulPose(Direction.UP.getRotation());
        p_115908_.translate(0.0D, -0.5D, 0.0D);
    }
}

