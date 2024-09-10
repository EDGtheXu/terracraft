package com.theXu.terracraft0323.recipe;

import com.theXu.terracraft0323.ServerManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.*;

public class terraRecipe {

    public enum catalogMenu{
        ALL,
        BOW,
        SWORD,
        ARMOR,
        FOOD,
        BLOCK,
        TOOL,
        MATERIAL,
        OTHER
    }



    private static Map<ItemStack, List<Ingredient>> recipeMapAll = new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapBow= new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapSword= new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapArmor= new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapFood= new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapBlock= new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapTool= new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapMat= new LinkedHashMap<>();
    private static Map<ItemStack, List<Ingredient>> recipeMapOther= new LinkedHashMap<>();


    private static Map<catalogMenu,Map<ItemStack, List<Ingredient>>> recipeCatalogs = Map.of(
            catalogMenu.ALL,recipeMapAll,
            catalogMenu.BOW,recipeMapBow,
            catalogMenu.SWORD,recipeMapSword,
            catalogMenu.ARMOR,recipeMapArmor,
            catalogMenu.FOOD,recipeMapFood,
            catalogMenu.BLOCK,recipeMapBlock,
            catalogMenu.TOOL,recipeMapTool,
            catalogMenu.MATERIAL,recipeMapMat,
            catalogMenu.OTHER,recipeMapOther


    );

    public static Map<ItemStack, List<Ingredient>> getRecipeType(catalogMenu catalog){return recipeCatalogs.get(catalog);}

    public static Map<Item,Set<ItemStack>> itemForResultSet = new LinkedHashMap<>();

    //分类
    public static void genRecipes(){

        recipeMapAll.forEach((k,v)->{

            boolean added = false;
            Item it = k.getItem();

            if(it instanceof BlockItem){
                recipeMapBlock.put(k,v);
                added = true;
            }

            if(//k.getComponents().keySet().contains(DataComponents.FOOD) ||
                    k.getPrototype().get(DataComponents.FOOD)!=null
            ){
                recipeMapFood.put(k,v);
                added = true;
            }

            if(it instanceof BowItem
                ||it instanceof CrossbowItem){
                recipeMapBow.put(k,v);
                added = true;
            }

            if(it instanceof SwordItem){
                recipeMapSword.put(k,v);
                added = true;
            }

            if(it instanceof ArmorItem){
                recipeMapArmor.put(k,v);
                added = true;
            }

            if(it instanceof DiggerItem){
                recipeMapTool.put(k,v);
                added = true;
            }

            if(!added&&itemForResultSet.containsKey(it)
                    &&itemForResultSet.get(it).size()>3){
                recipeMapMat.put(k,v);
                added = true;
            }


            if(!added){
                recipeMapOther.put(k,v);
            }


        });
    }
    static boolean init = false;
    public static void initRecipe(){
        if(init)return;
        init = true;

        recipeMapAll.clear();
        RecipeManager rm = ServerManager.serverInstance.getRecipeManager();
        Level level = Minecraft.getInstance().level;
        if(level==null){
            System.out.println("level is null");
            return;
        }

        //工作台
        //var rs = rm.getAllRecipesFor(RecipeType.CRAFTING);

        var rs = rm.getAllRecipesFor(RecipeType.CRAFTING);

        System.out.println(rs.size());
        //遍历所有制作谱
        for(var recipe : rs){
            //LogUtils.getLogger().info(recipe.value().getResultItem(level.registryAccess()).getDisplayName().getString());

            var is = recipe.value().getIngredients();
            List<Ingredient> terraRecipe = new ArrayList<>();

            //遍历所有原料
            for(Ingredient i : is){

                if(i.getItems().length==0)continue;
                //LogUtils.getLogger().info(i.getItems()[0].getDisplayName().getString());

                //遍历已添加的原料
                boolean flag = false;
                for (Ingredient ti : terraRecipe){
                    if(ti.equals(i) ||
                         i.getItems().length > 0 && (
                                i.getItems()[0].getItem() == ti.getItems()[0].getItem()
                        )){
                        flag = true;
                        for(ItemStack it : ti.getItems()){
                            it.setCount(it.getCount()+1);
                        }
                    }
                    if(flag) break;
                }
                if(!flag){
                    terraRecipe.add(i);

                    //将新的原料添加对应的制作物品
                    for(ItemStack it : i.getItems()){
                        if(itemForResultSet.containsKey(it.getItem())){
                            Set<ItemStack> oldSet = itemForResultSet.get(it.getItem());
                            ItemStack itemStack = recipe.value().getResultItem(level.registryAccess());
                            oldSet.add(itemStack);
                        }else{
                            Set<ItemStack> newSet = new LinkedHashSet<>();
                            newSet.add(recipe.value().getResultItem(level.registryAccess()));
                            itemForResultSet.put(it.getItem(),newSet);
                        }
                    }


                }

/*
                terraRecipe.stream().filter(it-> it.equals(i) || i.getItems().length > 0 && (
                                i.getItems()[0].getItem() == it.getItems()[0].getItem()
                        )).findAny()
                        .ifPresentOrElse(
                            it -> {
                                for(ItemStack itemStack : it.getItems()){
                                    int c = itemStack.getCount()+1;
                                    itemStack.setCount(c);
                                    System.out.println(c);
                                }},
                            () -> terraRecipe.add(i)
                );

*/
            }
            recipeMapAll.put(recipe.value().getResultItem(level.registryAccess()),terraRecipe);


        }

        genRecipes();
    }



}
