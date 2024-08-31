package com.theXu.terracraft0323.item.terraSword.tailaren;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.theXu.terracraft0323.NeoMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class tai_la_ren_waveModel<T extends tai_la_ren_wave> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "tai_la_ren_wave_model"), "main1");
    private final ModelPart body;

    public tai_la_ren_waveModel(ModelPart root) {
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(-1, 0).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, 2.0F, -5.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, 4.0F, -1.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, -8.0F, 0.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, -10.0F, 0.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, 6.0F, 0.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, 8.0F, 0.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, 4.0F, -4.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, -6.0F, -4.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, -8.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        body.xRot = pLimbSwing;
        body.yRot = pNetHeadYaw;
        body.zRot = pHeadPitch;
    }


}