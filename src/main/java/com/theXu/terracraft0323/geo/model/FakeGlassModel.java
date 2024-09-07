package com.theXu.terracraft0323.geo.model;


import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.entity.FakeGlassEntity;
import com.theXu.terracraft0323.geo.renderer.FakeGlassRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;

/**
 * Example {@link GeoModel} for the {@link FakeGlassEntity}
 * @see FakeGlassRenderer
 */
public class FakeGlassModel extends DefaultedEntityGeoModel<FakeGlassEntity> {
	private static final ResourceLocation REDSTONE_BLOCK_TEXTURE =
			ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/redstone_block.png");

	public FakeGlassModel() {
		super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "fake_glass"));
	}

	// We just want our texture to be the Redstone Block texture
	@Override
	public ResourceLocation getTextureResource(FakeGlassEntity animatable) {
		return REDSTONE_BLOCK_TEXTURE;
	}

	// We want our entity to be translucent
	@Override
	public RenderType getRenderType(FakeGlassEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(texture);
	}
}
