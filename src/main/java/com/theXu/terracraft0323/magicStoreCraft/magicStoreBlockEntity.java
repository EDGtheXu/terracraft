package com.theXu.terracraft0323.magicStoreCraft;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.block.entity.ModBlockEntities;
import com.theXu.terracraft0323.ui.jewelrySlots.terraBag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class magicStoreBlockEntity extends BlockEntity  implements MenuProvider {

    public magicStoreBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MAGIC_STORE_BLOCK_ENTITY.get(),pPos,pBlockState);
        System.out.println("gen entity");


    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("gui."+ NeoMod.MODID+".magic_storage");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new terraBag(id,inventory, null);
    }
}