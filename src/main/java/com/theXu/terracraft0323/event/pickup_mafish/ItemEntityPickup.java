package com.theXu.terracraft0323.event.pickup_mafish;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

@EventBusSubscriber(modid = NeoMod.MODID)
public class ItemEntityPickup {
    @SubscribeEvent
    public static void ItemEntityPickupEvent(ItemEntityPickupEvent.Pre event) {//去Event类，按ctrl+h，展开看所有events
        if (!event.getItemEntity().hasPickUpDelay() && event.getItemEntity().getItem().is(ModItems.MAFISH) && !event.getPlayer().level().isClientSide) {
            if (event.getPlayer() instanceof Player player) {
                player.sendSystemMessage(Component.literal(  "被"+ player.getName().getString() +"捡起来了!(*/ω＼*)"));
            }
        }
    }
}
