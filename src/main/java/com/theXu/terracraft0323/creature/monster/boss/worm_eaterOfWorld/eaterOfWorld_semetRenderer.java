package com.theXu.terracraft0323.creature.monster.boss.worm_eaterOfWorld;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.theXu.terracraft0323.creature.monster.boss.geoNormalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class eaterOfWorld_semetRenderer extends GeoEntityRenderer<eaterOfWorld_segment> {
    public eaterOfWorld_semetRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new geoNormalModel<>("worm_eater_of_world_segment"));
    }

    @Override
    public void preRender(PoseStack poseStack, eaterOfWorld_segment animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        poseStack.scale(3.5f,3.5f,3.5f);
        poseStack.translate(0,0,0);

        poseStack.mulPose(Axis.YN.rotationDegrees(-90));
        //poseStack.mulPose(Axis.XN.rotationDegrees(animatable.xRotO));
        poseStack.mulPose(Axis.XN.rotationDegrees(-(float) (Math.cos(Math.toRadians(animatable.yRotO+90)) * animatable.xRotO)));
        poseStack.mulPose(Axis.ZN.rotationDegrees(-(float) (Math.sin(Math.toRadians(animatable.yRotO+90)) * animatable.xRotO)));

    }



/*
    @Override
    public @Nullable RenderType getRenderType(kulouwang animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.energySwirl(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"texture/entity/creeper.png"), (float) Math.sin( partialTick),(float) Math.sin( partialTick));
    }
    */
}