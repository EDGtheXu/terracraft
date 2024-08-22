package com.theXu.terracraft0323.effect.move;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffectCategory;

public class fastMove extends MobEffect {

    public fastMove(MobEffectCategory pCategory, String name, float rate) {
        super(pCategory, 0);

        addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,name),
                rate,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

    }


}
