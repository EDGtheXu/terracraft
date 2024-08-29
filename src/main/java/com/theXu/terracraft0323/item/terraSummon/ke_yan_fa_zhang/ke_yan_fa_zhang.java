package com.theXu.terracraft0323.item.terraSummon.ke_yan_fa_zhang;

import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.item.terraSummon.summons_base;
import com.theXu.terracraft0323.item.terraSummon.terra_summon_base;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ke_yan_fa_zhang extends terra_summon_base {
    public ke_yan_fa_zhang() {
        super(new Properties());
    }

    @Override
    protected int getMagicConsume() {
        return 10;
    }

    @Override
    protected int getDamage() {
        return 10;
    }

    @Override
    protected void summon(Level level, Player owner) {
        var summons = new ke_yan_fa_zhang_summon(ModEntities.KE_YAN_SUMMON.get(), level) ;
        level.addFreshEntity(summons);
        summons.setOwner(owner);

    }





}
