package com.theXu.terracraft0323.effect.hurt;

import com.theXu.terracraft0323.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class po_xiao extends MobEffect {
    int level = 1;
    int damage = 1;
    public po_xiao(MobEffectCategory pCategory, int pColor, int level) {
        super(pCategory, pColor);
        this.level = level;
    }
    public po_xiao() {
        super(MobEffectCategory.HARMFUL, ModEffects.PO_XIAO.get().getColor());
    }


    @Override
    public boolean applyEffectTick(LivingEntity entity, int pAmplifier) {
        super.applyEffectTick(entity,pAmplifier);
        entity.hurt(entity.damageSources().onFire(),level*damage);
        entity.sendSystemMessage(Component.literal(level*damage+""));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
        int i = 25 >> p_294232_;
        if (i > 0) {
            return p_295368_ % i == 0;
        } else {
            return true;
        }
    }
    public void upLevel(){
        if(level<4) level+=1;
    }
}
