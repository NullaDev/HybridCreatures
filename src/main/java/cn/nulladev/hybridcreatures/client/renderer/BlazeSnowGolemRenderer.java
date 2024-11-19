package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.BlazeSnowGolemModel;
import cn.nulladev.hybridcreatures.entity.BlazeSnowGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlazeSnowGolemRenderer extends MobRenderer<BlazeSnowGolem, BlazeSnowGolemModel<BlazeSnowGolem>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HybridCreatures.MODID, "textures/entity/blaze_snow_golem.png");

    public BlazeSnowGolemRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new BlazeSnowGolemModel<>(ctx.bakeLayer(BlazeSnowGolemModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new BlazeSnowGolemHeadLayer(this, ctx.getBlockRenderDispatcher(), ctx.getItemRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(BlazeSnowGolem entity) {
        return TEXTURE;
    }

}
