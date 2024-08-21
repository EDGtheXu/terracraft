package com.theXu.terracraft0323.item.terraSword.xingNu;

import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.item.terraSword.waveHandler.wave_base;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class xing_nu_wave extends wave_base {
    @Override
    public float damage() {
        return 10;
    }
    @Override
    public float waveDur() {
        return 2000;
    }
    public int hitCount = 2;


    public xing_nu_wave(EntityType<? extends xing_nu_wave> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public xing_nu_wave(Player pOwner, Level pLevel, Vec3 target) {
        super(ModEntities.XING_NU_WAVE.get(),pLevel);
        this.setOwner(pOwner);
    }


    @Override//添加减速药水效果
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(!this.level().isClientSide()) {

            Entity entity1 = pResult.getEntity();
            Entity entity = this.getOwner();
            entity1.hurt(this.damageSources().mobProjectile(this, entity instanceof LivingEntity livingentity ? livingentity : null), damage());
            if(entity1 instanceof LivingEntity){
                /*
                ((LivingEntity)entity1).addEffect(new MobEffectInstance(modEffects.FROZEN,500));
                ((LivingEntity)entity1).addEffect(new MobEffectInstance(modEffects.PO_XIAO_1,500));
            */
            }
            if(--hitCount == 0)
                discard();
        }
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!this.level().isClientSide())
            discard();
    }



    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.DUST_PLUME;
    }


    @Override
    protected float getLiquidInertia() {
        return 1f;
    }


}
