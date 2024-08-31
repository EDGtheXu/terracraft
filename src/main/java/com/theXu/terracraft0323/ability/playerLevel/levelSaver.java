package com.theXu.terracraft0323.ability.playerLevel;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

public class levelSaver extends SavedData {

    public playerLevel levels;

    public int ti;
    private static final String storeName = NeoMod.MODID + "_levels";


    public levelSaver(){
        ti = 0;
        levels = new playerLevel();
        System.out.println("new level saver\n\n\n\n\n");
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("playerLevels",levels.toNBTData());
        tag.putInt("ti",ti);
        System.out.println("save nbt\n\n\n\n\n\n\n\n");
        return tag;
    }


    private static levelSaver createFromNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        levelSaver newInstance= new levelSaver();

        newInstance.levels.loadNBTData(compoundTag.getCompound("playerLevels"));
        newInstance.ti = compoundTag.getInt("ti");

        System.out.println("create nbt\n\n\n\n\n\n\n\n");
        return newInstance;
    }

    public static levelSaver create(){

        return new levelSaver();
    }

    private static Factory<levelSaver> type = new Factory<>(
            levelSaver::create, // 若不存在 'BlockStateSaverAndLoader' 则创建
            levelSaver::createFromNbt, // 若存在 'BlockStateSaverAndLoader' NBT, 则调用 'createFromNbt' 传入参数
            null // 此处理论上应为 'DataFixTypes' 的枚举，但我们直接传递为空(null)也可以
    );

    public static levelSaver getServerState(MinecraftServer server) {
        // (注：如需在任意维度生效，请使用 'World.OVERWORLD' ，不要使用 'World.END' 或 'World.NETHER')
        levelSaver state = new levelSaver();

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