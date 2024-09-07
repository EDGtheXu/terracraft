package com.theXu.terracraft0323.item;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.item.tiers.modArmorMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModTiers {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, NeoMod.MODID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> WOLF_ARMOR_MATERIAL = ARMOR_MATERIALS.register("wolf", ModTiers::dummyArmorMaterial);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GECKO_ARMOR_MATERIAL = ARMOR_MATERIALS.register("gecko", ModTiers::dummyArmorMaterial);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SHEN_SHENG_MATERIAL = ARMOR_MATERIALS.register("shen_sheng",  ModTiers::shenShengArmorMaterial );



    private static ArmorMaterial dummyArmorMaterial() {
        ArmorMaterial diamond = ArmorMaterials.DIAMOND.value();
        return new ArmorMaterial(diamond.defense(), diamond.enchantmentValue(), diamond.equipSound(), diamond.repairIngredient(), List.of(), diamond.toughness(), diamond.knockbackResistance());
    }

    private static ArmorMaterial shenShengArmorMaterial() {
        ArmorMaterial shenSheng = modArmorMaterials.SHEN_SHENG.value();
        return new ArmorMaterial(shenSheng.defense(), shenSheng.enchantmentValue(), shenSheng.equipSound(), shenSheng.repairIngredient(), List.of(), shenSheng.toughness(), shenSheng.knockbackResistance());
    }
}