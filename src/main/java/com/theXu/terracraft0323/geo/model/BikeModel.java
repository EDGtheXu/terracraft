package com.theXu.terracraft0323.geo.model;


import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.entity.BikeEntity;
import com.theXu.terracraft0323.geo.renderer.BikeRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;

/**
 * Example {@link GeoModel} for the {@link BikeEntity}
 * @see BikeRenderer
 */
public class BikeModel extends DefaultedEntityGeoModel<BikeEntity> {
	public BikeModel() {
		super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "bike"));
	}

	// We want this entity to have a translucent render
	@Override
	public RenderType getRenderType(BikeEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}
}
