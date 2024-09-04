package com.theXu.terracraft0323.item.terraJewelry.boot;

import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.item.terraJewelry.jewelryItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.List;

public class bootBase extends jewelryItem {
    public float moveSpeed;
    public bootBase(Properties properties,float moveSpeed) {
        super(properties);
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
        componentList.add(Component.literal(""));
        componentList.add(Component.literal("移速加成 "+moveSpeed)
                .withStyle(Style.EMPTY.withColor(
                        Color.cyan.getRGB())
                ));


    }
}
