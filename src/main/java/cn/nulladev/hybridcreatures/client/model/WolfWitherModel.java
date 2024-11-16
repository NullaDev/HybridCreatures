package cn.nulladev.hybridcreatures.client.model;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.entity.WolfWither;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class WolfWitherModel <T extends WolfWither> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HybridCreatures.MODID, "wolf_wither"), "main");
    private final ModelPart head;
    private final ModelPart bone;
    private final ModelPart body;
    private final ModelPart head2;
    private final ModelPart head3;
    private final ModelPart tail;

    public WolfWitherModel(ModelPart root) {
        this.head = root.getChild("head");
        this.bone = root.getChild("bone");
        this.body = root.getChild("body");
        this.head2 = root.getChild("head2");
        this.head3 = root.getChild("head3");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(1.0F))
                .texOffs(16, 14).addBox(2.0F, -6.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 14).addBox(-2.0F, -6.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-0.5F, 0.98F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.0F, -12.0F, 8.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.5F, 8.0F, 8.0F));

        PartDefinition leg4_r1 = bone.addOrReplaceChild("leg4_r1", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-1.5F, 1.7F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition leg4_r2 = bone.addOrReplaceChild("leg4_r2", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-7.5F, 1.7F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition leg4_r3 = bone.addOrReplaceChild("leg4_r3", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-1.5F, -3.6F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition leg4_r4 = bone.addOrReplaceChild("leg4_r4", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-7.5F, -3.6F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition leg4_r5 = bone.addOrReplaceChild("leg4_r5", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.01F)), PartPose.offsetAndRotation(-1.5F, -15.9F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition leg4_r6 = bone.addOrReplaceChild("leg4_r6", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-7.5F, -15.9F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition leg4_r7 = bone.addOrReplaceChild("leg4_r7", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-1.5F, -9.0F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition leg4_r8 = bone.addOrReplaceChild("leg4_r8", CubeListBuilder.create().texOffs(0, 18).addBox(0.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-7.5F, -9.0F, 4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(9, 18).addBox(-1.0F, -14.0F, -3.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.01F))
                .texOffs(9, 18).addBox(-1.0F, -17.4F, -3.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.02F))
                .texOffs(9, 18).addBox(-1.0F, -4.0F, -3.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 12.0F, 10.0F));

        PartDefinition head2 = partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(1.01F))
                .texOffs(16, 14).addBox(1.0F, -8.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 14).addBox(-3.0F, -8.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-1.5F, -1.02F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(11.0F, -6.0F, 8.0F));

        PartDefinition head3 = partdefinition.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(1.01F))
                .texOffs(16, 14).addBox(2.0F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 14).addBox(-2.0F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-0.5F, -0.02F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(-12.0F, -7.0F, 8.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(9, 18).addBox(-1.0F, 0.9541F, -1.2356F, 2.0F, 8.0F, 2.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 17.0F, 8.2F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(WolfWither entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = Mth.cos(ageInTicks * 0.1F);
        this.bone.xRot = (0.065F + 0.05F * f) * (float)Math.PI;
        this.body.xRot = (0.065F + 0.05F * f) * (float)Math.PI;
//        this.tail.setPos(-2.0F, 6.9F + Mth.cos(this.body.xRot) * 10.0F, -0.5F + Mth.sin(this.bone.xRot) * 10.0F);
        this.tail.xRot = (0.265F + 0.1F * f) * (float)Math.PI;
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void prepareMobModel(WolfWither entity, float p_104096_, float p_104097_, float p_104098_) {
        setupHeadRotation(entity, this.head2, 0);
        setupHeadRotation(entity, this.head3, 1);
    }

    private static <T extends WolfWither> void setupHeadRotation(T p_171072_, ModelPart p_171073_, int p_171074_) {
        p_171073_.yRot = (p_171072_.getHeadYRot(p_171074_) - p_171072_.yBodyRot) * ((float)Math.PI / 180F);
        p_171073_.xRot = p_171072_.getHeadXRot(p_171074_) * ((float)Math.PI / 180F);
    }

}
