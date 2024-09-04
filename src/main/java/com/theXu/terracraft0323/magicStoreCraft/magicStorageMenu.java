package com.theXu.terracraft0323.magicStoreCraft;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.mojang.datafixers.util.Pair;
import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.item.terraJewelry.jewelryItem;
import com.theXu.terracraft0323.network.packet.terraCraftPacket.serverSavePacket;
import com.theXu.terracraft0323.tag.ModTags;
import com.theXu.terracraft0323.ui.jewelrySlots.jewelryInventorySaver;
import com.theXu.terracraft0323.ui.jewelrySlots.terraAmorSlot;
import com.theXu.terracraft0323.ui.jewelrySlots.terraEquipmentSlot;
import com.theXu.terracraft0323.ui.modMenuType;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Map;

public class magicStorageMenu extends AbstractContainerMenu {
    public static final int CONTAINER_ID = 0;
    public static final int RESULT_SLOT = 0;
    public static final int CRAFT_SLOT_START = 1;
    public static final int CRAFT_SLOT_COUNT = 4;
    public static final int CRAFT_SLOT_END = 5;
    public static final int ARMOR_SLOT_START = 5;
    public static final int ARMOR_SLOT_COUNT = 4;
    public static final int ARMOR_SLOT_END = 9;
    public static final int INV_SLOT_START = 9;
    public static final int INV_SLOT_END = 36;
    public static final int USE_ROW_SLOT_START = 36;
    public static final int USE_ROW_SLOT_END = 45;
    public static final int SHIELD_SLOT = 45;
    public static final ResourceLocation BLOCK_ATLAS = ResourceLocation.withDefaultNamespace("textures/atlas/blocks.png");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_SHIELD = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_shield");
    private static final Map<EquipmentSlot, ResourceLocation> TEXTURE_EMPTY_SLOTS;
    private static final EquipmentSlot[] SLOT_IDS;
    private static final terraEquipmentSlot[] SUB_SLOT_IDS;
    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 2, 2);
    int vcount = 20;
    private final CraftingContainer terraCraftSlots = new TransientCraftingContainer(this, 1, vcount+9);
    private final ResultContainer resultSlots = new ResultContainer();
    public Player owner ;
    public static jewelryInventorySaver jis;//饰品栏空间

    public magicStorageMenu(int pContainerId, Inventory inv) {
        this(pContainerId,inv, new SimpleContainerData(27));


    }

    public magicStorageMenu(int pContainerId, Inventory playerInventory, ContainerData data) {
        super(modMenuType.TERRA_MENU.get(), pContainerId);

//        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 154, 28));

        //owner.sendSystemMessage(Component.literal("message"));
        int i1;
        int j1;

        owner =  Minecraft.getInstance().player;
//        for(i1 = 0; i1 < 2; ++i1) {
//            for(j1 = 0; j1 < 2; ++j1) {
//                this.addSlot(new Slot(this.craftSlots, j1 + i1 * 2, 98 + j1 * 18, 18 + i1 * 18));
//            }
//        }

        //装备栏
        for(i1 = 0; i1 < 4; ++i1) {
            EquipmentSlot equipmentslot = SLOT_IDS[i1];
            ResourceLocation resourcelocation = (ResourceLocation)TEXTURE_EMPTY_SLOTS.get(equipmentslot);
            this.addSlot(new terraAmorSlot(playerInventory, owner, equipmentslot, 39 - i1, 8, 8 + i1 * 18, resourcelocation){

            });

        }

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


        //物品栏
        for(i1 = 0; i1 < 3; ++i1) {
            for(j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + (i1 + 1) * 9, 8 + j1 * 18, 84 + i1 * 18));
            }
        }

        //快捷键栏
        for(i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
        }

        //副手栏
        this.addSlot(new Slot(playerInventory, 40, 77, 62) {
            public void setByPlayer(ItemStack p_270969_, ItemStack p_299918_) {
                owner.onEquipItem(EquipmentSlot.OFFHAND, p_299918_, p_270969_);
                super.setByPlayer(p_270969_, p_299918_);
            }

            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(BLOCK_ATLAS, EMPTY_ARMOR_SLOT_SHIELD);
            }
        });

        //制作栏
