package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.NeoMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = NeoMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class registerParticalEvent {
    @SubscribeEvent
    public static void registerParticle(RegisterParticleProvidersEvent event){

    }
}
