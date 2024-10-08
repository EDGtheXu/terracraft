package com.theXu.terracraft0323.effect;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.effect.custom.NormalEffect;
import com.theXu.terracraft0323.effect.custom.normalAttributeEffevt;
import com.theXu.terracraft0323.effect.hurt.po_xiao;
import com.theXu.terracraft0323.effect.move.frozen;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEffects {
    public static  DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, NeoMod.MODID);

    public static final DeferredHolder<MobEffect,MobEffect> IRONMAN =registerDeferredHolder("ironman",()->new NormalEffect(MobEffectCategory.BENEFICIAL,0xFF0000)) ;
    public static final DeferredHolder<MobEffect,MobEffect>  FLOWER_EFFECT =registerDeferredHolder("flower_effect",()->new NormalEffect(MobEffectCategory.BENEFICIAL,0xFF0000)) ;
    public static final DeferredHolder<MobEffect,MobEffect>  TELEPORT_EFFECT = registerDeferredHolder("teleport_effect",()->new NormalEffect(MobEffectCategory.BENEFICIAL,0x00FFFF));
    public static final DeferredHolder<MobEffect,MobEffect>  SPIDER_EFFECT = registerDeferredHolder("spider_effect",()->new NormalEffect(MobEffectCategory.BENEFICIAL,0x800000));
    public static final DeferredHolder<MobEffect,MobEffect>  SHEEP_EFFECT = registerDeferredHolder("sheep_effect",()->new NormalEffect(MobEffectCategory.BENEFICIAL,0x80F18BEB));
    public static final DeferredHolder<MobEffect,MobEffect>  ANTIDOTE_EFFECT =registerDeferredHolder("antidote_effect",()->new NormalEffect(MobEffectCategory.BENEFICIAL,0x80FFFFFF));



    public static final DeferredHolder<MobEffect,MobEffect>  PO_XIAO =registerDeferredHolder("po_xiao_effect",()->new po_xiao(MobEffectCategory.HARMFUL,0x80FFFFFF,1));
    public static final DeferredHolder<MobEffect,MobEffect>  ICE_FROZEN =registerDeferredHolder("frozen_effect",()->new frozen(MobEffectCategory.HARMFUL,0x80FFFFFF));
    public static final DeferredHolder<MobEffect,MobEffect>  CAVE_SHOW =registerDeferredHolder("cave_effect",()->new NormalEffect(MobEffectCategory.BENEFICIAL,0xFFFFFF00));
    public static final DeferredHolder<MobEffect,MobEffect>  IRON_EFFECT =registerDeferredHolder("iron_effect",()->new normalAttributeEffevt(MobEffectCategory.BENEFICIAL,0xBF8f008F,Attributes.ARMOR,"iron",4f, AttributeModifier.Operation.ADD_VALUE));





    public static DeferredHolder<MobEffect,MobEffect> registerDeferredHolder(String name, Supplier<MobEffect> supplier){
        return EFFECTS.register(name,supplier);
    }

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
