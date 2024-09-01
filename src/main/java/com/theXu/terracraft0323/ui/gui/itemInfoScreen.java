package com.theXu.terracraft0323.ui.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.theXu.terracraft0323.ui.jewelrySlots.terraBag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class itemInfoScreen  {

    private final Minecraft minecraft;

    //右手栏和盔甲栏的位置
    int x;
    int y;


    public itemInfoScreen(Minecraft mc){
        this.minecraft = mc;
        x =minecraft.getWindow().getGuiScaledWidth() - 100;
        y = minecraft.getWindow().getGuiScaledHeight()-30;
    }

    public void render(GuiGraphics guiGraphics) {

        guiGraphics.pose().pushPose();
        Player player = minecraft.player;


        //饰品

        for(int i=0;i< 7;i++){
            int x1 = minecraft.getWindow().getGuiScaledWidth()-20;
            int y1 = y - ((8 - i)*18);
            //guiGraphics.hLine(x,x+18,y1+18,Color.MAGENTA.getRGB());
            guiGraphics.renderItem(player.getInventory().getItem(41+i),x1,y1);
        }

        //装备
        for(int i=0;i<4;i++){
            int x2 = x + 30 + 18 * i;
            int y2 = y;
            //guiGraphics.vLine(x2-2,y2, y2 + 18,Color.MAGENTA.getRGB());
            ItemStack stack = player.getInventory().getArmor(3-i);
            guiGraphics.renderItem(stack,x2, y2 );
            drawItemBar(stack,guiGraphics,x2, y2 );
        }

        //右手物品
        guiGraphics.pose().translate(x,y,0);
        guiGraphics.pose().scale(1.1f,1.1f,1.1f);
        ItemStack stack = player.getMainHandItem();
        guiGraphics.renderItem(stack,0,0);
            //名称
        String str = stack.getDisplayName().getString();
        guiGraphics.drawString(minecraft.font,str.substring(1,str.length()-1),0,-10, stack.getRarity().getStyleModifier().apply(Style.EMPTY).getColor().getValue());
            //耐久条
        if (stack.isBarVisible()) {
            drawItemBar(stack, guiGraphics, 0, 0);
            int dur = stack.getDamageValue();
            int full = stack.getMaxDamage();
            guiGraphics.pose().translate(3, 15, 0);
            guiGraphics.pose().scale(0.7f, 0.7f, 0.7f);
            guiGraphics.drawString(minecraft.font, String.valueOf(full - dur), 1, 1, stack.getBarColor());
        }

        guiGraphics.pose().popPose();
    }

    public void drawItemBar(ItemStack stack,GuiGraphics g,int x,int y){
        if (stack.isBarVisible()) {
            int l = stack.getBarWidth();
            int i = stack.getBarColor();
            int i1 = x + 2;
            int j1 = y + 13;
            g.fill(RenderType.guiOverlay(), i1, j1, i1 + 13, j1 + 2, -16777216);
            g.fill(RenderType.guiOverlay(), i1, j1, i1 + l, j1 + 1, i | -16777216);
        }
    }



    private void drawBar(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, int progress, ResourceLocation[] barProgressSprites, ResourceLocation[] overlayProgressSprites) {
        RenderSystem.enableBlend();
        guiGraphics.blitSprite(barProgressSprites[bossEvent.getColor().ordinal()], 182, 5, 0, 0, x, y, progress, 5);
        if (bossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
            guiGraphics.blitSprite(overlayProgressSprites[bossEvent.getOverlay().ordinal() - 1], 182, 5, 0, 0, x, y, progress, 5);
        }

        RenderSystem.disableBlend();
    }


}