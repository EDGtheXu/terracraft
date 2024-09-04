package com.theXu.terracraft0323.magicStoreCraft;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.List;

public class magicStorageScreen extends AbstractContainerScreen<magicStorageMenu> {
    /**
     * The old x position of the mouse pointer
     */
    private float xMouse;
    /**
     * The old y position of the mouse pointer
     */
    private float yMouse;
    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private boolean widthTooNarrow;
    private boolean buttonClicked;
    public final Inventory playerInventory;
    private craftComponent craftComponent;
    private storegeComponent storegeComponent;
    private int componentX = 5;
    private int componentY = 5;
    private int componentLineCount = 6;
    private int componentRowCount = 11;
    private AbstractButton switchBt;
    private int switchI = 0;


    public magicStorageScreen(magicStorageMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.titleLabelX = 97;

        playerInventory = pPlayerInventory;


    }

/*
    @Override
    public void containerTick() {
        if (this.minecraft.gameMode.hasInfiniteItems()) {
            this.minecraft
                    .setScreen(
                            new CreativeModeInventoryScreen(
                                    this.minecraft.player, this.minecraft.player.connection.enabledFeatures(), this.minecraft.options.operatorItemsTab().get()
                            )
                    );
        } else {
            //this.recipeBookComponent.tick();
        }
    }
*/


