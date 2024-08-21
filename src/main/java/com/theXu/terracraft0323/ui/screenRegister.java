package com.theXu.terracraft0323.ui;

import com.theXu.terracraft0323.ui.jewelrySlots.slotsScreen;
import com.theXu.terracraft0323.ui.mainMenu.mainMenuScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class screenRegister {

    //screen注册

    @SubscribeEvent
    private static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(modMenuType.MAIN_MENU.get(), mainMenuScreen::new);
        event.register(modMenuType.TERRA_MENU.get(),slotsScreen::new);
    }




}



