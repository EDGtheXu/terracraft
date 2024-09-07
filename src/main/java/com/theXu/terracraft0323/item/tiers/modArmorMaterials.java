package com.theXu.terracraft0323.item.tiers;

import com.theXu.terracraft0323.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class modArmorMaterials {

//    public static final Holder<ArmorMaterial> NETHERITE;
    public static final Holder<ArmorMaterial> SHEN_SHENG;


    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace(name)));
        return register(name, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngridient, List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap(ArmorItem.Type.class);
        ArmorItem.Type[] var9 = ArmorItem.Type.values();
        int var10 = var9.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            ArmorItem.Type armoritem$type = var9[var11];
            enummap.put(armoritem$type, (Integer)defense.get(armoritem$type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.withDefaultNamespace(name), new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngridient, layers, toughness, knockbackResistance));
    }

    static {
 /*
        NETHERITE = register("netherite", Util.make(new EnumMap(ArmorItem.Type.class), (p_323379_) -> {
            p_323379_.put(ArmorItem.Type.BOOTS, 3);
            p_323379_.put(ArmorItem.Type.LEGGINGS, 6);
            p_323379_.put(ArmorItem.Type.CHESTPLATE, 8);
            p_323379_.put(ArmorItem.Type.HELMET, 3);
            p_323379_.put(ArmorItem.Type.BODY, 11);
        }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F,
                () -> Ingredient.of(Items.NETHERITE_INGOT));
*/

        SHEN_SHENG = register("netherite", Util.make(new EnumMap(ArmorItem.Type.class), (p_323379_) -> {
                    p_323379_.put(ArmorItem.Type.BOOTS, 6);
                    p_323379_.put(ArmorItem.Type.LEGGINGS, 9);
                    p_323379_.put(ArmorItem.Type.CHESTPLATE, 11);
                    p_323379_.put(ArmorItem.Type.HELMET, 6);
                    p_323379_.put(ArmorItem.Type.BODY, 14);
                }), 18, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F,
                () -> Ingredient.of(ModItems.SHEN_SHENG_DING));
    }


}