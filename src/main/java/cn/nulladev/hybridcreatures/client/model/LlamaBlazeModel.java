package cn.nulladev.hybridcreatures.client.model;

import cn.nulladev.hybridcreatures.entity.LlamaBlaze;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class LlamaBlazeModel<T extends LlamaBlaze> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "llama_blaze"), "main");
    private final ModelPart head;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;

    public LlamaBlazeModel(ModelPart root) {
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(17, 0).addBox(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(17, 0).addBox(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 3.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(29, 29).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(5.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(-2.0F, 0.0F, -13.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(5.0F, 0.0F, -13.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 10.0F, 6.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(29, 29).addBox(-19.0F, -24.0F, -13.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(8.0F, -24.0F, -13.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(-19.0F, -25.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(7.0F, -25.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 10.0F, 6.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(29, 29).addBox(-6.0F, -16.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(9.0F, -16.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(-6.0F, -16.0F, 9.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 29).addBox(9.0F, -16.0F, 9.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 10.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(LlamaBlaze entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
