package com.theXu.terracraft0323.block;

import com.mojang.datafixers.types.templates.Tag;
import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.block.custome.PotatoTNTBlock;
import com.theXu.terracraft0323.block.custome.PotatoTNTPrepareBlock;
import com.theXu.terracraft0323.block.custome.SoundBlock;
import com.theXu.terracraft0323.item.ModItems;
import com.theXu.terracraft0323.magicStoreCraft.geo.block.magicBlock;
import com.theXu.terracraft0323.sound.ModSounds;
import com.theXu.terracraft0323.tag.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
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






    //TerraCraft+
    public static final DeferredBlock<Block> GANG_ORE = registerBlock("gang_ore",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> YIN_ORE = registerBlock("yin_ore",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> YU_YAN_ORE = registerBlock("yu_yan_ore",
            ()-> new DropExperienceBlock(UniformInt.of(5, 6), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 999.0F)));

    public static final DeferredBlock<Block> BA_JIN_ORE = registerBlock("ba_jin_ore",
            ()-> new DropExperienceBlock(UniformInt.of(6, 8), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 999.0F)));







    public static final DeferredBlock<Block> MAGIC_STORE = registerSingleBlock("magic_storage_block",
            ()->new magicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().sound(ModSounds.SOUND_BLOCK_SOUNDS).requiresCorrectToolForDrops()));











    public static DeferredBlock<Block> registerSingleBlock(String name, Supplier<Block> blockSupplier) {
        DeferredBlock<Block> register = BLOCKS.register(name, blockSupplier);
        return register;
    }


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
