package com.theXu.terracraft0323.item.menuItem;

import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.ability.playerLevel.playerLevel;
import com.theXu.terracraft0323.ui.jewelrySlots.terraBag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class menuItem extends Item {


    public menuItem (Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(net.minecraft.world.level.Level pLevel, Player pPlayer, InteractionHand pUsedHand) {


        if (!pLevel.isClientSide && pUsedHand == InteractionHand.MAIN_HAND) {


            playerLevel lev = abilityRegister.get();
            if(lev!=null){
                lev.swordL++;
                System.out.println(lev.swordL);

            }


/*
            //打开主菜单
            pPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, p) -> new mainMenu(containerId, playerInventory),
                    Component.translatable("menu.title.mainMenu")
            ));
*/
            //打开带饰品的物品栏

            pPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, p) -> new terraBag(containerId, playerInventory),
                    Component.translatable("menu.title.terraInventory")
            ));

            System.out.println(pPlayer.getOffhandItem().getTags().toList());


        } else if (!pLevel.isClientSide && pUsedHand == InteractionHand.OFF_HAND) {
            pPlayer.sendSystemMessage(Component.literal("off hand"));

            var mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;



        }



        return super.use(pLevel, pPlayer, pUsedHand);
    }

}
