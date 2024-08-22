package com.theXu.terracraft0323.block;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.block.custome.PotatoTNTBlock;
import com.theXu.terracraft0323.block.custome.PotatoTNTPrepareBlock;
import com.theXu.terracraft0323.block.custome.SoundBlock;
import com.theXu.terracraft0323.block.magicStoreCraft.magicStoreBlock;
import com.theXu.terracraft0323.item.ModItems;
import com.theXu.terracraft0323.sound.ModSounds;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlock {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoMod.MODID);

    public static final DeferredBlock<Block> GOLD_MELON = registerSimpleBlock("gold_melon", BlockBehaviour.Properties.of().mapColor(MapColor.GOLD));
//    public static final DeferredBlock<Block> GEM_POLISHING_STATION=registerBlock("gem_polishing_station",
//            ()-> new GemPolishingStationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final DeferredBlock<Block> POTATO_TNT = registerBlock("potato_tnt",
            ()-> new PotatoTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)
                    .instrument(NoteBlockInstrument.BASS).strength(5f,1f).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> POTATO_TNT_PREPARE = registerBlock("potato_tnt_prepare",
            ()-> new PotatoTNTPrepareBlock(MobEffects.FIRE_RESISTANCE,10,BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM).noOcclusion().noCollission()));

    public static final DeferredBlock<Block> WHATE_CAT_BLOCK = registerBlock("white_cat_block",
            ()-> new SoundBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(ModSounds.SOUND_BLOCK_SOUNDS)));



    //TerraCraft

    public static final DeferredBlock<Block> MAGIC_STORE = registerBlock("magic_store",
            magicStoreBlock::new);











    private static DeferredBlock<Block> registerBlock(String name, Supplier<Block> blockSupplier) {
        DeferredBlock<Block> register = BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name,()-> new BlockItem(register.get(),new Item.Properties()));
        return register;
    }

    public static DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties props) {
        DeferredBlock<Block> deferredBlock =  BLOCKS.registerSimpleBlock(name,props);
        ModItems.ITEMS.register(name,()-> new BlockItem(deferredBlock.get(),new Item.Properties()));
        return  deferredBlock;
    }

    public static DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties props, Item.Properties properties) {
        DeferredBlock<Block> deferredBlock =  BLOCKS.registerSimpleBlock(name,props);
        ModItems.ITEMS.register(name,()-> new BlockItem(deferredBlock.get(),properties));
        return  deferredBlock;
    }










    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