    @Override
    protected void init() {

        if (Minecraft.getInstance().screen != null) {
            craftComponent = new craftComponent(componentX,componentY,componentLineCount,componentRowCount, Component.literal("widget.terracraft"),
                    List.of(playerInventory.items, magicStoreSaver.itemStacks));
            //addWidget(scrollWidget);
            //addRenderableWidget(craftComponent);

            storegeComponent = new storegeComponent(componentX,componentY,componentLineCount,componentRowCount, Component.literal("widget.magicstorage"));
            addRenderableWidget(storegeComponent);


            switchBt = new AbstractButton(Minecraft.getInstance().screen.width/2,20,30,20,Component.literal("存储")) {
                @Override
                public void onPress() {
                    switchI++;
                    if(switchI==2) switchI = 0;
                    clearWidgets();
                    if(switchI==0){
                        addRenderableWidget(storegeComponent);
                        this.setMessage(Component.literal("存储"));
                    }else{
                        addRenderableWidget(craftComponent);
                        this.setMessage(Component.literal("合成"));
                    }
                    addRenderableWidget(switchBt);
                }

                @Override
                protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
            };
            addRenderableWidget(switchBt);
        }

        super.init();

    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        pGuiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            this.recipeBookComponent.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        } else {
            super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            this.recipeBookComponent.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            this.recipeBookComponent.renderGhostRecipe(pGuiGraphics, this.leftPos, this.topPos, false, pPartialTick);
        }

        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);

        this.recipeBookComponent.renderTooltip(pGuiGraphics, this.leftPos, this.topPos, pMouseX, pMouseY);
        this.xMouse = (float)pMouseX;
        this.yMouse = (float)pMouseY;

        //渲染物品信息
        if(craftComponent!=null&& craftComponent.hoverIt!=null)
            pGuiGraphics.renderTooltip(this.font, this.getTooltipFromContainerItem(craftComponent.hoverIt), craftComponent.hoverIt.getTooltipImage(), craftComponent.hoverIt, pMouseX, pMouseY);
        if(storegeComponent!=null&& storegeComponent.hoverIt!=null)
            pGuiGraphics.renderTooltip(this.font, this.getTooltipFromContainerItem(storegeComponent.hoverIt), storegeComponent.hoverIt.getTooltipImage(), storegeComponent.hoverIt, pMouseX, pMouseY);


    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = this.leftPos;
        int j = this.topPos;
        pGuiGraphics.blit(INVENTORY_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        renderEntityInInventoryFollowsMouse(pGuiGraphics, i + 26, j + 8, i + 75, j + 78, 30, 0.0625F, this.xMouse, this.yMouse, this.minecraft.player);
    }

    public static void renderEntityInInventoryFollowsMouse(
            GuiGraphics pGuiGraphics,
            int pX1,
            int pY1,
            int pX2,
            int pY2,
            int pScale,
            float pYOffset,
            float pMouseX,
            float pMouseY,
            LivingEntity pEntity
    ) {
        float f = (float)(pX1 + pX2) / 2.0F;
        float f1 = (float)(pY1 + pY2) / 2.0F;
        float f2 = (float)Math.atan((double)((f - pMouseX) / 40.0F));
        float f3 = (float)Math.atan((double)((f1 - pMouseY) / 40.0F));
        // Forge: Allow passing in direct angle components instead of mouse position
        renderEntityInInventoryFollowsAngle(pGuiGraphics, pX1, pY1, pX2, pY2, pScale, pYOffset, f2, f3, pEntity);
    }

    public static void renderEntityInInventoryFollowsAngle(
            GuiGraphics p_282802_,
            int p_275688_,
            int p_275245_,
            int p_275535_,
            int p_294406_,
            int p_294663_,
            float p_275604_,
            float angleXComponent,
            float angleYComponent,
            LivingEntity p_275689_
    ) {
        float f = (float)(p_275688_ + p_275535_) / 2.0F;
        float f1 = (float)(p_275245_ + p_294406_) / 2.0F;
        p_282802_.enableScissor(p_275688_, p_275245_, p_275535_, p_294406_);
        float f2 = angleXComponent;
        float f3 = angleYComponent;
        Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternionf1 = new Quaternionf().rotateX(f3 * 20.0F * (float) (Math.PI / 180.0));
        quaternionf.mul(quaternionf1);
        float f4 = p_275689_.yBodyRot;
        float f5 = p_275689_.getYRot();
        float f6 = p_275689_.getXRot();
        float f7 = p_275689_.yHeadRotO;
        float f8 = p_275689_.yHeadRot;
        p_275689_.yBodyRot = 180.0F + f2 * 20.0F;
        p_275689_.setYRot(180.0F + f2 * 40.0F);
        p_275689_.setXRot(-f3 * 20.0F);
        p_275689_.yHeadRot = p_275689_.getYRot();
        p_275689_.yHeadRotO = p_275689_.getYRot();
        Vector3f vector3f = new Vector3f(0.0F, p_275689_.getBbHeight() / 2.0F + p_275604_, 0.0F);
        renderEntityInInventory(p_282802_, f, f1, p_294663_, vector3f, quaternionf, quaternionf1, p_275689_);
        p_275689_.yBodyRot = f4;
        p_275689_.setYRot(f5);
        p_275689_.setXRot(f6);
        p_275689_.yHeadRotO = f7;
        p_275689_.yHeadRot = f8;
        p_282802_.disableScissor();
    }

    public static void renderEntityInInventory(
            GuiGraphics pGuiGraphics,
            float pX,
            float pY,
            int pScale,
            Vector3f pTranslate,
            Quaternionf pPose,
            @Nullable Quaternionf pCameraOrientation,
            LivingEntity pEntity
    ) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate((double)pX, (double)pY, 50.0);
        pGuiGraphics.pose().mulPose(new Matrix4f().scaling((float)pScale, (float)pScale, (float)(-pScale)));
        pGuiGraphics.pose().translate(pTranslate.x, pTranslate.y, pTranslate.z);
        pGuiGraphics.pose().mulPose(pPose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (pCameraOrientation != null) {
            pCameraOrientation.conjugate();
            entityrenderdispatcher.overrideCameraOrientation(pCameraOrientation);
        }

        entityrenderdispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(pEntity, 0.0, 0.0, 0.0, 0.0F, 1.0F, pGuiGraphics.pose(), pGuiGraphics.bufferSource(), 15728880));
        pGuiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        pGuiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }

    @Override
    protected boolean isHovering(int pX, int pY, int pWidth, int pHeight, double pMouseX, double pMouseY) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(pX, pY, pWidth, pHeight, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {


        if (this.recipeBookComponent.mouseClicked(pMouseX, pMouseY, pButton)) {
            this.setFocused(this.recipeBookComponent);
            System.out.println("click");
            return true;
        } else {

            return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? false : super.mouseClicked(pMouseX, pMouseY, pButton);
        }

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }


    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (this.buttonClicked) {
            this.buttonClicked = false;
            return true;
        } else {
            return super.mouseReleased(pMouseX, pMouseY, pButton);
        }
    }



    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        super.slotClicked(pSlot, pSlotId, pMouseButton, pType);

    }






}
