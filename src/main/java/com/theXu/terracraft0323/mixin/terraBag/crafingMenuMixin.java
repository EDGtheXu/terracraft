package com.theXu.terracraft0323.mixin.terraBag;

import com.theXu.terracraft0323.slot.jewelrySlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(CraftingMenu.class)
public abstract class crafingMenuMixin extends RecipeBookMenu<CraftingInput, CraftingRecipe> {


    public crafingMenuMixin(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(MenuType.CRAFTING, containerId);
        }

    @Inject(at = @At(value = "TAIL"), method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V")
    private void init(int containerId, Inventory playerInventory, ContainerLevelAccess access, CallbackInfo ci){

        //饰品栏
        for(int ii1 = 0; ii1 < 7; ++ii1) {
            addSlot(new jewelrySlot(playerInventory,ii1 + 41,180,8+ii1*18,
                    playerInventory.player, this,41
                    ){
                /*
                @Override
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()  {
                    return Pair.of(ResourceLocation.withDefaultNamespace("textures/atlas/blocks.png"),ResourceLocation.withDefaultNamespace("item/empty_armor_slot_shield"));
                }
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.getTags().toList().contains(ModTags.Items.JEWELRY);
                }
                */
            });


        }
    }







}
