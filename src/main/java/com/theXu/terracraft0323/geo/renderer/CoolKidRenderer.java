package com.theXu.terracraft0323.geo.renderer;


import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.entity.CoolKidEntity;
import com.theXu.terracraft0323.geo.renderer.layer.CoolKidGlassesLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * Example {@link software.bernie.geckolib.renderer.GeoRenderer} implementation of an entity that uses a {@link software.bernie.geckolib.renderer.layer.GeoRenderLayer render layer}
 * @see CoolKidGlassesLayer
 * @see CoolKidEntity
 */
public class CoolKidRenderer extends GeoEntityRenderer<CoolKidEntity> {
	public CoolKidRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "cool_kid")));

		this.shadowRadius = 0.25f;

		// Add our render layer
		addRenderLayer(new CoolKidGlassesLayer(this));
    }
}
