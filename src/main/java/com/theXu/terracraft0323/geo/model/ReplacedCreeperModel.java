package com.theXu.terracraft0323.geo.model;


import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.entity.ReplacedCreeperEntity;
import com.theXu.terracraft0323.geo.renderer.ReplacedCreeperRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

/**
 * Example {@link software.bernie.geckolib.model.GeoModel} for dynamically replacing an
 * existing entity's renderer with a GeckoLib model (in this case, {@link net.minecraft.world.entity.monster.Creeper}
 * @see software.bernie.geckolib.renderer.GeoReplacedEntityRenderer
 * @see ReplacedCreeperRenderer ReplacedCreeperRenderer
 */
public class ReplacedCreeperModel extends DefaultedEntityGeoModel<ReplacedCreeperEntity> {
	public ReplacedCreeperModel() {
		super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "creeper"));
	}
}
