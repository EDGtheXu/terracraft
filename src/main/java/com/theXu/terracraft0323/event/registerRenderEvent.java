package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.item.terraSummon.ke_yan_fa_zhang.ke_yan_fa_zhang_summon_render;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import com.theXu.terracraft0323.item.terraSword.waveHandler.emptyRender;
import com.theXu.terracraft0323.item.terraSword.yongYeSword.yong_ye_ren_waveRender;
import com.theXu.terracraft0323.item.terraSword.tailaren.tai_la_ren_waveRender;
import com.theXu.terracraft0323.item.terraSword.iceSword.ice_sword_waveRender;
import com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan.kesuluzhiyanRender;
import com.theXu.terracraft0323.item.terraSword.xingNu.xing_nu_waveRender;

@EventBusSubscriber(modid = NeoMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class registerRenderEvent {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        /*
        MenuScreens.register(ModMenuTypes.MY_ANVIL_BLOCK_MENU.get(), MyAnvilBlockScreen::new);
    */
    }

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //wave
        event.registerEntityRenderer(ModEntities.EMPTY_ENTITY.get(), emptyRender::new);
        event.registerEntityRenderer(ModEntities.TAI_LA_REN_WAVE.get(), tai_la_ren_waveRender::new);
        event.registerEntityRenderer(ModEntities.YONG_YE_REN_WAVE.get(), yong_ye_ren_waveRender::new);
        event.registerEntityRenderer(ModEntities.ICE_SWORD_WAVE.get(), ice_sword_waveRender::new);
        event.registerEntityRenderer(ModEntities.XING_NU_WAVE.get(), xing_nu_waveRender::new);

        //boss
        event.registerEntityRenderer(ModEntities.KE_YAN.get(),kesuluzhiyanRender::new);

        //summon
        event.registerEntityRenderer(ModEntities.KE_YAN_SUMMON.get(), ke_yan_fa_zhang_summon_render::new );

    }


}
