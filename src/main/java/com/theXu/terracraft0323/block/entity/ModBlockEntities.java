package com.theXu.terracraft0323.block.entity;

import com.theXu.terracraft0323.NeoMafishMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NeoMafishMod.MODID);


    public static void register(IEventBus bus){
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
