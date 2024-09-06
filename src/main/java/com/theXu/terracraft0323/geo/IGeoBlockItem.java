package com.theXu.terracraft0323.geo;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;

public abstract class IGeoBlockItem extends BlockItem implements GeoItem {
    public IGeoBlockItem(Block block) {
        super(block,new Item.Properties());
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }



}
