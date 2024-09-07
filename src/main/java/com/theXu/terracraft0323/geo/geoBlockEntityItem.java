package com.theXu.terracraft0323.geo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.sound.ModSounds;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.theXu.terracraft0323.block.ModBlock.registerSingleBlock;
import static com.theXu.terracraft0323.block.entity.ModBlockEntities.BLOCK_ENTITY_TYPES;
import static com.theXu.terracraft0323.item.ModItems.registerItem;

public class geoBlockEntityItem {

    public DeferredBlock<Block> MAGIC_STORE;
    public Supplier<BlockEntityType<blockEntity>> MAGIC_STORE_BLOCK_ENTITY;

    public geoBlockEntityItem(String blockName){

        geoModelPath = ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"geo/"+blockName+".json");
        texturePath =ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"textures/entity/"+blockName+".png");
        animationPath =ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"animations/"+blockName+".animation.json");


        MAGIC_STORE = registerSingleBlock("magic_storage_block",
                ()->new block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().sound(ModSounds.SOUND_BLOCK_SOUNDS)));


        MAGIC_STORE_BLOCK_ENTITY =
                BLOCK_ENTITY_TYPES.register("magic_store_block_entity", () ->
                        BlockEntityType.Builder.of(blockEntity::new,
                                MAGIC_STORE.get()).build(null));

        DeferredItem<Item> MAGIC_BLOCK_ITEM = registerItem("magic_storage_block",
                ()->new blockItem());



        BlockEntityRenderers.register(MAGIC_STORE_BLOCK_ENTITY.get(), blockEntityRenderer::new);


    }



    private String animName;
    public ResourceLocation geoModelPath;
    public ResourceLocation texturePath;
    public ResourceLocation animationPath;


    public class itemModel extends GeoModel<blockItem> {


        @Override
        public ResourceLocation getModelResource(blockItem animatable) {
            return geoModelPath;
        }

        @Override
        public ResourceLocation getTextureResource(blockItem animatable) {
            return texturePath;
        }

        @Override
        public ResourceLocation getAnimationResource(blockItem animatable) {
            return animationPath;
        }
    }
    public class blockModel extends GeoModel<blockEntity> {
        @Override
        public ResourceLocation getModelResource(blockEntity animatable) {
            return geoModelPath;

        }

        @Override
        public ResourceLocation getTextureResource(blockEntity animatable) {
            return texturePath;

        }

        @Override
        public ResourceLocation getAnimationResource(blockEntity animatable) {
            return animationPath;
        }
    }



    public class itemRenderer extends GeoItemRenderer<blockItem> {
        public itemRenderer() {
            super(new itemModel());
        }
    }

    public class blockEntityRenderer extends GeoBlockRenderer<blockEntity> {
        public blockEntityRenderer(BlockEntityRendererProvider.Context pContext ) {
            super(new blockModel());
        }
        @Override
        public void preRender(PoseStack poseStack, blockEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
            super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        }
    }




    private final RawAnimation animation = RawAnimation.begin().then(animName, Animation.LoopType.LOOP);
    public class blockItem extends IGeoBlockItem {
        public blockItem() {
            super(MAGIC_STORE.get());
        }

        @Override
        public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
            consumer.accept(new GeoRenderProvider() {
                private itemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                    if (this.renderer == null)
                        this.renderer = new itemRenderer();
                    return this.renderer;
                }
            });
        }

        protected <E extends GeoAnimatable> PlayState predicate(final AnimationState<E> state) {
            state.getController().setAnimation(animation);
            return PlayState.CONTINUE;
        }

        private final AnimationController<blockItem> controller = new AnimationController<>(this,"idle",0,this::predicate);
        private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

        @Override
        public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
            controllers.add(controller);
        }

        @Override
        public AnimatableInstanceCache getAnimatableInstanceCache() {
            return cache;
        }

    };




    public class blockEntity extends BlockEntity implements GeoBlockEntity {

        public blockEntity(BlockPos pPos, BlockState pBlockState) {
            super(MAGIC_STORE_BLOCK_ENTITY.get(),pPos,pBlockState);
        }

        protected <E extends GeoAnimatable> PlayState predicate(final AnimationState<E> state) {
            state.getController().setAnimation(animation);
            return PlayState.CONTINUE;
        }
        private final AnimationController<blockEntity> controller = new AnimationController<>(this,"idle",0,this::predicate);
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



    public class block extends BaseEntityBlock {
        public block(Properties properties) {
            super(properties);
        }



        @Override
        protected MapCodec<? extends BaseEntityBlock> codec() {

            return null;
        }

        @Override
        protected RenderShape getRenderShape(BlockState state) {
            return RenderShape.ENTITYBLOCK_ANIMATED;
        }

        @Override
        protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            //return Block.box(0,0,0,0,0,0);
            return Block.box(0,0,0,16,16,16);
        }

        @Override
        public ItemInteractionResult useItemOn(ItemStack stack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

            if(!pLevel.isClientSide){
                pPlayer.openMenu(this.getMenuProvider(pState,pLevel,pPos),pPos);
            }
            return ItemInteractionResult.SUCCESS;
        }


        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {

            return new blockEntity(blockPos,blockState);
        }

    }







}
