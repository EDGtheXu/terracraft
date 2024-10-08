package com.theXu.terracraft0323.effect.move;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class frozen extends MobEffect {

    public frozen(MobEffectCategory pCategory, int pColor) {
        super(pCategory, 0);

        addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"frozen2"),20F, AttributeModifier.Operation.ADD_VALUE);

    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int pAmplifier) {
        super.applyEffectTick(entity,pAmplifier);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_){
        return true;
    }


}
