package com.theXu.terracraft0323.item.terraSword.iceSword;

import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.item.terraSword.waveHandler.wave_base;
import com.theXu.terracraft0323.registry.ParticleRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ice_sword_wave extends wave_base {
    @Override
    public float damage() {
        return 10;
    }
    @Override
    public float waveDur() {
        return 2000;
    }


    public ice_sword_wave(EntityType<? extends ice_sword_wave> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ice_sword_wave(Entity pOwner, Level pLevel) {
        super(ModEntities.ICE_SWORD_WAVE.get(),pLevel);
        this.setOwner(pOwner);
    }


    @Override//添加减速药水效果
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(!this.level().isClientSide()) {

            Entity entity1 = pResult.getEntity();
            Entity entity = this.getOwner();
            entity1.hurt(this.damageSources().mobProjectile(this, entity instanceof LivingEntity livingentity ? livingentity : null), damage());
            if(entity1 instanceof LivingEntity && entity != getOwner()){

                ((LivingEntity)entity1).addEffect(new MobEffectInstance(ModEffects.ICE_FROZEN,500));
                                /*
                ((LivingEntity)entity1).addEffect(new MobEffectInstance(modEffects.PO_XIAO_1,500));
            */
            }

            discard();
        }
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!this.level().isClientSide()) discard();
    }

    @Override
    public void tick(){
        super.tick();
        level().addParticle(ParticleRegistry.DRAGON_FIRE_PARTICLE.get(), xOld,yOld,zOld,getDeltaMovement().x,getDeltaMovement().y,getDeltaMovement().z);

    }



    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.BUBBLE;
    }


    @Override
    protected float getLiquidInertia() {
        return 0.95f;
    }


}
