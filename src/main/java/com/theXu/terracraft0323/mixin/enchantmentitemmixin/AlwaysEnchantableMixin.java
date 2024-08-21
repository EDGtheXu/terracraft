package com.theXu.terracraft0323.mixin.enchantmentitemmixin;

import com.theXu.terracraft0323.Config;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.class)
public class AlwaysEnchantableMixin {


	@Shadow
	@Final
	private Enchantment.EnchantmentDefinition definition;

	/**
	 * @author
	 * Mafuyu33
	 * @reason
	 * Always Enchantable.
	 */
	@Overwrite
	public boolean canEnchant(ItemStack stack) {
		if(Config.isAlwaysEnchantable()) {
			return true;
		}else {
			return this.definition.supportedItems().contains(stack.getItemHolder());
		}
	}
}