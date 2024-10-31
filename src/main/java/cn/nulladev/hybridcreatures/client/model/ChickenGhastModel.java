package cn.nulladev.hybridcreatures.client.model;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.entity.ChickenGhast;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ChickenGhastModel<T extends ChickenGhast> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HybridCreatures.MODID, "chicken_ghast"), "main");
    private final ModelPart head;
    private final ModelPart bill;
    private final ModelPart chin;
    private final ModelPart leg;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;
    private final ModelPart leg7;
    private final ModelPart leg8;
    private final ModelPart leg9;

    public ChickenGhastModel(ModelPart root) {
        this.head = root.getChild("head");
        this.bill = root.getChild("bill");
        this.chin = root.getChild("chin");
        this.leg = root.getChild("leg");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.leg5 = root.getChild("leg5");
        this.leg6 = root.getChild("leg6");
        this.leg7 = root.getChild("leg7");
        this.leg8 = root.getChild("leg8");
        this.leg9 = root.getChild("leg9");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -19.0F, 2.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(7.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        PartDefinition bill = partdefinition.addOrReplaceChild("bill", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -15.0F, -9.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(3.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        PartDefinition chin = partdefinition.addOrReplaceChild("chin", CubeListBuilder.create().texOffs(14, 4).addBox(-1.0F, -10.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(3.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        PartDefinition leg = partdefinition.addOrReplaceChild("leg", CubeListBuilder.create().texOffs(26, 0).addBox(-0.9F, 10.5F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-0.9F, 5.5F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-0.9F, 0.5F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 8.0F, 6.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(26, 0).addBox(-1.9F, 5.5F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.9F, 0.5F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 8.0F, -2.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, 11.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.0F, 6.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.0F, 1.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.9F, 7.5F, -2.0F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(26, 0).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-2.0F, 5.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 8.5F, -2.0F));

        PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(26, 0).addBox(-2.1F, 5.3F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-2.1F, 0.3F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.8F, 8.2F, 2.0F));

        PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(26, 0).addBox(-1.5F, 6.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.5F, 1.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.4F, 7.5F, 2.0F));

        PartDefinition leg7 = partdefinition.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(26, 0).addBox(-1.6F, 6.3F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.6F, 1.3F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.6F, 7.2F, 2.0F));

        PartDefinition leg8 = partdefinition.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(26, 0).addBox(-1.4F, 10.9F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.5F, 5.9F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.5F, 0.9F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 7.6F, 6.0F));

        PartDefinition leg9 = partdefinition.addOrReplaceChild("leg9", CubeListBuilder.create().texOffs(26, 0).addBox(-1.4F, 7.8F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-1.4F, 2.8F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 5.7F, 6.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(ChickenGhast entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leg.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 0) + 0.4F;
        this.leg2.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 1) + 0.4F;
        this.leg3.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 2) + 0.4F;
        this.leg4.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 3) + 0.4F;
        this.leg5.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 4) + 0.4F;
        this.leg6.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 5) + 0.4F;
        this.leg7.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 6) + 0.4F;
        this.leg8.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 7) + 0.4F;
        this.leg9.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 8) + 0.4F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bill.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        chin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg8.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg9.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
