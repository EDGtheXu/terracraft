package com.theXu.terracraft0323.item.tiers;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class modTiers implements Tier {


    @Override
    public int getUses() {
        return 0;
    }

    @Override
    public float getSpeed() {
        return 50;
    }

    @Override
    public float getAttackDamageBonus() {
        return 50;
    }

    @Override
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return getIncorrectBlocksForDrops();
    }


    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return null;
    }
}
