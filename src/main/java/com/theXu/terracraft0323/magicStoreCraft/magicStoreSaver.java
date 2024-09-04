package com.theXu.terracraft0323.magicStoreCraft;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class magicStoreSaver extends SavedData {
    /**
     * 保存数据的名称常量。
     * 注意这个NAME应该保持是唯一的，不要重复。
     */
    public static final String NAME = "magic_storage";
    /**
     * 用于存储物品堆栈的栈结构。
     */
    public static final List<ItemStack> itemStacks = new ArrayList<>();
    /**
     * 创建一个新的ModLevelSaveData实例。
     *
     * @return 新创建的ModLevelSaveData实例。
     */
    public static magicStoreSaver create() {
        System.out.println("new");
        return new magicStoreSaver();
    }
    /**
     * 将物品堆栈放入栈中。
     *
     * @param item 要放入栈中的物品堆栈。
     */
    public void putItem(ItemStack item) {
        itemStacks.add(item);

        itemStacks.sort(Comparator.comparing(a -> a.getDisplayName().getString())
                );

        setDirty();
    }
    /**
     * 从栈中获取物品堆栈。
     *
     * @return 如果栈为空，则返回空气物品堆栈；否则返回栈顶的物品堆栈。
     */
    public ItemStack removeItem(int index) {
        if (itemStacks.isEmpty()) {
            return new ItemStack(Items.AIR);
        }
        setDirty();
        ItemStack it = itemStacks.get(index);
        itemStacks.remove(it);
        return it;
    }
    /**
     * 将ModLevelSaveData的数据保存到CompoundTag中。
     *
     * @param pCompoundTag 要保存数据的CompoundTag。
     * @return 保存了数据的CompoundTag。
     */

    @Override
    public CompoundTag save(CompoundTag pCompoundTag, HolderLookup.Provider registries) {
        ListTag listTag = new ListTag();
        itemStacks.forEach((stack) -> {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.put("itemstack", stack.save(registries));
            listTag.add(compoundTag);
        });
        pCompoundTag.put("list", listTag);
        System.out.println("save");
        return pCompoundTag;
    }
    /**
     * 从CompoundTag中加载ModLevelSaveData的数据。
     *
     * @param nbt 包含数据的CompoundTag。
     * @return 加载了数据的ModLevelSaveData实例。
     */
    public magicStoreSaver load(CompoundTag nbt, HolderLookup.Provider registries) {
        magicStoreSaver data = this.create();
        ListTag listNBT = (ListTag) nbt.get("list");
        if (listNBT != null) {
            for (Tag value : listNBT) {
                CompoundTag tag = (CompoundTag) value;
                ItemStack itemStack = ItemStack.parseOptional(registries,tag.getCompound("itemstack"));
                itemStacks.add(itemStack);
            }
        }
        System.out.println("load");
        return data;
    }
    /**
     * 从CompoundTag中解码ModLevelSaveData的数据。
     *
     * @param tag 包含数据的CompoundTag。
     * @return 解码后的ModLevelSaveData实例。
     */
    public static magicStoreSaver decode(CompoundTag tag, HolderLookup.Provider registries){
        magicStoreSaver modLevelSaveData = magicStoreSaver.create();
        modLevelSaveData.load(tag,registries);
        return modLevelSaveData;
    }
    /**
     * 获取指定世界的ModLevelSaveData实例。通过这个方法获得对应的data
     *
     * @param worldIn 要获取数据的世界。
     * @return 与指定世界关联的ModLevelSaveData实例。
     * @throws RuntimeException 如果尝试从客户端世界获取数据，则抛出运行时异常。
     * **/
    public static magicStoreSaver get(Level worldIn) {
        if (!(worldIn instanceof ServerLevel)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerLevel world = worldIn.getServer().getLevel(ServerLevel.OVERWORLD);
        DimensionDataStorage dataStorage = world.getDataStorage();

        magicStoreSaver t = dataStorage.computeIfAbsent(new Factory<magicStoreSaver>(magicStoreSaver::create, magicStoreSaver::decode), magicStoreSaver.NAME);
        System.out.println(t);
        return t;
    }


}