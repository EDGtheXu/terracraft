package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan.kesuluzhiyan;
import com.theXu.terracraft0323.entity.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class registerCreatureEvent {
    @SubscribeEvent
    public static void setupAttributes(EntityAttributeCreationEvent event) {
        // 第一个参数是你的entityType，第二个参数是你的AttributeSupplier，通过我们写的Builder获得。
        event.put(ModEntities.KE_YAN.get(), kesuluzhiyan.createAttributes().build());
    }


}
