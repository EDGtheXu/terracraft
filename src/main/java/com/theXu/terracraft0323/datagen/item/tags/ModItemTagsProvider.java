package com.theXu.terracraft0323.datagen.item.tags;

import com.theXu.terracraft0323.item.ModItems;
import com.theXu.terracraft0323.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.CAT_FOOD)
                .add(ModItems.CHEESE_BERGER.get());

        tag(ModTags.Items.JEWELRY)
                .add(ModItems.TIAN_JIE_XING_PAN.get())
                .add(ModItems.TAI_LA_XUE.get())
                .add(ModItems.JIN_MA_ZHANG.get())
        ;

        tag(ModTags.Items.JEWELRY_FLY)
                .add(ModItems.TIAN_JIE_XING_PAN.get())
        ;


    }
}
