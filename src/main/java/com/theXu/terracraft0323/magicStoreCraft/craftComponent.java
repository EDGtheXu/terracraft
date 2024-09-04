package com.theXu.terracraft0323.magicStoreCraft;

import com.theXu.terracraft0323.recipe.craftHelper;
import com.theXu.terracraft0323.recipe.terraRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.gui.layouts.Layout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.*;

import static com.theXu.terracraft0323.util.renderHelper.renderItemStack;

public class craftComponent extends AbstractContainerWidget {

    private List<ItemStack> results;//输出的配方result
    private Map<ItemStack, List<Ingredient>> recipeMap;//所有选择的配方
    private List<List<ItemStack>> itemListForCraft;//拥有的原材料

    terraRecipe.catalogMenu recipeType= terraRecipe.catalogMenu.SWORD;

    public craftHelper helper;
//参数
    public int internal = 18;
    private int lineCount = 6;
    private int rowCount = 11;
    private int pageCount = lineCount * rowCount;
    private int page = 1;
    private int maxPage;
    boolean onCraftPath = false;
    boolean showAll = false;


//子控件
    private StringWidget stringWidget;
    private AbstractButton nextBt;
    private AbstractButton lastBt;
    private doCraftComponent doCraft;
    private AbstractButton catalogSwitcher;
    private AbstractButton showAllBt;

//动作缓冲
    public List<Ingredient> selectIng = null;
    public ItemStack selectResult = ItemStack.EMPTY;
    public ItemStack hoverIt;


    public craftComponent(int x, int y, int lineCount, int rowCount, Component message,
        List<List<ItemStack>> itsForCraft
    ) {
        super(x, y, 0, 0, message);
        this.lineCount = lineCount;
        this.rowCount = rowCount;
        setWidth(lineCount * internal);
        setHeight(rowCount * internal);
        results = new ArrayList<>();
        recipeMap = terraRecipe.getRecipeType(recipeType);

        helper = new craftHelper(Minecraft.getInstance().player, magicStoreSaver.itemStacks);

        this.itemListForCraft = itsForCraft;

        for(var list : itsForCraft){
            for(ItemStack it : list){

            }
        }


        doCraft = new doCraftComponent(x,y+getHeight(),200,35,Component.literal("button.craft"),this);

        stringWidget = new StringWidget(Component.literal(page+"/"+maxPage),Minecraft.getInstance().font);
        stringWidget.setPosition(this.width+5+28,10);
        stringWidget.setWidth(40);
        lastBt = new AbstractButton(this.getX()+this.width+5,this.getY(),25,15,Component.literal("<-")) {
            @Override
            public void onPress() {
                page--;
                if(page==0) page = maxPage;

            }

            @Override
            protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            }
        };

        nextBt = new AbstractButton(this.getX()+this.width+5+28+28,this.getY(),25,15,Component.literal("->")) {
            @Override
            public void onPress() {
                page = page % maxPage+1;
            }

            @Override
            protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
        };

        catalogSwitcher = new AbstractButton(this.getX()+this.width+5,this.getY()+18,35,15,Component.literal(recipeType.name())) {
            @Override
            public void onPress() {
                recipeType = terraRecipe.catalogMenu.values()[(recipeType.ordinal()+1)%terraRecipe.catalogMenu.values().length];
                this.setMessage(Component.literal(recipeType.name()));
                recipeMap = terraRecipe.getRecipeType(recipeType);
                page = 1;
            }

            @Override
            public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
        };

