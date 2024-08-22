package com.theXu.terracraft0323.ui.mainMenu;

import com.theXu.terracraft0323.NeoMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

public class mainMenuScreen extends AbstractContainerScreen<mainMenu> {
    private final ResourceLocation OBSIDIAN_CONTAINER_RESOURCE = ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "textures/container/null.png");
    //图片的大小
    private final int textureWidth = 176;
    private final int textureHeight = 166;
    //构造
    public mainMenuScreen(mainMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    // 初始化
    @Override
    protected void init() {
        super.init();
    }
    // 渲染label，在这里实现渲染你的按钮，提示信息等
    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        //这里我们将slot中的itemstack的堆叠数量取出，渲染在界面上。
        // 其中参数含义和上一个教程讲得一样。
        super.renderLabels(pGuiGraphics, pMouseX, pMouseY);
        //pGuiGraphics.drawString(this.font,Component.literal(this.menu.getContainerData().get(0)+""),82,20,0xeb0505);


    }
    // 这里渲染你的图片
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1F);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        // 渲染图片
        pGuiGraphics.blit(OBSIDIAN_CONTAINER_RESOURCE,x,y,0,0,this.textureWidth,this.textureHeight,this.textureWidth,this.textureHeight);

    }
    // 为了能让我们的GUI渲染物品的提示信息，所以调用下renderTooltip
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics,pMouseX,pMouseY,pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics,pMouseX,pMouseY);
    }

    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        super.slotClicked(pSlot, pSlotId, pMouseButton, pType);
        System.out.println(pSlot);

    }
}