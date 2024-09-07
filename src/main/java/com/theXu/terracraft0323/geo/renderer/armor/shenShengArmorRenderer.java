package com.theXu.terracraft0323.geo.renderer.armor;


import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.item.WolfArmorItem;
import com.theXu.terracraft0323.geo.item.shenShengArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

/**
 * Example {@link GeoRenderer} for the {@link WolfArmorItem} example item
 */
public final class shenShengArmorRenderer extends GeoArmorRenderer<shenShengArmorItem> {
	public shenShengArmorRenderer() {
		super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "armor/shen_sheng_armor")));
	}
}
