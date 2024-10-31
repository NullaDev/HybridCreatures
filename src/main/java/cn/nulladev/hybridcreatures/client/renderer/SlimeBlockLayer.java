package cn.nulladev.hybridcreatures.client.renderer;

import cn.nulladev.hybridcreatures.client.model.SlimeEnderManModel;
import cn.nulladev.hybridcreatures.entity.SlimeEnderMan;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SlimeBlockLayer extends RenderLayer<SlimeEnderMan, SlimeEnderManModel<SlimeEnderMan>> {
    private final BlockRenderDispatcher blockRenderer;

    public SlimeBlockLayer(RenderLayerParent<SlimeEnderMan, SlimeEnderManModel<SlimeEnderMan>> p_234814_, BlockRenderDispatcher p_234815_) {
        super(p_234814_);
        this.blockRenderer = p_234815_;
    }

    public void render(PoseStack poseStack, MultiBufferSource p_116640_, int p_116641_, SlimeEnderMan p_116642_, float p_116643_, float p_116644_, float p_116645_, float p_116646_, float p_116647_, float p_116648_) {
        BlockState blockstate = Blocks.SLIME_BLOCK.defaultBlockState();
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.6875F, -0.75F);
        poseStack.mulPose(Axis.XP.rotationDegrees(20.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
        poseStack.translate(0.25F, 0.1875F, 0.25F);
        poseStack.scale(-0.5F, -0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        this.blockRenderer.renderSingleBlock(blockstate, poseStack, p_116640_, p_116641_, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
