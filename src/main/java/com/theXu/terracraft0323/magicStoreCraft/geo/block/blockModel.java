package com.theXu.terracraft0323.magicStoreCraft.geo.block;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class blockModel extends GeoModel<magicStoreBlockEntity> {


    @Override
    public ResourceLocation getModelResource(magicStoreBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"geo/magic_storage_block.geo.json");

    }

    @Override
    public ResourceLocation getTextureResource(magicStoreBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"textures/entity/magic_storage_block.png");

    }

    @Override
    public ResourceLocation getAnimationResource(magicStoreBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"animations/magic_storage_block.animation.json");

    }
}
