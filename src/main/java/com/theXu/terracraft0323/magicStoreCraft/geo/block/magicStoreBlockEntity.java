package com.theXu.terracraft0323.magicStoreCraft.geo.block;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.block.entity.ModBlockEntities;
import com.theXu.terracraft0323.magicStoreCraft.magicStorageMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class magicStoreBlockEntity extends BlockEntity implements MenuProvider, GeoBlockEntity {

    public magicStoreBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MAGIC_STORE_BLOCK_ENTITY.get(),pPos,pBlockState);
    }


    private final RawAnimation animation = RawAnimation.begin().then("animation.model.idle", Animation.LoopType.LOOP);

    protected <E extends GeoAnimatable> PlayState predicate(final AnimationState<E> state) {
        state.getController().setAnimation(animation);
        return PlayState.CONTINUE;
    }
    private final AnimationController<magicStoreBlockEntity> controller = new AnimationController<>(this,"idle",0,this::predicate);



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(controller);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;

    }










    @Override
    public Component getDisplayName() {
        return Component.translatable("gui."+ NeoMod.MODID+".magic_storage");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new magicStorageMenu(id,inventory, null);
    }
}