package com.theXu.terracraft0323.item.custom;

import com.theXu.terracraft0323.item.ModFoods;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class BreadSwordItem extends SwordItem {

    public BreadSwordItem() {
            super(Tiers.STONE, new Properties().food(ModFoods.BREAD_SWORD));
    }


    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (remainingUseDuration%20==0){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.HARM,10,0));
        }
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
    }
}
