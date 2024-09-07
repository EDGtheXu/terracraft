package com.theXu.terracraft0323.geo.renderer;


import com.theXu.terracraft0323.geo.entity.RaceCarEntity;
import com.theXu.terracraft0323.geo.model.RaceCarModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * Example {@link software.bernie.geckolib.renderer.GeoRenderer} implementation of an entity
 * @see RaceCarModel
 * @see RaceCarEntity
 */
public class RaceCarRenderer extends GeoEntityRenderer<RaceCarEntity> {
	public RaceCarRenderer(EntityRendererProvider.Context context) {
		super(context, new RaceCarModel());
	}
}