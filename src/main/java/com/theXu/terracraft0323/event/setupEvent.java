package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.NeoMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = NeoMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class setupEvent {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event){


    }




}
