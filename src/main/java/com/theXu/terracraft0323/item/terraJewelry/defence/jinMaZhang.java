package com.theXu.terracraft0323.item.terraJewelry.defence;

import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.item.terraJewelry.jewelryItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class jinMaZhang extends jewelryItem {

    public jinMaZhang(Properties properties) {
        super(properties);
    }

    @Override
    public void jewelryTick(Entity pEntity) {
        ((LivingEntity) pEntity).addEffect(new MobEffectInstance(ModEffects.SAFE_FALL,25));

    }

}
