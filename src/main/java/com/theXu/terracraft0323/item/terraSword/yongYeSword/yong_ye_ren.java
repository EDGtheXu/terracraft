package com.theXu.terracraft0323.item.terraSword.yongYeSword;

import com.theXu.terracraft0323.item.terraSword.terraria_sword_base;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class yong_ye_ren extends terraria_sword_base {

    private static final int damage = 20;


    public yong_ye_ren() {
        super(damage);
    }

    @Override
    protected void genWaves(Level level, Player playerIn, InteractionHand handIn) {
        yong_ye_ren_wave wave = new yong_ye_ren_wave(playerIn,level);
        wave.setPos(playerIn.position());
        level.addFreshEntity(wave);
    }
/*
    @Override
    protected ArrayList<Entity> shootWave(Level level, Player playerIn, InteractionHand handIn){

        ArrayList<Entity>waves = new ArrayList<>();
        float xr = playerIn.getXRot();
        float yr = playerIn.getYRot();
        float zr = 0;
        float targetx = xr+30;

        for(float x=xr-30;x<=targetx;x+=1.5){
            for(float y=yr-5;y<=yr+5;y+=1){
                tai_la_ren_wave wave = new tai_la_ren_wave(playerIn,level,50);
                wave.shootFromRotation(playerIn,x, y, zr, 2.0F, 1.0f);
                level.addFreshEntity(wave);
                waves.add(wave);
            }
        }
        return waves;
    }*/
}
