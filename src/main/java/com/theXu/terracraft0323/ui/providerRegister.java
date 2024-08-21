package com.theXu.terracraft0323.ui;

import com.theXu.terracraft0323.NeoMafishMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class providerRegister {
    protected static final DeferredRegister<BlockEntityType<?>> MENU_PROVIDER_TYPES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NeoMafishMod.MODID);

    public static void register(IEventBus eventBus){
        MENU_PROVIDER_TYPES.register(eventBus);
    }

/*
    public static final Supplier<BlockEntityType<mainMenuProvider>> MAIN_MEMU_PROVIDER = MENU_PROVIDER_TYPES.register(
            "main_block_provider",
            ()->BlockEntityType.Builder.of(mainMenuProvider::new, ModBlocks.MAIN_MENU_BLOCK.get())
                    .build(null)
    );
*/








}
