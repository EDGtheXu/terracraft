package com.theXu.terracraft0323.block.magicStoreCraft;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class magicStoreBlock extends Block {
    public magicStoreBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    }



    /**
     * 当玩家使用该方块时触发的交互逻辑。这里如果是空手就从DATA中获得物品，如果手持物品就放入到DATA中去。
     *
     * @param pState  方块的状态。
     * @param pLevel  方块所在的世界。
     * @param pPos    方块的位置。
     * @param pPlayer 与方块交互的玩家。
     * @param pHand   玩家使用的手（主手或副手）。
     * @param pHit    玩家点击方块的碰撞结果。
     * @return 交互的结果，表示交互是否成功。
     */

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        System.out.println("use");
        if(!pLevel.isClientSide){

            magicStoreData data  = magicStoreData.get(pLevel);
            ItemStack mainHandItem = pPlayer.getMainHandItem();

            if(mainHandItem.isEmpty()){
                ItemStack itemStack = data.getItem();
                pPlayer.setItemInHand(InteractionHand.MAIN_HAND,itemStack);
            }else{
                ItemStack itemStack =mainHandItem.copy();
                mainHandItem.shrink(mainHandItem.getCount());
                data.putItem(itemStack);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }
}