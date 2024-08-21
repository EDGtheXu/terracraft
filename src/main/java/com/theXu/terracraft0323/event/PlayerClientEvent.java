package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.NeoMafishMod;
import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.mixinhelper.BellBlockDelayMixinHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

@EventBusSubscriber(modid = NeoMafishMod.MODID,value = Dist.CLIENT)
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
    public static void onKeyInput(InputEvent.Key event) {
        //System.out.println(event.getKey());
        if(event.getKey()==32){
            abilityRegister.get().inputJumping = true;
        }

    }
    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton.Post event) {
        //System.out.println(event.getKey());
        //System.out.println(event.getAction());
        //System.out.println(event.getButton());

    }

}
