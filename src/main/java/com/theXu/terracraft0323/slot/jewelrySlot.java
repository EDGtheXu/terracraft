package com.theXu.terracraft0323.slot;

import com.mojang.datafixers.util.Pair;
import com.theXu.terracraft0323.tag.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class jewelrySlot extends Slot {
    public Player owner;
    private int startIndex ;
    public AbstractContainerMenu menu;

    public jewelrySlot(Container container, int slot, int x, int y, Player owner, AbstractContainerMenu menu,int startIndex) {
        super(container, slot, x, y);

        this.owner = owner;
        this.menu = menu;

        this.startIndex = startIndex;
    }


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
            for(int i = startIndex; i < startIndex + 7; i++){
                if(menu.slots.get(i).getItem().is(stack.getItem()))
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
}
