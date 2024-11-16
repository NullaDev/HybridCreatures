package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.DogHeadModel;
import cn.nulladev.hybridcreatures.entity.DogHead;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DogHeadRenderer extends EntityRenderer<DogHead> {

    private static final ResourceLocation DOG_HEAD_BLUE_LOCATION = new ResourceLocation(HybridCreatures.MODID, "textures/entity/dog_head_blue.png");
    private static final ResourceLocation DOG_HEAD_LOCATION = new ResourceLocation(HybridCreatures.MODID, "textures/entity/dog_head.png");
    private final DogHeadModel model;

    public DogHeadRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.model = new DogHeadModel(ctx.bakeLayer(DogHeadModel.LAYER_LOCATION));
    }

    protected int getBlockLightLevel(DogHead p_116491_, BlockPos p_116492_) {
        return 15;
    }

    public void render(DogHead p_116484_, float p_116485_, float p_116486_, PoseStack p_116487_, MultiBufferSource p_116488_, int p_116489_) {
        p_116487_.pushPose();
        p_116487_.scale(-1.0F, -1.0F, 1.0F);
        float f = Mth.rotLerp(p_116486_, p_116484_.yRotO, p_116484_.getYRot());
        float f1 = Mth.lerp(p_116486_, p_116484_.xRotO, p_116484_.getXRot());
        VertexConsumer vertexconsumer = p_116488_.getBuffer(this.model.renderType(this.getTextureLocation(p_116484_)));
        this.model.setupAnim(0.0F, f, f1);
        this.model.renderToBuffer(p_116487_, vertexconsumer, p_116489_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        p_116487_.popPose();
        super.render(p_116484_, p_116485_, p_116486_, p_116487_, p_116488_, p_116489_);
    }

    public ResourceLocation getTextureLocation(DogHead p_116482_) {
        return p_116482_.isDangerous() ? DOG_HEAD_BLUE_LOCATION : DOG_HEAD_LOCATION;
    }
}
