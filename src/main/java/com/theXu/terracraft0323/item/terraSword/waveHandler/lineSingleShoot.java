package com.theXu.terracraft0323.item.terraSword.waveHandler;


import com.theXu.terracraft0323.item.terraSword.tailaren.tai_la_ren_wave;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class lineSingleShoot extends empty{

    float forwardSpeed;
    public lineSingleShoot(Player player, Level pLevel, ArrayList<Entity> waves, int dur, float forwardSpeed) {
        super(player, pLevel, waves, dur);
        setPos(player.position());
        this.forwardSpeed = forwardSpeed;

        for (var wave:waves
             ) {
            ((tai_la_ren_wave)wave).shootFromRotation(player,player.xRotO,360-player.yRotO,0,1,0);
        }
    }

    @Override
    public void tick() {
        super.tick();

        for (var wave:waves) {
            var n = wave.getForward().normalize().scale(forwardSpeed/20).add(wave.position());

            //wave.moveTo(n);
            //wave.move(MoverType.SELF,nextMove());
            //System.out.println("moveone");
            //System.out.println(wave.position());

            //wave.moveTo(n);

            //((tai_la_ren_waveModelEntity)wave).syncPos();
            //System.out.println(wave.position());
        }

    }
}
