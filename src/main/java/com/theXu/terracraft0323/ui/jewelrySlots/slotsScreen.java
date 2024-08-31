package com.theXu.terracraft0323.ui.jewelrySlots;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.theXu.terracraft0323.network.packet.terraCraftPacket.serverCraftPacket;
import com.theXu.terracraft0323.recipe.terraRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.*;
import java.util.List;

public class slotsScreen extends AbstractContainerScreen<terraBag> {
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
    private TerraCraftComponent scrollWidget;
    private doCraftWidget craftWidget;
    public Map<Item,Integer> inventoryMap = new HashMap<>();
    public Map<Item,List<ItemStack>> inventoryItemStackMap = new HashMap<>();

    public slotsScreen(terraBag pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        /*
        if (this.minecraft.gameMode.hasInfiniteItems()) {
            this.minecraft
                    .setScreen(
                            new CreativeModeInventoryScreen(
                                    this.minecraft.player, this.minecraft.player.connection.enabledFeatures(), this.minecraft.options.operatorItemsTab().get()
                            )
                    );
        } else {
            */

        if (Minecraft.getInstance().screen != null) {
            scrollWidget = new TerraCraftComponent(0,0,Minecraft.getInstance().screen.width/4,Minecraft.getInstance().screen.height-40, Component.literal("widget.terracraft"));
            //addWidget(scrollWidget);
            addRenderableWidget(scrollWidget);

            craftWidget = new doCraftWidget(10,minecraft.screen.height-35,200,35,Component.literal("button.craft"));
            addRenderableWidget(craftWidget);
        }

            super.init();

/*
            this.widthTooNarrow = this.width < 379;
            this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            this.addRenderableWidget(
                    new ImageButton(this.leftPos + 104, this.height / 2 - 22, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, p_313434_ -> {
                        this.recipeBookComponent.toggleVisibility();
                        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
                        p_313434_.setPosition(this.leftPos + 104, this.height / 2 - 22);
                        this.buttonClicked = true;
                    })
            );
            this.addWidget(this.recipeBookComponent);
            this.setInitialFocus(this.recipeBookComponent);


        }
  */


    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        pGuiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    /**
     * Renders the graphical user interface (GUI) element.
     *
     * @param pGuiGraphics the GuiGraphics object used for rendering.
     * @param pMouseX      the x-coordinate of the mouse cursor.
     * @param pMouseY      the y-coordinate of the mouse cursor.
     * @param pPartialTick the partial tick time.
     */
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
        if(scrollWidget.hoverIt!=null)
            pGuiGraphics.renderTooltip(this.font, this.getTooltipFromContainerItem(scrollWidget.hoverIt), scrollWidget.hoverIt.getTooltipImage(), scrollWidget.hoverIt, pMouseX, pMouseY);

        this.recipeBookComponent.renderTooltip(pGuiGraphics, this.leftPos, this.topPos, pMouseX, pMouseY);
        this.xMouse = (float)pMouseX;
        this.yMouse = (float)pMouseY;


        //this.scrollWidget.render(pGuiGraphics,0,0,pPartialTick);

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

    /**
     * Called when a mouse button is clicked within the GUI element.
     * <p>
     * @return {@code true} if the event is consumed, {@code false} otherwise.
     *
     * @param pMouseX the X coordinate of the mouse.
     * @param pMouseY the Y coordinate of the mouse.
     * @param pButton the button that was clicked.
     */

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

    /**
     * Called when a mouse button is released within the GUI element.
     * <p>
     * @return {@code true} if the event is consumed, {@code false} otherwise.
     *
     * @param pMouseX the X coordinate of the mouse.
     * @param pMouseY the Y coordinate of the mouse.
     * @param pButton the button that was released.
     */
    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (this.buttonClicked) {
            this.buttonClicked = false;
            return true;
        } else {
            return super.mouseReleased(pMouseX, pMouseY, pButton);
        }
    }


    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        super.slotClicked(pSlot, pSlotId, pMouseButton, pType);


    }



    private class TerraCraftComponent extends AbstractWidget{
        List<ItemStack> results;

        private Map<ItemStack, List<Ingredient>> map;

        public int internal = 18;
        private int lineCount = 6;
        private int rowCount = 11;
        private int pageCount = lineCount * rowCount;
        public List<Ingredient> selectIng = null;
        public ItemStack selectResult = ItemStack.EMPTY;
        private int page = 1;
        private int maxPage;
        private ItemStack hoverIt;


        private AbstractButton nextBt = new AbstractButton(this.width+28,0,25,20,Component.literal(String.valueOf(maxPage))) {
            @Override
            public void onPress() {
                page = page % maxPage+1;
                lastBt.setMessage(Component.literal(String.valueOf(page)));
            }

            @Override
            protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
        };
        private AbstractButton lastBt = new AbstractButton(this.width,0,25,20,Component.literal(String.valueOf(page))) {
            @Override
            public void onPress() {
                page--;
                if(page==0) page = maxPage;
                this.setMessage(Component.literal(String.valueOf(page)));
            }

            @Override
            protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            }
        };


        public TerraCraftComponent(int x, int y, int width, int height, Component message) {
            super(x, y, width, height, message);

            results = new ArrayList<>();
            map = terraRecipe.getInstance();

            addRenderableWidget(nextBt);
            addRenderableWidget(lastBt);


        }


        @Override
        protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float pTick) {
            //刷新可制作表和背包列表
            refreshInventoryMap(playerInventory);
            refreshScreen();
            //渲染物品
            boolean flag = true;
            for(int j=0;j < rowCount  && flag;j++){

                for(int i=0;i<lineCount;i++){
                    int index = i + j * lineCount + (page -1)* pageCount;
                    if(index >= results.size()){
                        flag = false;
                        break;
                    }
                    renderItemStack(guiGraphics,results.get(index),i * internal,j * internal,!canCraft(inventoryMap,terraRecipe.getInstance().get(results.get(index))));
                }
            }

            //渲染物品信息

            if(this.isHovered) {
                int resi = mouseX / internal;
                int resj = mouseY / internal;

                int index = resi + resj * lineCount + (page - 1) * pageCount;
                if (index < results.size()) {
                    hoverIt = results.get(index);
                }else{
                    hoverIt = null;
                }
            }else{
                hoverIt = null;
            }
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {

            if(mouseX>this.getX()+this.width||mouseY>getY()+this.height||mouseX<getX()||mouseY<getY()) return false;

            int resi = (int) (mouseX/internal);
            int resj = (int) (mouseY/internal);
                //System.out.println(resi+" "+resj);
            int index = resi + resj * lineCount + (page - 1) * pageCount;
            if(index < results.size()){
                selectResult = results.get(index);
                selectIng = map.get(selectResult);
            }

            return super.mouseClicked(mouseX, mouseY, button);

        }


        public Set<ItemStack> getItemForResult(Inventory inventory){
            Set<ItemStack> itemStacks = new LinkedHashSet<>();
            for(ItemStack it : inventory.items){
                var sets = terraRecipe.itemForResultSet.get(it.getItem());
                if(sets!=null && !sets.isEmpty()) itemStacks.addAll(sets);
            }
            return itemStacks;
        }

        public List<Ingredient> getIngredients(ItemStack itemStack){
            return terraRecipe.getInstance().get(itemStack);
        }

        public void refreshScreen(){
            var templist = getItemForResult(playerInventory).stream().toList();
            results.clear();
            for(var n : templist){
                results.add(n);
            }

            results.sort((a,b)->canCraft(inventoryMap,getIngredients(a))?-1:(canCraft(inventoryMap,getIngredients(b))?1:0 ));

            maxPage = (results.size() + pageCount + 1 ) / pageCount;
            nextBt.setMessage(Component.literal(String.valueOf(maxPage)));
        }



    }


    private class doCraftWidget extends AbstractWidget {
        private AbstractButton bt;


        public doCraftWidget(int x, int y, int width, int height, Component message) {
            super(x, y, width, height, message);
            bt = new AbstractButton(x,y,20,10,Component.literal("合成")) {
                @Override
                public void onPress() {
                    int id = terraRecipe.getInstance().keySet().stream().toList().indexOf(scrollWidget.selectResult);
                    PacketDistributor.sendToServer(new serverCraftPacket(id));


/*
                    refreshInventoryMap(playerInventory);
                    List<ItemStack> cost = new ArrayList<>();
                    if(scrollWidget.selectResult!=null){
                        boolean canCraft = true;
                        //每一种原料
                        for(Ingredient ig : scrollWidget.selectIng){
                            boolean haveIg = false;
                            //原料的每种物品
                            for (ItemStack it : ig.getItems()){
                                List<ItemStack> invens = inventoryItemStackMap.get(it.getItem());
                                if(invens==null) continue;
                                boolean contain = false;
                                //背包拥有的物品
                                for(ItemStack haveIt : invens){
                                    if(haveIt.getCount() >= it.getCount()){
                                        contain = true;
                                        cost.add(haveIt);
                                        break;
                                    }
                                }
                                if(contain){
                                    haveIg = true;
                                    break;
                                }
                            }
                            if(!haveIg){
                                canCraft = false;
                                break;
                            }

                        }
                        if(canCraft){
                            cost.forEach(it->it.setCount(0));
                            playerInventory.add(scrollWidget.selectResult);
                            System.out.println("craft");
                        }
                    }
*/
                }

                @Override
                protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

                }


            };
            addRenderableWidget(bt);
        }

        @Override
        protected void renderWidget(GuiGraphics guiGraphics, int x, int y, float t) {
            //bt.render(guiGraphics,x,y,t);


            var ings = scrollWidget.selectIng;
            if(ings==null) return;
            renderItemStack(guiGraphics,scrollWidget.selectResult,this.getX()+30,this.getY(),!canCraft(inventoryMap,ings));

            for(int i=0;i<ings.size();i++){
                renderItemStack(guiGraphics,ings.get(i).getItems()[0], this.getX() + i * scrollWidget.internal, 15+this.getY() ,!containIng(inventoryMap,ings.get(i)));
            }
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

        }
    }

    public void renderItemStack(GuiGraphics guiGraphics,ItemStack it,int x,int y,boolean overLay){

        guiGraphics.pose().pushPose();
        if(overLay) guiGraphics.setColor(0.3F,  0,0,1F);
        guiGraphics.renderItem(it,x,y);
        guiGraphics.setColor(1F,  1,1,1F);
        guiGraphics.renderItemDecorations(minecraft.font,it,x,y);

        guiGraphics.pose().popPose();
    }


    private void refreshInventoryMap(Inventory inventory){
        inventoryMap.clear();
        for(ItemStack itemStack : inventory.items){
            Item item = itemStack.getItem();
            if (inventoryMap.containsKey(item)) {
                int c = inventoryMap.get(item);
                inventoryMap.replace(item,c+itemStack.getCount());

                List<ItemStack> itemStacks = inventoryItemStackMap.get(item);
                itemStacks.add(itemStack);
            }else{
                inventoryMap.put(item,itemStack.getCount());

                List<ItemStack> itemStacks = new ArrayList<>();
                itemStacks.add(itemStack);
                inventoryItemStackMap.put(item,itemStacks);
            }
        }
    }


    private boolean canCraft(Map<Item,Integer> inventoryMap, List<Ingredient> ingredients){
        for(Ingredient in : ingredients){
            boolean ifContain = false;
            for(ItemStack needIt : in.getItems()){
                Integer haveCount = inventoryMap.get(needIt.getItem());
                if(haveCount!=null && haveCount  >= needIt.getCount()){
                    ifContain = true;
                    break;
                }
            }
            if(!ifContain)return false;
        }
        return true;

    }

    private boolean containIng(Map<Item,Integer> inventoryMap, Ingredient ingredients){

        for(ItemStack it : ingredients.getItems()){
            Integer sum = inventoryMap.get(it.getItem());
            if(sum!=null && sum >= it.getCount()) return true;
        }
        return false;
    }




}
