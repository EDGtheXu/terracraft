package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.mixinhelper.BellBlockDelayMixinHelper;
import com.theXu.terracraft0323.network.packet.menuhandler.serverMenuPacket;
import com.theXu.terracraft0323.ui.gui.itemInfoScreen;
import com.theXu.terracraft0323.ui.jewelrySlots.terraBag;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = NeoMod.MODID,value = Dist.CLIENT)
public class PlayerClientEvent {
    @SubscribeEvent
    public static void onPlayerDisConnection(EntityLeaveLevelEvent event){
        //客户端已经成功连接到服务器
        // 清空hashmap
        BellBlockDelayMixinHelper.BellBlockEntityMap.clear();
        BellBlockDelayMixinHelper.HitCoolDownMap.clear();
        BellBlockDelayMixinHelper.DirectionMap.clear();



    }

    @SubscribeEvent
    public static void renderGui(RenderGuiEvent.Post event) {

        itemInfoScreen iI = new itemInfoScreen(Minecraft.getInstance());
        iI.render(event.getGuiGraphics());


    }



    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton.Post event) {
        //System.out.println(event.getKey());
        //System.out.println(event.getAction());
        //System.out.println(event.getButton());

    }


    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        //System.out.println(event.getKey());
        if(event.getKey()==32){
            abilityRegister.get().inputJumping = true;

        }else if(registerKeyEvent.KeyBinding.MAIN_MENU_KEY.consumeClick()){
            System.out.println("input I");
            PacketDistributor.sendToServer(new serverMenuPacket(1));
        }

    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.MouseScrollingEvent event) {
        System.out.println(event.getScrollDeltaX());
        System.out.println(event.getScrollDeltaY());
    }


}
