package com.theXu.terracraft0323.block.entity;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.block.ModBlock;
import com.theXu.terracraft0323.magicStoreCraft.magicStoreBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
             DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NeoMod.MODID);

    public static final Supplier<BlockEntityType<magicStoreBlockEntity>> MAGIC_STORE_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("magic_store_block_entity", () ->
                    BlockEntityType.Builder.of(magicStoreBlockEntity::new,
                            ModBlock.MAGIC_STORE.get()).build(null));

    public static void register(IEventBus bus){
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
