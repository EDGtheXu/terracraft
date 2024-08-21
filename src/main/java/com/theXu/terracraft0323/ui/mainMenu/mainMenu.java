package com.theXu.terracraft0323.ui.mainMenu;


import com.theXu.terracraft0323.ServerManager;
import com.theXu.terracraft0323.item.ModItems;
import com.theXu.terracraft0323.ui.modMenuType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class mainMenu extends AbstractContainerMenu {
    private final ContainerData data;

    public mainMenuInventorySaver mit;


    //客户端构造
    public mainMenu(int pContainerId, Inventory inv) {
        this(pContainerId,inv, new SimpleContainerData(27));


    }


//服务端构造
    public mainMenu(int pContainerId, Inventory inv,  ContainerData data) {
        //构造这是使用到了MenuType和EntitiyType一样要是对Menu的注册，我们之后注册讲
        super(modMenuType.MAIN_MENU.get(), pContainerId);
        // 检查slot的多少
        checkContainerDataCount(data,27);



        if(Minecraft.getInstance().isSameThread()){
            mit = new mainMenuInventorySaver();
        }else mit = mainMenuInventorySaver.getServerState(ServerManager.getServerInstance());

        ItemStackHandler itemHandler = mit.itemHandler;

        // 添加一个slot在menu上。
        //this.addSlot(new SlotItemHandler(itemHandler,0,80,32));
        for(int i=0;i<3;++i)
            for(int j=0;j<9;++j){
                addSlot(new SlotItemHandler(itemHandler,i*9+j,8+18*j,12+i*18));
            }


        ItemStack is = ModItems.ICE_SWORD.toStack();
        //is.enchant(Enchantments.QUICK_CHARGE,5);
        itemHandler.insertItem(0, is,false);

        this.data = data;
        // 添加data 用于数据同步的。
        addDataSlots(data);


        // 添加玩家背包和热键栏的slot，关于slot的讲解在下面。
        layoutPlayerInventorySlots(inv);


    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        // The quick moved slot stack
        ItemStack quickMovedStack = ItemStack.EMPTY;
        // The quick moved slot
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        // If the slot is in the valid range and the slot is not empty
        if (quickMovedSlot.hasItem()) {
            // Get the raw stack to move
            ItemStack rawStack = quickMovedSlot.getItem();
            // Set the slot stack to a copy of the raw stack
            quickMovedStack = rawStack.copy();

    /*
    The following quick move logic can be simplified to if in data inventory,
    try to move to player inventory/hotbar and vice versa for containers
    that cannot transform data (e.g. chests).
    */

            // If the quick move was performed on the data inventory result slot
            if (quickMovedSlotIndex == 0) {
                // Try to move the result slot into the player inventory/hotbar
                if (!this.moveItemStackTo(rawStack, 5, 41, true)) {
                    // If cannot move, no longer quick move
                    return ItemStack.EMPTY;
                }

                // Perform logic on result slot quick move
                quickMovedSlot.onQuickCraft(rawStack, quickMovedStack);
            }
            // Else if the quick move was performed on the player inventory or hotbar slot
            else if (quickMovedSlotIndex >= 5 && quickMovedSlotIndex < 41) {
                // Try to move the inventory/hotbar slot into the data inventory input slots
                if (!this.moveItemStackTo(rawStack, 1, 5, false)) {
                    // If cannot move and in player inventory slot, try to move to hotbar
                    if (quickMovedSlotIndex < 32) {
                        if (!this.moveItemStackTo(rawStack, 32, 41, false)) {
                            // If cannot move, no longer quick move
                            return ItemStack.EMPTY;
                        }
                    }
                    // Else try to move hotbar into player inventory slot
                    else if (!this.moveItemStackTo(rawStack, 5, 32, false)) {
                        // If cannot move, no longer quick move
                        return ItemStack.EMPTY;
                    }
                }
            }
            // Else if the quick move was performed on the data inventory input slots, try to move to player inventory/hotbar
            else if (!this.moveItemStackTo(rawStack, 5, 41, false)) {
                // If cannot move, no longer quick move
                return ItemStack.EMPTY;
            }

            if (rawStack.isEmpty()) {
                // If the raw stack has completely moved out of the slot, set the slot to the empty stack
                quickMovedSlot.set(ItemStack.EMPTY);
            } else {
                // Otherwise, notify the slot that that the stack count has changed
                quickMovedSlot.setChanged();
            }

    /*
    The following if statement and Slot#onTake call can be removed if the
    menu does not represent a container that can transform stacks (e.g.
    chests).
    */
            if (rawStack.getCount() == quickMovedStack.getCount()) {
                // If the raw stack was not able to be moved to another slot, no longer quick move
                return ItemStack.EMPTY;
            }
            // Execute logic on what to do post move with the remaining stack
            quickMovedSlot.onTake(player, rawStack);
        }

        return quickMovedStack; // Return the slot stack
    }

    @Override//保持菜单打开
    public boolean stillValid(Player pPlayer) {
        return true;
    }



    private void layoutPlayerInventorySlots(Inventory playerInventory) {
        // Player inventory
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }

        // Hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }




}
