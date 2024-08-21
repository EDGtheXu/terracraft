package com.theXu.terracraft0323.item.terraSword.waveHandler;

import com.theXu.terracraft0323.item.terraSword.tailaren.tai_la_ren_wave;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;


@OnlyIn(Dist.CLIENT)
public class emptyRender extends EntityRenderer<AbstractHurtingProjectile> {
    protected ResourceLocation TEXTURE_LOCATION;
    protected RenderType RENDER_TYPE;

    public emptyRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    public void render(@NotNull tai_la_ren_wave pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractHurtingProjectile pEntity) {
        return TEXTURE_LOCATION;
    }

}

