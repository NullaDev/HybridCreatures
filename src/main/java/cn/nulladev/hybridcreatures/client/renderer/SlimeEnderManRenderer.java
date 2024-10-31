package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.SlimeEnderManModel;
import cn.nulladev.hybridcreatures.entity.SlimeEnderMan;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SlimeEnderManRenderer extends MobRenderer<SlimeEnderMan, SlimeEnderManModel<SlimeEnderMan>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HybridCreatures.MODID, "textures/entity/slime_ender_man.png");

    public SlimeEnderManRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SlimeEnderManModel<>(ctx.bakeLayer(SlimeEnderManModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new SlimeEnderManOuterLayer<>(this, ctx.getModelSet()));
        this.addLayer(new SlimeBlockLayer(this, ctx.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(SlimeEnderMan entity) {
        return TEXTURE;
    }
}