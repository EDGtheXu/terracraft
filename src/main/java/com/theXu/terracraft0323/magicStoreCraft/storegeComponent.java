package com.theXu.terracraft0323.magicStoreCraft;

import com.theXu.terracraft0323.network.packet.terraCraftPacket.serverSavePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractContainerWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

import static com.theXu.terracraft0323.util.renderHelper.renderItemStack;

public class storegeComponent  extends AbstractContainerWidget {

    public int internal = 18;
    private int lineCount = 6;
    private int rowCount = 11;
    private int pageCount = lineCount * rowCount;
    public List<Ingredient> selectIng = null;
    public ItemStack selectResult = ItemStack.EMPTY;
    private int page = 1;
    private int maxPage;
    public ItemStack hoverIt;
    private int hoverIndex;
    private List<ItemStack> items;


    private AbstractButton nextBt;
    private AbstractButton lastBt;


    public storegeComponent(int x, int y, int lineCount, int rowCount, Component message) {
        super(x, y, 0, 0, message);
        this.lineCount = lineCount;
        this.rowCount = rowCount;
        setWidth(lineCount * internal);
        setHeight(rowCount * internal);

        items = magicStoreSaver.itemStacks.stream().toList();
        maxPage = (items.size() + pageCount + 1 ) / pageCount;

        lastBt = new AbstractButton(this.getX()+this.width+5,this.getY(),25,20,Component.literal(String.valueOf(page))) {
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

        nextBt = new AbstractButton(this.getX()+this.width+5+28,this.getY(),25,20,Component.literal(String.valueOf(maxPage))) {
            @Override
            public void onPress() {
                page = page % maxPage+1;
                lastBt.setMessage(Component.literal(String.valueOf(page)));
            }

            @Override
            protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
        };

    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float pTick) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(this.getX(),this.getY(),0);

        items = magicStoreSaver.itemStacks.stream().toList();
        maxPage = (items.size() + pageCount + 1 ) / pageCount;
        nextBt.setMessage(Component.literal(String.valueOf(maxPage)));
        //渲染物品
        boolean flag = true;
        for(int j=0;j < rowCount  && flag;j++){
            for(int i=0;i<lineCount;i++){
                int index = i + j * lineCount + (page -1)* pageCount;
                if(index >= items.size()){
                    flag = false;
                    break;
                }
                renderItemStack(guiGraphics,items.get(index),i * internal,j * internal,false);
            }
        }
        guiGraphics.pose().popPose();

        //渲染物品信息
        if(this.isHovered) {
            int resi = (mouseX-getX()) / internal;
            int resj = (mouseY-getY()) / internal;

            int index = resi + resj * lineCount + (page - 1) * pageCount;
            hoverIndex = index;
            if (index < items.size()) {
                hoverIt = items.get(index);
            }else{
                hoverIt = null;
            }
        }else{
            hoverIt = null;
        }

        //子控件
        lastBt.render(guiGraphics,this.width,mouseY,pTick);
        nextBt.render(guiGraphics,this.width+lastBt.getWidth()+3,mouseY,pTick);


    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }

    @Override
    public List<? extends GuiEventListener> children() {
        return List.of(lastBt,nextBt);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(hoverIt!=null){
            selectResult = hoverIt;
            PacketDistributor.sendToServer(new serverSavePacket(hoverIndex + 10000));
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }



}
