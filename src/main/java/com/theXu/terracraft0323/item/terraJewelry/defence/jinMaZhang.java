package com.theXu.terracraft0323.item.terraJewelry.defence;

import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.item.terraJewelry.jewelryItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class jinMaZhang extends jewelryItem {

    public jinMaZhang(Properties properties) {
        super(properties);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if(slotId < 41 )return;
        ((LivingEntity) entity).addEffect(new MobEffectInstance(ModEffects.SAFE_FALL,25));

    }

}
