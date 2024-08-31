package com.theXu.terracraft0323.network.packet.terraCraftPacket;

import com.mojang.logging.LogUtils;
import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.ServerManager;
import com.theXu.terracraft0323.recipe.craftHelper;
import com.theXu.terracraft0323.recipe.terraRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class serverCraftPacket implements CustomPacketPayload {

    public int recipeID;


    public static final Type<serverCraftPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "packet.docraft"));
    public static final StreamCodec<FriendlyByteBuf, serverCraftPacket> STREAM_CODEC =
            CustomPacketPayload.codec(serverCraftPacket::write, serverCraftPacket::new);

    private void write(FriendlyByteBuf buf) {
        buf.writeInt(this.recipeID);
    }

    public serverCraftPacket(FriendlyByteBuf buf) {
        this.recipeID = buf.readInt();

    }

    public serverCraftPacket(int id){
        this.recipeID = id;
    }

    public static void receive(final serverCraftPacket data, final IPayloadContext context) {

        craftHelper helper = new craftHelper(context.player());
        helper.doCraft(terraRecipe.getInstance().entrySet().stream().toList().get(data.recipeID));


    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}