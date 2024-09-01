package com.theXu.terracraft0323.item.terraJewelry;

import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.List;

public class jewelryItem extends Item {


    public jewelryItem(Properties properties) {
        super(properties);
    }

    public void jewelryTick(Entity pEntity) {

    }
    public void onJewelryEquip(Entity pEntity) {

    }
    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        componentList.add(Component.literal("TerraCraft Sword").withColor(
                Color.green.getRGB()
        ));

        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
    }




}
