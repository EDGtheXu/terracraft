package com.theXu.terracraft0323.recipe;

import com.theXu.terracraft0323.ServerManager;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.*;

public class terraRecipe {
    private static Map<ItemStack, List<Ingredient>> recipeMap=new LinkedHashMap<>();
    public static Map<ItemStack, List<Ingredient>> getInstance(){return recipeMap;}

    public static Map<Item,Set<ItemStack>> itemForResultSet = new LinkedHashMap<>();
    public static void initRecipe(){
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
            recipeMap.put(recipe.value().getResultItem(level.registryAccess()),terraRecipe);


        }

    }



}
