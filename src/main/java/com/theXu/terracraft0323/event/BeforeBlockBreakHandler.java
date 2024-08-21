package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.enchantmentblock.BlockEnchantmentStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber
public class BeforeBlockBreakHandler {

    @SubscribeEvent
    public static void OnBlockBreak (BlockEvent.BreakEvent event){
        BlockPos pos = event.getPos();
        int level = BlockEnchantmentStorage.getLevel(Enchantments.PROTECTION, pos);
        if (level>0 && !event.getPlayer().isCreative()){
            event.setCanceled(true);
        }
    }
}
