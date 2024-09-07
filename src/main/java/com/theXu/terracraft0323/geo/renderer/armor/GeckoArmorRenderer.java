package com.theXu.terracraft0323.geo.renderer.armor;


import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.item.GeckoArmorItem;
import com.theXu.terracraft0323.geo.item.WolfArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForge;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

/**
 * Example {@link GeoRenderer} for the {@link WolfArmorItem} example item
 */
public final class GeckoArmorRenderer extends GeoArmorRenderer<GeckoArmorItem> {
	public GeckoArmorRenderer() {
		super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "armor/gecko_armor")));

		addRenderLayer(new AutoGlowingGeoLayer<>(this));
	}
}
