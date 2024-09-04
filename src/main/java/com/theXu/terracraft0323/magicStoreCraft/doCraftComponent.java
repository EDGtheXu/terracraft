package com.theXu.terracraft0323.magicStoreCraft;

import com.theXu.terracraft0323.network.packet.terraCraftPacket.serverCraftPacket;
import com.theXu.terracraft0323.recipe.terraRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractContainerWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

import static com.theXu.terracraft0323.util.renderHelper.renderItemStack;

public class doCraftComponent extends AbstractContainerWidget {
    private AbstractButton bt;
    private craftComponent craft;

    public doCraftComponent(int x, int y, int width, int height, Component message,craftComponent craftComponent) {
        super(x, y, width, height, message);
        this.craft = craftComponent;
        bt = new AbstractButton(getX(),getY(), 25,15,Component.literal("合成")) {
            @Override
            public void onPress() {
                int id = terraRecipe.getRecipeType(craftComponent.recipeType).keySet().stream().toList().indexOf(craft.selectResult);
                //客户端发包
                PacketDistributor.sendToServer(new serverCraftPacket(id, craftComponent.recipeType.ordinal()));

            }
            @Override
            protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
        };

    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int x, int y, float t) {
        guiGraphics.pose().pushPose();
        //guiGraphics.pose().translate(this.getX(),this.getY(),0);

        var ings = craft.selectIng;
        if(ings==null) return;
        renderItemStack(guiGraphics, craft.selectResult,getX()+28,getY(),!craft.helper.canCraft(ings));

        for(int i=0;i<ings.size();i++){
            renderItemStack(guiGraphics,ings.get(i).getItems()[0], getX()+i * craft.internal, getY()+18 ,!craft.helper.containIng(ings.get(i)));
        }
        bt.render(guiGraphics,x,y,t);
        guiGraphics.pose().popPose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }

    @Override
    public List<? extends GuiEventListener> children() {
        return List.of(bt);
    }
}