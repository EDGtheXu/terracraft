package com.theXu.terracraft0323.item.terraSummon;

import com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan.kesuluzhiyan;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.UUID;

public class summons_base extends Monster {
    protected Entity owner;

    protected summons_base(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }



    //属性设置
    public boolean hurt(DamageSource source,float damage){
        return false;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.FLYING_SPEED, 0.2F)
                .add(Attributes.FOLLOW_RANGE, 200.0)
                .add(Attributes.ARMOR, 4.0)
                .add(Attributes.SAFE_FALL_DISTANCE,1020)
                .add(Attributes.ATTACK_DAMAGE,10);

    }

    //数据同步

    private static final EntityDataAccessor<Optional<UUID>> DATA_OWNER_UUID = SynchedEntityData.defineId(summons_base.class, EntityDataSerializers.OPTIONAL_UUID);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_OWNER_UUID, Optional.empty());

    }

    public void setOwner(Entity entity){
        owner = entity;
        this.entityData.set(DATA_OWNER_UUID,Optional.of(owner.getUUID()));
    }

    public Entity getOwner() {
        if(owner!=null) return owner;
        UUID id = this.entityData.get(DATA_OWNER_UUID).orElse(null);
        if (id != null) {
            owner = this.level().getPlayerByUUID(id);
        }
        return owner;
    }


}
