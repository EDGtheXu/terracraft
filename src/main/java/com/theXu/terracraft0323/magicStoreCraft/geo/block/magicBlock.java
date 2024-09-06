package com.theXu.terracraft0323.magicStoreCraft.geo.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class magicBlock extends BaseEntityBlock {
    public magicBlock(Properties properties) {
        super(properties);

    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        super.spawnDestroyParticles(level, player, pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {

        return null;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        //return Block.box(0,0,0,0,0,0);
        return Block.box(0,0,0,16,16,16);
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

        if(!pLevel.isClientSide){

            pPlayer.openMenu(this.getMenuProvider(pState,pLevel,pPos),pPos);
        }
        return ItemInteractionResult.SUCCESS;
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {

        return new magicStoreBlockEntity(blockPos,blockState);
    }

}
