package com.theXu.terracraft0323.item.custom;

import com.theXu.terracraft0323.item.ModFoods;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class BreadSwordHotItem extends SwordItem {

    public BreadSwordHotItem(){
        super(Tiers.STONE,new Properties().food(ModFoods.BREAD_SWORD_HOT));
    }
}
