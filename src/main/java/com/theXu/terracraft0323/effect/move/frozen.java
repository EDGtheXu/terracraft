package com.theXu.terracraft0323.effect.move;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class frozen extends MobEffect {
    float rate = 0.2f;
    int lev = 1;

    public frozen(MobEffectCategory pCategory, int pColor) {
        super(pCategory, 0);

        //removeAttributeModifiers();
        addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"frozen2"),20F, AttributeModifier.Operation.ADD_VALUE);
        System.out.println("aplly");
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int pAmplifier) {
        super.applyEffectTick(entity,pAmplifier);
        float newspeed = entity.getSpeed()*rate/lev;
        entity.setSpeed(newspeed);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_){
        return true;
    }


}
