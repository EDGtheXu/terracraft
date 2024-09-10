package com.theXu.terracraft0323.entity.wave;

import com.theXu.terracraft0323.creature.monster.boss.kulouwang.kulouwang;
import com.theXu.terracraft0323.creature.monster.boss.kulouwang.kulouwangHand;
import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.item.terraSword.waveHandler.wave_base;
import net.minecraft.client.resources.metadata.animation.AnimationFrame;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class following_wave extends wave_base {
    private float damage = 1;
    @Override
    public float damage() {
        return damage;
    }
    @Override
    public float waveDur() {
        return 2000;
    }

    public Entity target;
    private float fact;
    boolean follow = true;

    public following_wave(EntityType<? extends following_wave> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public following_wave(Entity pOwner, Entity target, Level pLevel, float followFact,float damage) {
        super(ModEntities.KU_LOU_WANG_WAVE.get(),pLevel);
        this.setOwner(pOwner);
        this.target = target;
        this.fact = followFact;
        this.damage = damage;
    }

    @Override
    public void tick(){
        super.tick();
        if(target!=null) {
            Vec3 a = target.position().subtract(position());
            Vec3 b = this.getDeltaMovement();
            double angle = Math.acos(a.dot(b)/a.length()/b.length());
            if(distanceToSqr(target) < 2) follow = false;
            if(angle>1 ) {

                Vec3 voffset = a.cross(b);
                voffset = b.cross(voffset);



                this.addDeltaMovement(voffset.normalize().scale(fact));
            }
        }
    }

    @Override//添加减速药水效果
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(!this.level().isClientSide()) {

            Entity entity1 = pResult.getEntity();

            Entity entity = this.getOwner();

            if(entity1 instanceof kulouwangHand &&
                    getOwner() instanceof kulouwang)return;

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
