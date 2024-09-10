package com.theXu.terracraft0323.item.tiers;

import com.google.common.base.Suppliers;
import com.theXu.terracraft0323.item.ModItems;
import com.theXu.terracraft0323.tag.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum modTier implements Tier {

    YU_YAN(ModTags.Blocks.INCORRECT_FOR_YU_YAN_TOOL, 2000, 10.0F, 8.0F, 20, () -> Ingredient.of(ModItems.YU_YAN_DING)),
    BA_JIN(ModTags.Blocks.INCORRECT_FOR_BA_JIN_TOOL, 2000, 10.0F, 8.0F, 20, () -> Ingredient.of(ModItems.BA_JIN_DING)),
    SHEN_SHENG_DING(ModTags.Blocks.INCORRECT_FOR_SHEN_SHENG_TOOL, 2000, 10.0F, 8.0F, 20, () -> Ingredient.of(ModItems.SHEN_SHENG_DING)),
    YE_LV(ModTags.Blocks.INCORRECT_FOR_YE_LV_TOOL, 2000, 10.0F, 8.0F, 20, () -> Ingredient.of(ModItems.YE_LV_DING));



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
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }


}
