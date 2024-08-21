package com.theXu.terracraft0323.ui.jewelrySlots;

import net.minecraft.world.entity.EquipmentSlot;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import javax.annotation.Nullable;

public class terraAmorSlot extends Slot {
    private final LivingEntity owner;
    private final EquipmentSlot slot;
    @Nullable
    private final ResourceLocation emptyIcon;

    public terraAmorSlot(Container container, LivingEntity owner, EquipmentSlot slot, int slotIndex, int x, int y, @Nullable ResourceLocation emptyIcon) {
        super(container, slotIndex, x, y);
        this.owner = owner;
        this.slot = slot;
        this.emptyIcon = emptyIcon;
    }

    public void setByPlayer(ItemStack newStack, ItemStack oldStack) {
        this.owner.onEquipItem(this.slot, oldStack, newStack);
        super.setByPlayer(newStack, oldStack);
    }

    public int getMaxStackSize() {
        return 1;
    }

    public boolean mayPlace(ItemStack stack) {
        return stack.canEquip(this.slot, this.owner);
    }

    public boolean mayPickup(Player player) {
        ItemStack itemstack = this.getItem();
        return !itemstack.isEmpty() && !player.isCreative() && EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) ? false : super.mayPickup(player);
    }

    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
        return this.emptyIcon != null ? Pair.of(InventoryMenu.BLOCK_ATLAS, this.emptyIcon) : super.getNoItemIcon();
    }
}
