package com.theXu.terracraft0323.item.tiers;

import com.google.common.base.Suppliers;
import com.theXu.terracraft0323.item.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum modTier implements Tier {


    SHEN_SHENG_DING(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2000, 10.0F, 8.0F, 20, () -> Ingredient.of(ModItems.SHEN_SHENG_DING));



    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;


    private modTier(TagKey<Block> incorrectBlockForDrops, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlockForDrops;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }


    @Override
    public int getUses() {
        return 0;
    }

    @Override
    public float getSpeed() {
        return 10;
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
