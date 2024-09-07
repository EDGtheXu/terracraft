package com.theXu.terracraft0323.item.terraJewelry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;

import java.awt.*;
import java.util.List;

public class jewelryItem extends Item {


    public jewelryItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    public void jewelryTick(Entity pEntity) {

    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        componentList.add(Component.literal("TerraCraft Jewelry").withColor(
                Color.green.getRGB()
        ));

        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
    }




}
