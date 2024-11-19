package cn.nulladev.hybridcreatures.client.model;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.entity.BlazeSnowGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BlazeSnowGolemModel<T extends BlazeSnowGolem> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HybridCreatures.MODID, "blaze_snow_golem"), "main");
    private final ModelPart head;
    private final ModelPart left_hand;
    private final ModelPart left_hand_rotation;
    private final ModelPart right_hand;
    private final ModelPart right_hand_flip;
    private final ModelPart right_hand_rotation;
    private final ModelPart body;
    private final ModelPart body_bottom;

    public BlazeSnowGolemModel(ModelPart root) {
        this.head = root.getChild("head");
        this.left_hand = root.getChild("left_hand");
        this.left_hand_rotation = this.left_hand.getChild("left_hand_rotation");
        this.right_hand = root.getChild("right_hand");
        this.right_hand_flip = this.right_hand.getChild("right_hand_flip");
        this.right_hand_rotation = this.right_hand_flip.getChild("right_hand_rotation");
        this.body = root.getChild("body");
        this.body_bottom = root.getChild("body_bottom");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition left_hand = partdefinition.addOrReplaceChild("left_hand", CubeListBuilder.create(), PartPose.offset(4.5F, 5.25F, 0.0F));

        PartDefinition left_hand_rotation = left_hand.addOrReplaceChild("left_hand_rotation", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 1.0F, 0.0F, 0.0F, 0.0F, -0.0524F));

        PartDefinition cube_r1 = left_hand_rotation.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(45, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.55F, 0.0F, 0.0F, 0.0F, -0.6981F));

        PartDefinition right_hand = partdefinition.addOrReplaceChild("right_hand", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.5F, 5.25F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition right_hand_flip = right_hand.addOrReplaceChild("right_hand_flip", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 1.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition right_hand_rotation = right_hand_flip.addOrReplaceChild("right_hand_rotation", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9948F));

        PartDefinition cube_r2 = right_hand_rotation.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(45, 0).addBox(-8.4118F, -4.8677F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3F, -8.25F, 0.0F, 0.0F, 0.0F, -1.6144F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition body_bottom = partdefinition.addOrReplaceChild("body_bottom", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(BlazeSnowGolem entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.body.yRot = netHeadYaw * ((float)Math.PI / 180F) * 0.25F;
        float f = Mth.sin(this.body.yRot);
        float f1 = Mth.cos(this.body.yRot);
        this.left_hand.yRot = this.body.yRot;
        this.right_hand.yRot = -this.body.yRot;// + (float)Math.PI;
        this.left_hand.x = f1 * 5.0F;
        this.left_hand.z = -f * 5.0F;
        this.right_hand.x = -f1 * 5.0F;
        this.right_hand.z = f * 5.0F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body_bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public ModelPart getHead() {
        return this.head;
    }

}
