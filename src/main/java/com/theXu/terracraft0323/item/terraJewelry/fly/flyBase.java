package com.theXu.terracraft0323.item.terraJewelry.fly;

import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.item.terraJewelry.jewelryItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.awt.*;
import java.util.List;


public class flyBase extends jewelryItem {
    public float up = 1.2f;//上升速度
    public float horizon = 1.2f;//水平速度
    public float duration = 1.5f;//飞行时间
    public flyBase(Properties pProperties, float up, float horizon, float duration) {
        super(pProperties);
        this.up = up;
        this.horizon = horizon;
        this.duration = duration;

    }

    //应用手持药水效果

    @Override
    public void jewelryTick(Entity pEntity) {
//        ((Player)pEntity).getAttribute(Attributes.FLYING_SPEED).addOrReplacePermanentModifier(
//                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(NeoMafishMod.MODID,"tjxp_speed_h"),
//                        1,
//                        AttributeModifier.Operation.ADD_VALUE)
//        );

        abilityRegister.get().flySpeedH = horizon;
        abilityRegister.get().flySpeedV = up;
        abilityRegister.get().flyMaxTime = duration * 20;
        abilityRegister.get().canFly = true;

    }
    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
        componentList.add(Component.literal(""));
        componentList.add(Component.literal("飞行时间 "+duration+'s')
                .withStyle(Style.EMPTY.withColor(
                                Color.cyan.getRGB())
                ));
        componentList.add(Component.literal("水平飞行速度 "+up)
                .withStyle(Style.EMPTY.withColor(
                        Color.cyan.getRGB())
                ));
        componentList.add(Component.literal("垂直加速度 "+horizon)
                .withStyle(Style.EMPTY.withColor(
                        Color.cyan.getRGB())
                ));

    }
}
