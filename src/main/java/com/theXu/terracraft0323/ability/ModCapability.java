package com.theXu.terracraft0323.ability;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.ability.playerLevel.playerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ModCapability {
    public static final EntityCapability<playerLevel,Void> LEVELS_HANDLE =
            EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"levels_handle"), playerLevel.class);









    @EventBusSubscriber(modid = NeoMod.MODID,bus = EventBusSubscriber.Bus.MOD)
    public static class ModEventBus{
        @SubscribeEvent
        private static void registerCapabilities(RegisterCapabilitiesEvent event) {
            event.registerEntity(LEVELS_HANDLE,
                    EntityType.PLAYER,
                    new abilityRegister());
        }
    }

}
