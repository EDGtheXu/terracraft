package com.theXu.terracraft0323.tag;

import com.theXu.terracraft0323.NeoMafishMod;
import com.theXu.terracraft0323.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class modTagProvider extends ItemTagsProvider {


    public modTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, NeoMafishMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.JEWELRY)
                .add(ModItems.TIAN_JIE_XING_PAN.get())
        ;

        tag(ModTags.Items.JEWELRY_FLY)
                .add(ModItems.TIAN_JIE_XING_PAN.get())
                ;

    }
}