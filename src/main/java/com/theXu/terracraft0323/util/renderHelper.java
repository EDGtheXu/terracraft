package com.theXu.terracraft0323.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class renderHelper {

    public static void renderItemStack(GuiGraphics guiGraphics, ItemStack it, int x, int y, boolean overLay){
        var minecraft = Minecraft.getInstance();
        guiGraphics.pose().pushPose();
        if(overLay) guiGraphics.setColor(1F,  0.5F,0.5F,1F);
        guiGraphics.renderItem(it,x,y);
        guiGraphics.setColor(1F,  1,1,1F);
        guiGraphics.renderItemDecorations(minecraft.font,it,x,y);

        guiGraphics.pose().popPose();
    }
}
