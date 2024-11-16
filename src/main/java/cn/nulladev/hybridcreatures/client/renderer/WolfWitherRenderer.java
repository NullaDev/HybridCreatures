package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.WolfWitherModel;
import cn.nulladev.hybridcreatures.entity.WolfWither;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WolfWitherRenderer extends MobRenderer<WolfWither, WolfWitherModel<WolfWither>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HybridCreatures.MODID, "textures/entity/wolf_wither.png");

    public WolfWitherRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new WolfWitherModel<>(ctx.bakeLayer(WolfWitherModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    protected int getBlockLightLevel(WolfWither p_116443_, BlockPos p_116444_) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(WolfWither entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(WolfWither p_116439_, PoseStack p_116440_, float p_116441_) {
        float f = 2.0F;
        int i = p_116439_.getInvulnerableTicks();
        if (i > 0) {
            f -= ((float)i - p_116441_) / 220.0F * 0.5F;
        }

        p_116440_.scale(f, f, f);
    }
}

