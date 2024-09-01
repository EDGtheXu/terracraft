package com.theXu.terracraft0323.mixin.terraBag;

import com.mojang.datafixers.util.Pair;
import com.theXu.terracraft0323.tag.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenu.class)
public abstract class inventoryMenuMixin extends RecipeBookMenu<CraftingInput, CraftingRecipe> {


    public inventoryMenuMixin(Inventory playerInventory, boolean active, final Player owner) {super((MenuType)null, 0);}

    @Inject(at = @At(value = "TAIL"), method = "<init>")
    private void init(Inventory playerInventory, boolean active, Player owner, CallbackInfo ci) {

        //饰品栏

        for(int ii1 = 0; ii1 < 7; ++ii1) {
            addSlot(new Slot(playerInventory,ii1 + 41,180,8+ii1*18){
                @Override
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()  {
                    return Pair.of(ResourceLocation.withDefaultNamespace("textures/atlas/blocks.png"),ResourceLocation.withDefaultNamespace("item/empty_armor_slot_shield"));
                }
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.getTags().toList().contains(ModTags.Items.JEWELRY);
                }
            });


        }
    }







}
