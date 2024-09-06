package com.theXu.terracraft0323.magicStoreCraft.geo.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class magicStorageItem extends BlockItem implements GeoItem {


    public magicStorageItem(Block block) {
        super(block,new Item.Properties());
        SingletonGeoAnimatable.registerSyncedAnimatable(this);


    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private magicStorageItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new magicStorageItemRenderer();

                return this.renderer;
            }
        });
    }


    private final RawAnimation animation = RawAnimation.begin().then("animation.model.idle", Animation.LoopType.LOOP);
    protected <E extends GeoAnimatable> PlayState predicate(final AnimationState<E> state) {
        state.getController().setAnimation(animation);
        return PlayState.CONTINUE;
    }
    private final AnimationController<magicStorageItem> controller = new AnimationController<>(this,"idle",0,this::predicate);
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(controller);
    }
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
