package com.theXu.terracraft0323.geo.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.theXu.terracraft0323.geo.entity.ReplacedPlayerEntity;
import com.theXu.terracraft0323.geo.model.ReplacedPlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

/**
 * Example replacement renderer for a {@link Creeper}.<br>
 * This functionally replaces the model and animations of an existing entity without needing to replace the entity entirely
 * @see GeoReplacedEntityRenderer
 * @see ReplacedPlayerEntity
 */
public class ReplacedPlayerRenderer extends GeoReplacedEntityRenderer<AbstractClientPlayer, ReplacedPlayerEntity> {
	public ReplacedPlayerRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ReplacedPlayerModel(), new ReplacedPlayerEntity());

	}



	@Override
	public void preRender(PoseStack poseStack, ReplacedPlayerEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {
		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);


	}

	@Override
	public int getPackedOverlay(ReplacedPlayerEntity animatable, float u, float partialTick) {
		return super.getPackedOverlay(animatable, u, partialTick);
	}


}
