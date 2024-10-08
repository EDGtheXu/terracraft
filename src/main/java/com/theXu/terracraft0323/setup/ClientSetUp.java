package com.theXu.terracraft0323.setup;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.entity.ModEntities;

import com.theXu.terracraft0323.render.itemModel.ItemModelEvent;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = NeoMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetUp {

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.TNT_PROJECTILE.get(),ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.STONE_PROJECTILE.get(),ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.LIGHTNING_PROJECTILE.get(),ThrownItemRenderer::new);
    }


    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        ItemModelEvent.registerZhuGeItemProperties();

//        event.enqueueWork(()->{
//            BlockEntityRenderers.register(ModBlockEntities.MAGIC_STORE_BLOCK_ENTITY.get(),testRenderer::new);
//        });



    }


}
