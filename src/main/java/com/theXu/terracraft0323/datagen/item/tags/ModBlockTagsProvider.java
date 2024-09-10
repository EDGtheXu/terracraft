package com.theXu.terracraft0323.datagen.item.tags;

import com.theXu.terracraft0323.block.ModBlock;
import com.theXu.terracraft0323.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ModTags.Blocks.NEED_YE_LV_TOOL);
        this.tag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL);
        this.tag(ModTags.Blocks.NEED_BA_JIN_TOOL);
        this.tag(ModTags.Blocks.NEED_YU_YAN_TOOL).add(ModBlock.BA_JIN_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlock.BA_JIN_ORE.get(),ModBlock.YU_YAN_ORE.get());


        this.tag(ModTags.Blocks.INCORRECT_FOR_YE_LV_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_SHEN_SHENG_TOOL)  .addTag(ModTags.Blocks.NEED_YE_LV_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_BA_JIN_TOOL)      .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_YU_YAN_TOOL)      .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL).addTag(ModTags.Blocks.NEED_BA_JIN_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)        .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL).addTag(ModTags.Blocks.NEED_BA_JIN_TOOL).addTag(ModTags.Blocks.NEED_YU_YAN_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)          .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL).addTag(ModTags.Blocks.NEED_BA_JIN_TOOL).addTag(ModTags.Blocks.NEED_YU_YAN_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_IRON_TOOL)             .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL).addTag(ModTags.Blocks.NEED_BA_JIN_TOOL).addTag(ModTags.Blocks.NEED_YU_YAN_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_STONE_TOOL)            .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL).addTag(ModTags.Blocks.NEED_BA_JIN_TOOL).addTag(ModTags.Blocks.NEED_YU_YAN_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)             .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL).addTag(ModTags.Blocks.NEED_BA_JIN_TOOL).addTag(ModTags.Blocks.NEED_YU_YAN_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)           .addTag(ModTags.Blocks.NEED_YE_LV_TOOL).addTag(ModTags.Blocks.NEED_SHEN_SHENG_TOOL).addTag(ModTags.Blocks.NEED_BA_JIN_TOOL).addTag(ModTags.Blocks.NEED_YU_YAN_TOOL);


        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlock.BA_JIN_ORE.get(),
                ModBlock.YU_YAN_ORE.get()
        );



    }
}
