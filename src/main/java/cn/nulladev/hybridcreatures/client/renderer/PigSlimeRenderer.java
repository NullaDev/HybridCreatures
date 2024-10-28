package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.PigSlimeModel;
import cn.nulladev.hybridcreatures.entity.PigSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PigSlimeRenderer extends MobRenderer<PigSlime, PigSlimeModel<PigSlime>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HybridCreatures.MODID, "textures/entity/pig_slime.png");

    public PigSlimeRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PigSlimeModel<>(ctx.bakeLayer(PigSlimeModel.LAYER_LOCATION)), 1.0f);
        this.addLayer(new PigSlimeOuterLayer<>(this, ctx.getModelSet()));
    }

    @Override
    public void render(PigSlime p_115976_, float p_115977_, float p_115978_, PoseStack p_115979_, MultiBufferSource p_115980_, int p_115981_) {
        this.shadowRadius = 0.25F * (float)p_115976_.getSize();
        super.render(p_115976_, p_115977_, p_115978_, p_115979_, p_115980_, p_115981_);
    }

    @Override
    protected void scale(PigSlime p_115983_, PoseStack p_115984_, float p_115985_) {
        float f = 0.999F;
        p_115984_.scale(0.999F, 0.999F, 0.999F);
        p_115984_.translate(0.0F, 0.001F, 0.0F);
        float f1 = (float)p_115983_.getSize();
        float f2 = Mth.lerp(p_115985_, p_115983_.oSquish, p_115983_.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        p_115984_.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(PigSlime entity) {
        return TEXTURE;
    }
}