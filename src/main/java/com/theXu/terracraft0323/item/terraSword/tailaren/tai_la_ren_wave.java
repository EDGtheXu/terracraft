package com.theXu.terracraft0323.item.terraSword.tailaren;

import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.registry.ParticleRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class tai_la_ren_wave extends AbstractHurtingProjectile {

    //基础数据
    public Vector3f initForward = new Vector3f();
    public float damage(){
        return 30;
    }
    public float dur(){return 5000;};


    //默认构造方法
    public tai_la_ren_wave(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }
    //使用时的构造方法
    public tai_la_ren_wave(Entity owner,Level pLevel,Vector3f forward) {
        super(ModEntities.TAI_LA_REN_WAVE.get(), pLevel);
        initForward = forward;
        setRot(initForward.y,initForward.x);
        syncRot();
        setOwner(owner);
        //System.out.println(this.getBoundingBox());
        this.getBoundingBox().expandTowards(0,-2,0);
    }


    @Override
    public void tick() {

        if(System.currentTimeMillis()-starttime>dur()) discard();
        if(level().isClientSide){
            initForward = this.getEntityData().get(curRot);
        }else{
            //包围盒检测造成伤害
            var entities=level().getEntities(this,this.getBoundingBox());
            level().addParticle(ParticleRegistry.DRAGON_FIRE_PARTICLE.get(), xOld,yOld,zOld,getDeltaMovement().x,getDeltaMovement().y,getDeltaMovement().z);
            if(!entities.isEmpty()){

                for (var e:entities) {
                    if(e!=getOwner())
                        e.hurt(this.damageSources().mobProjectile(this, getOwner() instanceof LivingEntity livingentity ? livingentity : null), damage());
                }
            }

        }


        super.tick();
    }


    //弹幕设置
    @Override//取消射击惯性
    public void shootFromRotation(Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        float f = -Mth.sin(pY * (float) (Math.PI / 180.0)) * Mth.cos(pX * (float) (Math.PI / 180.0));
        float f1 = -Mth.sin((pX + pZ) * (float) (Math.PI / 180.0));
        float f2 = Mth.cos(pY * (float) (Math.PI / 180.0)) * Mth.cos(pX * (float) (Math.PI / 180.0));
        this.shoot((double)f, (double)f1, (double)f2, pVelocity, pInaccuracy);

    }


    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {

        if(!this.level().isClientSide()) {
            Entity entity1 = pResult.getEntity();
            Entity entity = this.getOwner();
            entity1.hurt(this.damageSources().mobProjectile(this, entity instanceof LivingEntity livingentity ? livingentity : null), damage());
        }
        super.onHitEntity(pResult);
    }

    @Nullable
    @Override//设置粒子效果
    protected ParticleOptions getTrailParticle() {
        return ParticleRegistry.DRAGON_FIRE_PARTICLE.get();
    }

    @Override
    protected boolean canHitEntity(@NotNull Entity pTarget) {
        return (pTarget != getOwner()) && super.canHitEntity(pTarget);
    }

    @Override//流体阻力
    protected float getLiquidInertia() {
        return 1;
    }

    @Override//火焰效果
    protected boolean shouldBurn() {
        return false;
    }
    @Override
    public boolean isPickable() {
        return false;
    }

    @Override//空气阻力
    protected float getInertia() {
        return 1;
    }




    //客户端数据交互

    private final long starttime = System.currentTimeMillis();
    private static final EntityDataAccessor<Vector3f> curRot = SynchedEntityData.defineId(tai_la_ren_wave.class, EntityDataSerializers.VECTOR3);


    //defineSynchedData()：该方法用于定义实体的同步数据，在该方法中，将COUNTER实体数据访问器初始化为0。
    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(curRot,new Vector3f());

    }
    public void syncRot(){
        if(!level().isClientSide){
            this.entityData.set(curRot,initForward);
        }
    }



/*
    //readAdditionalSaveData()：该方法用于从NBT标签中读取额外的保存数据，在该方法中，从NBT标签中读取计数器数据，并保存到实体数据中。
    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        ListTag listtag = pCompound.getList("power", 6);
        this.entityData.set(curPos,new Vector3f((float) listtag.getDouble(0), (float) listtag.getDouble(1), (float) listtag.getDouble(2)));

    }
    //addAdditionalSaveData()：该方法用于向NBT标签中添加额外的保存数据，在该方法中，将计数器数据保存到NBT标签中。
    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.put("power", this.newDoubleList(this.xPower, this.yPower, this.zPower));

    }
*/
}
