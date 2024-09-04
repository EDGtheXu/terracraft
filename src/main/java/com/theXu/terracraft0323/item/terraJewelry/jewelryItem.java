package com.theXu.terracraft0323.item.terraJewelry;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.item.tiers.modTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

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
