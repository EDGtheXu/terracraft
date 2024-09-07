package com.theXu.terracraft0323.magicStoreCraft.geo.item;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class itemModel extends GeoModel<magicStorageItem> {


    @Override
    public ResourceLocation getModelResource(magicStorageItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"geo/magic_storage_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(magicStorageItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"textures/entity/magic_storage_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(magicStorageItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"animations/magic_storage_block.animation.json");
    }




}