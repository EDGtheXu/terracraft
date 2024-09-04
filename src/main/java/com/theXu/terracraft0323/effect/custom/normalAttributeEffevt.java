package com.theXu.terracraft0323.effect.custom;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class normalAttributeEffevt extends MobEffect {

    public normalAttributeEffevt(MobEffectCategory pCategory, int color,Holder< Attribute> attributeHolder, String path,float amount, AttributeModifier.Operation operation) {
        super(pCategory, color);
        addAttributeModifier(attributeHolder, ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,path),amount, operation);
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