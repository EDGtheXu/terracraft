package com.theXu.terracraft0323.item.terraBow.shenShengLianNu;

import com.theXu.terracraft0323.item.terraBow.terraria_bow_base;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class shen_sheng_lian_nu extends terraria_bow_base {

    public shen_sheng_lian_nu(int damage) {
        super(damage);
    }





    @Override
    public int getAttackInternal() {
        return 300;
    }

    @Override
    protected void genWaves(Level level, Player playerIn, InteractionHand handIn) {

        ItemStack it = playerIn.getItemInHand(handIn);
        List<ItemStack> list = draw(it, playerIn.getProjectile(it), playerIn);
        if (!list.isEmpty()) {
            playerIn.getItemInHand(handIn).set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));

            ChargingSounds crossbowitem$chargingsounds = this.getChargingSounds(it);
            crossbowitem$chargingsounds.end()
                    .ifPresent(
                            p_352852_ -> level.playSound(
                                    null,
                                    playerIn.getX(),
                                    playerIn.getY(),
                                    playerIn.getZ(),
                                    p_352852_.value(),
                                    playerIn.getSoundSource(),
                                    1.0F,
                                    1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F
                            )
                    );



        }
    }

    @Override
    //初始附魔
    public ItemStack createStack(){
        ItemStack s = getDefaultInstance();

        Optional<HolderLookup.RegistryLookup<Enchantment>> lookup = null;
        Level level = Minecraft.getInstance().level;
        if ( level != null) {
            lookup = Minecraft.getInstance().level.registryAccess().lookup(Registries.ENCHANTMENT);
            HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = lookup.get();
            //List<Holder.Reference<Enchantment>> NEGATIVE_ENCHANTMENTS = enchantmentRegistryLookup.listElements().filter(enchantmentReference -> enchantmentReference.is(EnchantmentTags.CURSE)).toList();

            var op = enchantmentRegistryLookup.get(Enchantments.POWER);
            if(op.isPresent()){
                var enchant = op.get().getDelegate();
                s.enchant(enchant,(damage-6)/2);
            }

            op = enchantmentRegistryLookup.get(Enchantments.QUICK_CHARGE);
            if(op.isPresent()){
                var enchant = op.get().getDelegate();
                s.enchant(enchant,10);
            }

        }
        return s;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
        componentList.add(Component.literal("武器伤害 "+ damage).withColor(Color.pink.getRGB()));
        componentList.add(Component.literal("射速 "+ getAttackInternal()).withColor(Color.CYAN.getRGB()));
    }

}
