package com.theXu.terracraft0323.item.terraSword.iceSword;

import com.theXu.terracraft0323.NeoMafishMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class ice_sword_waveRender extends EntityRenderer<AbstractHurtingProjectile> {

    protected  ResourceLocation TEXTURE_LOCATION= ResourceLocation.fromNamespaceAndPath(NeoMafishMod.MODID, "textures/entity/wave/ice_sword_wave.png");

    public ice_sword_waveRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    public void render(@NotNull AbstractHurtingProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(getScale(), getScale(), getScale());
        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = pPoseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(getRenderType());
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1, 0, 0);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(AbstractHurtingProjectile pEntity) {
        return TEXTURE_LOCATION;
    }

    protected  RenderType getRenderType(){
        return RenderType.entityCutoutNoCull(getTextureLocation(null));
    }

    public float getScale(){
        return 1f;
    }

    protected static void vertex(
            VertexConsumer pConsumer, Matrix4f pPose, Matrix3f pNormal, int pLightmapUV, float pX, int pY, int pU, int pV
    ) {
        pConsumer.addVertex(pPose, pX - 0.5F, (float)pY - 0.25F, 0.0F)
                .setColor(255, 255, 255, 255)
                .setUv((float)pU, (float)pV)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(pLightmapUV)
                .setNormal(0.0F, 1.0F, 0.0F);
    }

}