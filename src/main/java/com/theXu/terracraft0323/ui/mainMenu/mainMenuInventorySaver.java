package com.theXu.terracraft0323.ui.mainMenu;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.neoforged.neoforge.items.ItemStackHandler;

public class mainMenuInventorySaver extends SavedData {

    public ItemStackHandler itemHandler;
    public int ti;
    private static final String storeName = NeoMod.MODID + "_mainMenuInventory";


    public mainMenuInventorySaver(){
        ti = 0;
        itemHandler = new ItemStackHandler(27)
        {
            /*
            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {

                return super.extractItem(slot,amount,simulate) ;
            }

            @Override
            protected void validateSlotIndex(int slot) {
                if(slot>5)return;
                super.validateSlotIndex(slot);

            }

            @Override//槽位可存取
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {

                return slot < 27;
            }

            @Override
            protected void onContentsChanged(int slot) {

                super.onContentsChanged(slot);
            }
*/
        };
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("mainMenuInventory",itemHandler.serializeNBT(registries));
        tag.putInt("ti",ti);
        return tag;
    }


    private static mainMenuInventorySaver createFromNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        mainMenuInventorySaver newInstance= new mainMenuInventorySaver();

        newInstance.itemHandler.deserializeNBT(provider,compoundTag.getCompound("mainMenuInventory"));
        newInstance.ti = compoundTag.getInt("ti");
        return newInstance;
    }

    public static mainMenuInventorySaver create(){

        System.out.println("create:new");
        return new mainMenuInventorySaver();
    }

    private static Factory<mainMenuInventorySaver> type = new Factory<>(
            mainMenuInventorySaver::create, // 若不存在 'BlockStateSaverAndLoader' 则创建
            mainMenuInventorySaver::createFromNbt, // 若存在 'BlockStateSaverAndLoader' NBT, 则调用 'createFromNbt' 传入参数
            null // 此处理论上应为 'DataFixTypes' 的枚举，但我们直接传递为空(null)也可以
    );

    public static mainMenuInventorySaver getServerState(MinecraftServer server) {
        // (注：如需在任意维度生效，请使用 'World.OVERWORLD' ，不要使用 'World.END' 或 'World.NETHER')
        mainMenuInventorySaver state = new mainMenuInventorySaver();

        if(server!=null) {

            DimensionDataStorage persistentStateManager = server.getLevel(Level.OVERWORLD).getDataStorage();

            // 当第一次调用了方法 'getOrCreate' 后，它会创建新的 'BlockStateSaverAndLoader' 并将其存储于  'PersistentStateManager' 中。
            //  'getOrCreate' 的后续调用将本地的 'BlockStateSaverAndLoader' NBT 传递给 'BlockStateSaverAndLoader::createFromNbt'。
            state = persistentStateManager.computeIfAbsent(type, storeName);


            // 若状态未标记为脏(dirty)，当 Minecraft 关闭时， 'writeNbt' 不会被调用，相应地，没有数据会被保存。
            // 从技术上讲，只有在事实上发生数据变更时才应当将状态标记为脏(dirty)。
            // 但大多数开发者和模组作者会对他们的数据未能保存而感到困惑，所以不妨直接使用 'markDirty' 。
            // 另外，这只将对应的布尔值设定为 TRUE，代价是文件写入磁盘时模组的状态不会有任何改变。(这种情况非常少见)
            state.setDirty();
            return state;
        }
        return null;
    }

}