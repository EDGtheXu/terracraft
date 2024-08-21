package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan.kesuluzhiyanModel;
import com.theXu.terracraft0323.item.terraSword.tailaren.tai_la_ren_waveModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class registerModelEvent {
    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(tai_la_ren_waveModel.LAYER_LOCATION, tai_la_ren_waveModel::createBodyLayer);
        evt.registerLayerDefinition(kesuluzhiyanModel.LAYER_LOCATION, kesuluzhiyanModel::createBodyLayer);



    }
}
