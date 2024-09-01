package com.theXu.terracraft0323.mixin.terraBag;

import com.google.common.collect.ImmutableList;
import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.Container;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Inventory.class)
public abstract class inventoryMixin implements Container, Nameable {
    @Mutable
    @Shadow @Final
    private List<NonNullList<ItemStack>> compartments;
    @Shadow @Final
    public  Player player;
    @Shadow @Final
    public NonNullList<ItemStack> items;
    @Shadow @Final
    public NonNullList<ItemStack> armor;
    @Shadow @Final
    public NonNullList<ItemStack> offhand;

    public final NonNullList<ItemStack> jewelry = NonNullList.withSize(7, ItemStack.EMPTY);

    public inventoryMixin(Player player) { }





    @Inject(at = @At(value = "TAIL"), method = "<init>")
    private void init(Player player, CallbackInfo ci){


        compartments = ImmutableList.of(this.items, this.armor, this.offhand,this.jewelry);
    }


    @Inject(method = "save", at = @At(value = "TAIL"))
    public void saveinit(ListTag listTag, CallbackInfoReturnable<ListTag> cir) {
        int k;
        CompoundTag compoundtag2;
        for(k = 0; k < this.jewelry.size(); ++k) {
            if (!((ItemStack)this.jewelry.get(k)).isEmpty()) {
                compoundtag2 = new CompoundTag();
                compoundtag2.putByte("Slot", (byte)(k +200));
                listTag.add(((ItemStack)this.jewelry.get(k)).save(this.player.registryAccess(), compoundtag2));
            }
        }

    }

    /**
     * @author thexu
     * @reason addJewelry
     */

    @Overwrite
    public void load(ListTag listTag) {

        this.items.clear();
        this.armor.clear();
        this.offhand.clear();
        this.jewelry.clear();

        for(int i = 0; i < listTag.size(); ++i) {
            CompoundTag compoundtag = listTag.getCompound(i);
            int j = compoundtag.getByte("Slot") & 255;
            ItemStack itemstack = (ItemStack)ItemStack.parse(this.player.registryAccess(), compoundtag).orElse(ItemStack.EMPTY);

            if (j >= 0 && j < this.items.size()) {
                this.items.set(j, itemstack);
            } else if (j >= 100 && j < this.armor.size() + 100) {
                this.armor.set(j - 100, itemstack);
            } else if (j >= 150 && j < this.offhand.size() + 150) {
                this.offhand.set(j - 150, itemstack);
            } else if (j >= 200 && j < this.jewelry.size() + 200) {
                this.jewelry.set(j - 200, itemstack);
            }
        }
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void initTick(CallbackInfo ci) {
        if (abilityRegister.get() != null) {
            abilityRegister.get().canFly = false;
        }
    }


}