        showAllBt = new AbstractButton(this.getX()+this.width+5+40,this.getY()+18,30,15,Component.literal("show all")) {
            @Override
            public void onPress() {
                showAll = !showAll;
                if(!showAll){
                    this.setMessage(Component.literal("only relation") );
                }else{
                    this.setMessage(Component.literal("show all") );

                }
                page = 1;
            }

            @Override
            public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
        };


    }


    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float pTick) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(this.getX(),this.getY(),0);
        //刷新可制作表和背包列表
        helper.refreshInventoryMap();
        refreshScreen();
        stringWidget.setMessage(Component.nullToEmpty(page+"/"+maxPage));
        //渲染物品
        boolean flag = true;
        for(int j=0;j < rowCount  && flag;j++){
            for(int i=0;i<lineCount;i++){
                int index = i + j * lineCount + (page -1)* pageCount;
                if(index >= results.size()){
                    flag = false;
                    break;
                }
                renderItemStack(guiGraphics,results.get(index),i * internal,j * internal,!helper.canCraft(terraRecipe.getRecipeType(recipeType).get(results.get(index))));
            }
        }
        guiGraphics.pose().popPose();

        //渲染物品信息
        if(this.isHovered) {
            int resi = (mouseX-getX()) / internal;
            int resj = (mouseY-getY()) / internal;

            int index = resi + resj * lineCount + (page - 1) * pageCount;
            if (index < results.size()) {
                hoverIt = results.get(index);
            }else{
                hoverIt = null;
            }
        }else{
            hoverIt = null;
        }

        //子控件

        lastBt.render(guiGraphics,this.width,mouseY,pTick);
        catalogSwitcher.render(guiGraphics,this.width+lastBt.getWidth() + 30,mouseY,pTick);
        nextBt.render(guiGraphics,this.width+ catalogSwitcher.getWidth() +3,mouseY,pTick);
        showAllBt.render(guiGraphics,0,0,pTick);
        doCraft.renderWidget(guiGraphics,this.width,this.height,pTick);
        stringWidget.renderWidget(guiGraphics,this.width,this.height,pTick);

    }


    @Override
    public List<? extends GuiEventListener> children() {
        return List.of(lastBt,catalogSwitcher,nextBt,doCraft,showAllBt,stringWidget);
    }


    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        if(hoverIt!=null){
            if(button ==0){//选中
                selectResult = hoverIt;
                selectIng = terraRecipe.getRecipeType(terraRecipe.catalogMenu.ALL).get(selectResult);
            }
            else if(button == 1){//逆向配方
                var mp = terraRecipe.itemForResultSet.get(hoverIt.getItem());
                if(mp!=null){

                    onCraftPath = true;
                    results.clear();
                    for(var it : mp)
                        results.add(it);

                }else{
                    results.clear();
                }

            }
        }
        return super.mouseClicked(mouseX, mouseY, button);

    }


    public Set<ItemStack> getItemForResult(){

        Set<ItemStack> itemStacks = new LinkedHashSet<>();
        for(List<ItemStack> list : itemListForCraft)//制作拥有的原材料
            for(ItemStack it : list){
                var sets = terraRecipe.itemForResultSet.get(it.getItem());//原材料对应可制作的物品
                if(sets!=null && !sets.isEmpty()) itemStacks.addAll(sets);
            }
        return itemStacks;
    }

    public List<Ingredient> getIngredients(ItemStack itemStack){
        return terraRecipe.getRecipeType(terraRecipe.catalogMenu.ALL).get(itemStack);
    }


    //显示可以合成物品
    public void refreshScreen(){

        if(!onCraftPath){
            results.clear();
            if(showAll){
                //全部显示
                results.addAll(recipeMap.keySet());
            }else{

                //所有拥有的原材料可制作的的物品
                var templist = getItemForResult().stream().toList();

                var set = recipeMap.keySet();
                for(var n : templist){
                    if(set.contains(n))
                        results.add(n);
                }
            }
        }else{//合成查看路径

        }

        //按可制作和名称排序
        results.sort((a,b)->
                helper.canCraft(getIngredients(a))?
                        (helper.canCraft(getIngredients(b))?
                                a.getHoverName().getString().compareTo(b.getHoverName().getString()):
                                -1):
                        (helper.canCraft(getIngredients(b))?
                                1:
                                a.getHoverName().getString().compareTo(b.getHoverName().getString()))
        );


        maxPage = (results.size() + pageCount + 1 ) / pageCount;
        //nextBt.setMessage(Component.literal(String.valueOf(maxPage)));
    }


}