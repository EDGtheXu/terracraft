package com.theXu.terracraft0323.mixin.terraBag;

import com.theXu.terracraft0323.slot.jewelrySlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
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
            addSlot(new jewelrySlot(playerInventory,ii1 + 41,180,8+ii1*18,
                    owner,this,41
                    ){
                /*
                @Override
                public void set(ItemStack stack) {
                    owner.getAttributes().getSyncableAttributes().forEach(
                            a->{
                                stack.getAttributeModifiers().modifiers().forEach((k)->{
                                    if(k.attribute() == a.getAttribute())
                                        a.addOrUpdateTransientModifier(k.modifier());
                                });
                            }
                    );
                    super.set(stack);
                }
                @Override
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()  {
                    return Pair.of(
                            ResourceLocation.withDefaultNamespace("textures/atlas/blocks.png"),
                            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_shield"));
                }
                @Override
                public boolean mayPlace(ItemStack stack) {
                    boolean res = stack.getTags().toList().contains(ModTags.Items.JEWELRY);
                    if(res){
                        //不能装备相同的饰品
                        for(int i = 41; i < 41 + 7; i++){
                            if(slots.get(i).getItem().is(stack.getItem()))
                                return false;
                        }
                    }
                    return res;
                }

                @Override
                public void setByPlayer(ItemStack newStack, ItemStack oldStack) {
                    owner.getAttributes().getSyncableAttributes().forEach(
                            a->{
                                oldStack.getAttributeModifiers().modifiers().forEach((k)->{
                                    if(k.attribute() == a.getAttribute())
                                        a.removeModifier(k.modifier().id());
                                });
                            }
                    );
                    super.setByPlayer(newStack, oldStack);
                }

                public int getMaxStackSize() {
                    return 1;
                }
                */
            });
        }
    }

}
