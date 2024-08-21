package com.theXu.terracraft0323.ui.jewelrySlots;

import com.theXu.terracraft0323.ServerManager;
import com.theXu.terracraft0323.ui.mainMenu.mainMenuInventorySaver;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;


public class slotsMenu extends InventoryMenu {


    mainMenuInventorySaver mit;



    public slotsMenu(Inventory pPlayerInventory, boolean pActive, Player pOwner) {
        super(pPlayerInventory, pActive, pOwner);

        //jewelrysInventory = new slotsHandler(pOwner);

        if(Minecraft.getInstance().isSameThread()){
            mit = new mainMenuInventorySaver();
        }else{
            mit = mainMenuInventorySaver.getServerState(ServerManager.getServerInstance());
        }

        ItemStackHandler jewelrysInventory = mit.itemHandler;
        for(int i = 0; i < slotsHandler.SLOT_COUNT; ++i) {
            this.addSlot(new SlotItemHandler(jewelrysInventory, i, 18 + 154 , 2+i*18));
        }

        //mit.itemHandler = jewelrysInventory;



        //pOwner.sendSystemMessage(Component.literal(String.valueOf(Minecraft.getInstance().isSameThread())));


    }

    @Override
    public void clicked(int pSlotId, int pButton, ClickType pClickType, Player pPlayer) {
        super.clicked(pSlotId, pButton, pClickType, pPlayer);

        //jewelrysInventory.save(new ListTag());
    }




}
