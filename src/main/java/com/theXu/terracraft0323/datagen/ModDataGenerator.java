package com.theXu.terracraft0323.datagen;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.datagen.item.tags.ModBlockTagsProvider;
import com.theXu.terracraft0323.datagen.item.tags.ModEnchantmentTagsProvider;
import com.theXu.terracraft0323.datagen.item.tags.ModItemTagsProvider;
import com.theXu.terracraft0323.datagen.loot.ModBlockLootProvider;
import com.theXu.terracraft0323.datagen.loot.ModEntityLootProvider;
import com.theXu.terracraft0323.datagen.loot.ModLootTableProvider;
import com.theXu.terracraft0323.datagen.recipe.ModRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// 用于注册数据生成器的类，该类通过EventBusSubscriber注解自动注册到MOD总线上
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = NeoMod.MODID)
public class ModDataGenerator {
    // 订阅GatherDataEvent事件，当数据收集事件触发时执行该方法
    @SubscribeEvent
    public static void register(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // 为数据生成器添加一个自定义的数据包内置条目提供者

        generator.addProvider(event.includeServer(), new ModDatapackBuiltinEntriesProvider(output, lookupProvider));

        generator.addProvider(event.includeServer(),new ModRecipe(output,lookupProvider));
        TagsProvider<Block> tagsprovider4 = generator.addProvider(event.includeServer(),new ModBlockTagsProvider(output,lookupProvider, NeoMod.MODID,existingFileHelper));
        //
        generator.addProvider(event.includeServer(),new ModItemTagsProvider(output,lookupProvider,tagsprovider4.contentsGetter(), NeoMod.MODID,existingFileHelper));


        generator.addProvider(event.includeServer(),
                (DataProvider.Factory<ModLootTableProvider>) pOutput -> new ModLootTableProvider(pOutput, Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(ModBlockLootProvider::new, LootContextParamSets.BLOCK),
                                new LootTableProvider.SubProviderEntry(ModEntityLootProvider::new, LootContextParamSets.ENTITY)
                        ),lookupProvider));



        //generator.addProvider(event.includeServer(),new ModEnchantmentTagsProvider(output,lookupProvider,existingFileHelper));




    }
}
