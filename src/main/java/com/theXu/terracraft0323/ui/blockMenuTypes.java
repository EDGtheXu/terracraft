package com.theXu.terracraft0323.ui;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.block.ModBlock;
import com.theXu.terracraft0323.magicStoreCraft.geo.block.magicStoreBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class blockMenuTypes {
    protected static final DeferredRegister<BlockEntityType<?>> MENU_PROVIDER_TYPES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NeoMod.MODID);

    public static void register(IEventBus eventBus){
        MENU_PROVIDER_TYPES.register(eventBus);
    }


    public static final Supplier<BlockEntityType<magicStoreBlockEntity>> MAGIC_STORE_MENU_PROVIDER = MENU_PROVIDER_TYPES.register(
            "magic_store_menu_provider",
            ()->BlockEntityType.Builder.of(magicStoreBlockEntity::new, ModBlock.MAGIC_STORE.get())
                    .build(null)
    );









}
