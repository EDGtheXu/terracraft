package com.theXu.terracraft0323.item.terraSword.waveHandler;


import com.theXu.terracraft0323.entity.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class empty extends AbstractHurtingProjectile {
    public ArrayList<Entity>waves;
    public int dur;
    public long startTime;
    public Vec3 forward;
    public empty(Player player, Level pLevel, ArrayList<Entity>waves, int dur) {
        super(ModEntities.EMPTY_ENTITY.get(), pLevel);
        startTime = System.currentTimeMillis();
        this.waves = waves;
        this.dur = dur;
        setOwner(player);

    }

    public empty(EntityType<empty> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    @Override
    public void tick() {
        super.tick();

        if(waves==null){
            discard();
            return;
        }

        if (!level().isClientSide()&& System.currentTimeMillis() - startTime >= dur) {
            for (Entity wave : waves) wave.discard();
            discard();
        }
    }
    public Vec3 nextMove(){
        return new Vec3(100,100,100);
    };

}