/*
        List<ItemStack> results = new ArrayList<>();
        Map<ItemStack, List<Ingredient>> map = terraRecipe.getInstance();
        map.forEach((k,v)-> results.add(k));

        int internal = 18;

        int craftx = -130;
        int crafty = -25;


        int currentIndex = 2;
        int craftIndex = (currentIndex + vcount)/2;

        int resultx = craftx + internal + 5;
        int resulty = crafty + craftIndex * internal;




        for(int i=0;i<vcount;i++){
            Slot slot = addSlot(new SlotItemHandler(new ItemStackHandler(vcount){

            },i,craftx,crafty + internal * i));
            slot.set(results.get(currentIndex+i));
        }
        var ings = map.get(results.get(craftIndex));
        System.out.println(ings.size());
        for(int i=0;i<ings.size();i++){
            Slot slot = addSlot(new SlotItemHandler(new ItemStackHandler(9) {
                @Override//槽位不可存取
                public boolean isItemValid(int slot, ItemStack stack) {
                    return false;
                }

                @Override
                public ItemStack getStackInSlot(int slot) {
                    return ItemStack.EMPTY;
                }
            },i,resultx + internal * i, resulty));
            slot.set(ings.get(i).getItems()[0]);
        }

*/

    }


    public static void tick(Player owner) {
        if(jis == null) return;
        int slot = jis.itemHandler.getSlots();
        int count = 0;
        if (abilityRegister.get() != null) {
            abilityRegister.get().canFly = false;
        }
        for (int i=0;i<slot ;i++) {
            ItemStack item = jis.itemHandler.getStackInSlot(i);
            if(item.is(ModTags.Items.JEWELRY)){
                ((jewelryItem)item.getItem()).jewelryTick(owner);
            }

            count++;
        }
    }


    public static boolean isHotbarSlot(int index) {
        return index >= 36 && index < 45 || index == 45;
    }

    public void fillCraftSlotsStackedContents(StackedContents itemHelper) {
        this.craftSlots.fillStackedContents(itemHelper);
    }

    public void clearCraftingContent() {
        this.resultSlots.clearContent();
        this.craftSlots.clearContent();
    }

    public boolean recipeMatches(RecipeHolder<CraftingRecipe> recipe) {
        return ((CraftingRecipe)recipe.value()).matches(this.craftSlots.asCraftInput(), this.owner.level());
    }



    public void slotsChanged(Container inventory) {
        super.slotsChanged(inventory);
        //CraftingMenu.slotChangedCraftingGrid(this, this.owner.level(), this.owner, this.craftSlots, this.resultSlots, (RecipeHolder)null);
    }


    public void removed(Player player) {
        super.removed(player);
        this.resultSlots.clearContent();
        if (!player.level().isClientSide) {
            this.clearContainer(player, this.craftSlots);
        }

    }

    public boolean stillValid(Player player) {
        return true;
    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            EquipmentSlot equipmentslot = player.getEquipmentSlotForItem(itemstack);
            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >= 1 && index < 5) {
                if (!this.moveItemStackTo(itemstack1, 9, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 5 && index < 9) {
                if (!this.moveItemStackTo(itemstack1, 9, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentslot.getType() == Type.HUMANOID_ARMOR && !((Slot)this.slots.get(8 - equipmentslot.getIndex())).hasItem()) {
                int i = 8 - equipmentslot.getIndex();
                if (!this.moveItemStackTo(itemstack1, i, i + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentslot == EquipmentSlot.OFFHAND && !((Slot)this.slots.get(45)).hasItem()) {
                if (!this.moveItemStackTo(itemstack1, 45, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 9 && index < 36) {

                if(Minecraft.getInstance().isSameThread()) {
                    PacketDistributor.sendToServer(new serverSavePacket(index));

                }
                return ItemStack.EMPTY;
                /*
                if (!this.moveItemStackTo(itemstack1, 36, 45, false)) {
                    return ItemStack.EMPTY;
                }
                */
            } else if (index >= 36 && index < 42+7) {
                if (!this.moveItemStackTo(itemstack1, 9, 36, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 9, 45, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY, itemstack);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            if (index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    public int getResultSlotIndex() {
        return 0;
    }

    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    public int getSize() {
        return 5;
    }

    public CraftingContainer getCraftSlots() {
        return this.craftSlots;
    }

    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    public boolean shouldMoveToInventory(int slotIndex) {
        return slotIndex != this.getResultSlotIndex();
    }



    static {
        TEXTURE_EMPTY_SLOTS = Map.of(
                EquipmentSlot.FEET, EMPTY_ARMOR_SLOT_BOOTS,
                EquipmentSlot.LEGS, EMPTY_ARMOR_SLOT_LEGGINGS,
                EquipmentSlot.CHEST, EMPTY_ARMOR_SLOT_CHESTPLATE,
                EquipmentSlot.HEAD, EMPTY_ARMOR_SLOT_HELMET

        );
        SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        SUB_SLOT_IDS = new terraEquipmentSlot[]{terraEquipmentSlot.SUB1,terraEquipmentSlot.SUB1,terraEquipmentSlot.SUB1,terraEquipmentSlot.SUB1,terraEquipmentSlot.SUB1,terraEquipmentSlot.SUB1,terraEquipmentSlot.SUB1};
    }
}
