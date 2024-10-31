package cn.nulladev.hybridcreatures.client.model;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.entity.SlimeEnderMan;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SlimeEnderManModel<T extends SlimeEnderMan> extends HumanoidModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HybridCreatures.MODID, "slime_ender_man"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_OUTER = new ModelLayerLocation(new ResourceLocation(HybridCreatures.MODID, "slime_ender_man"), "outer");
    private final ModelPart root;

    public SlimeEnderManModel(ModelPart root) {
        super(root);
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(-5.0F, -13.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offset(5.0F, -13.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(-2.0F, -6.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offset(2.0F, -6.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, 17.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -39.0F, 0.0F));

        PartDefinition right_eye = head.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(56, 36).addBox(1.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_eye = head.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(56, 40).addBox(-3.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(56, 44).addBox(-1.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createOuterLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition body2 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition body_out_r1 = body2.addOrReplaceChild("body_out_r1", CubeListBuilder.create().texOffs(29, 47).addBox(-7.0F, 2.0F, -1.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, 1.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition right_arm2 = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, -13.0F, 0.0F));

        PartDefinition right_leg_out_r1 = right_arm2.addOrReplaceChild("right_leg_out_r1", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition left_arm2 = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, -13.0F, 0.0F));

        PartDefinition left_leg_out_r1 = left_arm2.addOrReplaceChild("left_leg_out_r1", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition right_leg2 = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, -6.0F, 0.0F));

        PartDefinition right_leg_out_r2 = right_leg2.addOrReplaceChild("right_leg_out_r2", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -13.5F, -1.0F, 2.0F, 27.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.25F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition left_leg2 = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(56, 1).addBox(-1.0F, 2.75F, -1.0F, 2.0F, 27.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 0.0F));

        PartDefinition head2 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(25, 48).addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -39.25F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SlimeEnderMan entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.visible = true;
        this.body.xRot = 0.0F;
        this.body.y = -14.0F;
        this.body.z = -0.0F;
        this.rightLeg.xRot *= 0.5F;
        this.leftLeg.xRot *= 0.5F;

        if (this.rightLeg.xRot > 0.4F) {
            this.rightLeg.xRot = 0.4F;
        }

        if (this.leftLeg.xRot > 0.4F) {
            this.leftLeg.xRot = 0.4F;
        }

        if (this.rightLeg.xRot < -0.4F) {
            this.rightLeg.xRot = -0.4F;
        }

        if (this.leftLeg.xRot < -0.4F) {
            this.leftLeg.xRot = -0.4F;
        }

        this.rightArm.xRot = -0.5F;
        this.leftArm.xRot = -0.5F;
        this.rightArm.zRot = 0.05F;
        this.leftArm.zRot = -0.05F;

        this.rightLeg.z = 0.0F;
        this.leftLeg.z = 0.0F;
        this.rightLeg.y = -5.0F;
        this.leftLeg.y = -5.0F;

        this.rightArm.setPos(-5.0F, -12.0F, 0.0F);
        this.leftArm.setPos(5.0F, -12.0F, 0.0F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
