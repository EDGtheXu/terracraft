package com.theXu.terracraft0323.geo.renderer;


import com.theXu.terracraft0323.geo.entity.BikeEntity;
import com.theXu.terracraft0323.geo.model.BikeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * Example {@link software.bernie.geckolib.renderer.GeoRenderer} for {@link BikeEntity}
 * @see BikeModel
 */
public class BikeRenderer extends GeoEntityRenderer<BikeEntity> {
	public BikeRenderer(EntityRendererProvider.Context context) {
		super(context, new BikeModel());
	}
}
