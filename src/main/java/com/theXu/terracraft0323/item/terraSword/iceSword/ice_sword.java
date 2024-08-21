package com.theXu.terracraft0323.item.terraSword.iceSword;


import com.theXu.terracraft0323.item.terraSword.terraria_sword_base;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ice_sword extends terraria_sword_base {
    private static final int damage = 10;

    public ice_sword() {
        super(damage);
        Player p;

    }

    @Override
    protected void genWaves(Level level, Player playerIn, InteractionHand handIn) {
        float xr = playerIn.getXRot();
        float yr = playerIn.getYRot();
        float zr = 0;
        ice_sword_wave wave = new ice_sword_wave(playerIn,level);
        wave.setPos(playerIn.position().add(new Vec3(0,1,0)));
        wave.shootFromRotation(playerIn,xr, yr, zr, 0.5F, 1f);

        level.addFreshEntity(wave);
    }

}
