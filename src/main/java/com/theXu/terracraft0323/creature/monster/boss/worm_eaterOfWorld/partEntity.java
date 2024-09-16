package com.theXu.terracraft0323.creature.monster.boss.worm_eaterOfWorld;

import com.theXu.terracraft0323.creature.monster.boss.terraBossBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.neoforged.neoforge.entity.PartEntity;

public class partEntity  extends PartEntity<terraBossBase> {

    public final Entity parentEntity;
    public final String name;
    private final EntityDimensions size;
    private final boolean hasCollision;

    public partEntity(terraBossBase shieldEntity, String name, float scaleX, float scaleY, boolean hasCollision) {
        super(shieldEntity);
        this.size = EntityDimensions.scalable(scaleX, scaleY);
        this.refreshDimensions();
        this.parentEntity = shieldEntity;
        this.name = name;
        this.hasCollision = hasCollision;

    }



    @Override
    public boolean canBeCollidedWith() {
        return hasCollision;
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }



    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        this.parentEntity.hurt(pSource,pAmount);
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public boolean is(Entity entity) {
        return this == entity || this.parentEntity == entity;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.size;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}