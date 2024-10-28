package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.LlamaBlazeModel;
import cn.nulladev.hybridcreatures.entity.LlamaBlaze;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LlamaBlazeRenderer extends MobRenderer<LlamaBlaze, LlamaBlazeModel<LlamaBlaze>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HybridCreatures.MODID, "textures/entity/llama_blaze.png");

    public LlamaBlazeRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new LlamaBlazeModel<>(ctx.bakeLayer(LlamaBlazeModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LlamaBlaze entity) {
        return TEXTURE;
    }
}
