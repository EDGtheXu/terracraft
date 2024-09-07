package com.theXu.terracraft0323.geo.model;


import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.entity.RaceCarEntity;
import com.theXu.terracraft0323.geo.renderer.RaceCarRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;

/**
 * Example {@link GeoModel} for the {@link RaceCarEntity}
 * @see RaceCarRenderer
 */
public class RaceCarModel extends DefaultedEntityGeoModel<RaceCarEntity> {
	public RaceCarModel() {
		super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "race_car"));
	}

	// We want our model to render using the translucent render type
	@Override
	public RenderType getRenderType(RaceCarEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}
}