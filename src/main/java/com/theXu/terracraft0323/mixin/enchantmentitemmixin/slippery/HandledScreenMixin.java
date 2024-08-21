package com.theXu.terracraft0323.mixin.enchantmentitemmixin.slippery;

import com.theXu.terracraft0323.enchantment.ModEnchantments;
import com.theXu.terracraft0323.mixinhelper.InjectHelper;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(AbstractContainerScreen.class)
public abstract class HandledScreenMixin{
	@Shadow
	@Nullable
	protected Slot hoveredSlot;

	@Inject(at = @At(value = "HEAD"), method = "checkHotbarKeyPressed",cancellable = true)
	private void init(int keyCode, int scanCode, CallbackInfoReturnable<Boolean> cir){
		Slot slot1 = this.hoveredSlot;
		if(slot1 != null) {
			ItemStack itemStack = slot1.getItem();
			if (InjectHelper.getEnchantmentLevel(itemStack, ModEnchantments.SLIPPERY) > 0) {
				cir.cancel();
			}
		}
	}
}