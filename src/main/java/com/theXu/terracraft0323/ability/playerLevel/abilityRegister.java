package com.theXu.terracraft0323.ability.playerLevel;

import com.theXu.terracraft0323.ServerManager;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;


public class abilityRegister implements ICapabilityProvider<Player,Void,playerLevel> , INBTSerializable<CompoundTag> {

    Player player;
    private static levelSaver levels = null;
    public static levelSaver initLevel(){
        levels = levelSaver.getServerState(ServerManager.getServerInstance());
        return levels;
    }
    public static playerLevel get(){

        //return  levelSaver.getServerState(ServerManager.getServerInstance()).levels;
        if (levels != null) {
            return levels.levels;
        }
        return null;

    }

    @Override
    public @Nullable playerLevel getCapability(Player object, Void context) {
        player = object;
        return get();
    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        get().saveNBTData(nbt);
        System.out.println("save");
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        System.out.println("load");
        get().loadNBTData(nbt);
    }
}
