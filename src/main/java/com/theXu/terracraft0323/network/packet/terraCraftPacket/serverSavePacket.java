package com.theXu.terracraft0323.network.packet.terraCraftPacket;

import com.mojang.logging.LogUtils;
import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.magicStoreCraft.magicStoreSaver;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class serverSavePacket implements CustomPacketPayload {

    public int slotIndex;


    public static final Type<serverSavePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "packet.magicsave"));


    private void write(FriendlyByteBuf buf) {
        buf.writeInt(this.slotIndex);
    }

    public serverSavePacket(FriendlyByteBuf buf) {
        this.slotIndex = buf.readInt();

    }

    public serverSavePacket(int id){
        this.slotIndex = id;
    }

    public static void receive(final serverSavePacket data, final IPayloadContext context) {

        var list = magicStoreSaver.get(context.player().level());
        if(data.slotIndex < 10000) {//约定10000以下为存储
            int index = data.slotIndex - 2;
            ItemStack it = context.player().getInventory().getItem(index);
            LogUtils.getLogger().info(index + " " + it.getDisplayName().getString());
            list.putItem(it.copy());
            context.player().getInventory().removeItem(it);
        }
        else{//10000以上为取出所有
            int index  = data.slotIndex - 10000;
            ItemStack fetch = list.removeItem(index);
            context.player().getInventory().add(fetch);

        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<FriendlyByteBuf, serverSavePacket> STREAM_CODEC =
            CustomPacketPayload.codec(serverSavePacket::write, serverSavePacket::new);
}