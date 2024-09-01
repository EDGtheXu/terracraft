package com.theXu.terracraft0323.item.terraSword.tailaren;

import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.item.terraSword.terraria_sword_base;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

import java.awt.*;
import java.util.List;
import java.util.Optional;


public class tai_la_ren extends terraria_sword_base {
    private static final int damage = 50;
    private static final float speed = 50;
    private static final float forwardSpeed = 1;
    public tai_la_ren() {
        super(damage);
    }



    @Override
    protected void genWaves(Level level, Player playerIn, InteractionHand handIn) {

        Vector3f v3f = new Vector3f(playerIn.getXRot(),playerIn.getYRot(),0);

        //var wave = new tai_la_ren_waveModelEntity(playerIn,level,v3f);
        var wave = new tai_la_ren_wave(playerIn,level,v3f);
        wave.moveTo(playerIn.position().add(0,1,0));
        wave.setOwner(playerIn);

        wave.shootFromRotation(playerIn,playerIn.xRotO,playerIn.yRotO,0,forwardSpeed,0);

        level.addFreshEntity(wave);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
        componentList.add(Component.literal(""));
        componentList.add(Component.literal("泰拉瑞亚英雄之刃")
                .withStyle(Style.EMPTY.withColor(
                        Color.red.getRGB())
                        .withObfuscated(true)
                ));
        componentList.add(Component.literal("英雄之刃")
                .withStyle(Style.EMPTY.withColor(
                                Color.red.getRGB())
                        .withBold(true)
                ));


    }

    //初始附魔
    public ItemStack createStack(){
        ItemStack s = new ItemStack(this);

        Optional<HolderLookup.RegistryLookup<Enchantment>> lookup = null;
        Level level = Minecraft.getInstance().level;
        if ( level != null) {
            lookup = Minecraft.getInstance().level.registryAccess().lookup(Registries.ENCHANTMENT);
            HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = lookup.get();
            //List<Holder.Reference<Enchantment>> NEGATIVE_ENCHANTMENTS = enchantmentRegistryLookup.listElements().filter(enchantmentReference -> enchantmentReference.is(EnchantmentTags.CURSE)).toList();

            var op = enchantmentRegistryLookup.get(Enchantments.SHARPNESS);
            if(op.isPresent()){
                var enchant = op.get().getDelegate();
                s.enchant(enchant,10);
            }

            op = enchantmentRegistryLookup.get(Enchantments.QUICK_CHARGE);
            if(op.isPresent()){
                var enchant = op.get().getDelegate();
                s.enchant(enchant,10);
            }
        }




        return s;
    }

    public void onCraftedBy(ItemStack s, Level level, Player player){
        Optional<HolderLookup.RegistryLookup<Enchantment>> lookup = null;
        if ( level != null) {
            lookup = level.registryAccess().lookup(Registries.ENCHANTMENT);
            HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = lookup.get();
            //List<Holder.Reference<Enchantment>> NEGATIVE_ENCHANTMENTS = enchantmentRegistryLookup.listElements().filter(enchantmentReference -> enchantmentReference.is(EnchantmentTags.CURSE)).toList();

            var op = enchantmentRegistryLookup.get(Enchantments.SHARPNESS);
            if(op.isPresent()){
                var enchant = op.get().getDelegate();
                s.enchant(enchant,10);
            }

            op = enchantmentRegistryLookup.get(Enchantments.QUICK_CHARGE);
            if(op.isPresent()){
                var enchant = op.get().getDelegate();
                s.enchant(enchant,10);
            }
        }
    }

    //应用手持药水效果
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

        if(pIsSelected) {
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(ModEffects.ICE_FROZEN, 25));
//            ((LivingEntity) pEntity).getAttribute(Attributes.MOVEMENT_SPEED)
//                    .addOrReplacePermanentModifier(new AttributeModifier(ResourceLocation.fromNamespaceAndPath(NeoMafishMod.MODID, "replace"), 1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.ABSORPTION,25));
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,25,2));


        }



        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }




}
