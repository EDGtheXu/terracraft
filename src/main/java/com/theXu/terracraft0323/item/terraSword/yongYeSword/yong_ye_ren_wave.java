package com.theXu.terracraft0323.item.terraSword.yongYeSword;


import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.item.terraSword.waveHandler.wave_base;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class yong_ye_ren_wave extends wave_base {

    @Override
    public float damage() {
        return 10;
    }

    @Override
    public float waveDur() {
        return 300;
    }
    public yong_ye_ren_wave(EntityType<? extends yong_ye_ren_wave> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public yong_ye_ren_wave(Entity pOwner, Level pLevel) {
        super(ModEntities.YONG_YE_REN_WAVE.get(), pLevel);
        this.setOwner(pOwner);

    }


    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(!this.level().isClientSide()) {
            Entity entity1 = pResult.getEntity();
            Entity entity = this.getOwner();
            entity1.hurt(this.damageSources().mobProjectile(this, entity instanceof LivingEntity livingentity ? livingentity : null), damage());
        }
    }
    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.GLOW;
    }



    @Override
    public void tick() {
        super.tick();
        setPos(Objects.requireNonNull(getOwner()).position());
    }

}
