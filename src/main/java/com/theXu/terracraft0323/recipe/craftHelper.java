package com.theXu.terracraft0323.recipe;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class craftHelper {
    Player player;
    Inventory inventory;

    public Map<Item,Integer> inventoryMap = new HashMap<>();
    public Map<Item, List<ItemStack>> inventoryItemStackMap = new HashMap<>();



    public craftHelper(Player player){
        this.player = player;
        this.inventory = player.getInventory();

    }


    public void doCraft(Map.Entry<ItemStack, List<Ingredient>> recipe){

        refreshInventoryMap();
        List<ItemStack> cost = new ArrayList<>();
        List<Integer> costCount = new ArrayList<>();
        ItemStack result = recipe.getKey();
        List<Ingredient> ings = recipe.getValue();

        if(result!= null){
            boolean canCraft = true;
            Map<Item,Integer> needMap = new LinkedHashMap<>();
            //每一种原料
            for(Ingredient ig : ings){

                boolean haveIg = false;
                //原料的每种物品
                for (ItemStack it : ig.getItems()){
                    List<ItemStack> invens = inventoryItemStackMap.get(it.getItem());
                    if(invens==null) continue;
                    boolean contain = false;
                    //背包拥有的物品
                    int sum = 0;
                    List<ItemStack> temp = new ArrayList<>();
                    List<Integer>tempCount = new ArrayList<>();
                    for(ItemStack haveIt : invens){
                        sum+=haveIt.getCount();
                        temp.add(haveIt);
                        if(sum>it.getCount())
                            tempCount.add(haveIt.getCount() - sum + it.getCount());
                        else
                            tempCount.add(haveIt.getCount());
                        if( sum>= it.getCount()){
                            contain = true;
                            cost.addAll(temp);
                            costCount.addAll(tempCount);
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

                for(int i=0;i<cost.size();i++){
                    cost.get(i).setCount(cost.get(i).getCount() - costCount.get(i));
                }


                inventory.add(result.copy());
                System.out.println("craft");
            }
        }

    }

    private void refreshInventoryMap(){
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



}
