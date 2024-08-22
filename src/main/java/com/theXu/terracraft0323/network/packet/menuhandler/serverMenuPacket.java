package com.theXu.terracraft0323.network.packet.menuhandler;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.mixinhelper.BowDashMixinHelper;
import com.theXu.terracraft0323.ui.jewelrySlots.terraBag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class serverMenuPacket implements CustomPacketPayload {

    public int data;
    public static final Type<serverMenuPacket> TYPE = new Type<serverMenuPacket>(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"openmainmenu"));
    public static final StreamCodec<FriendlyByteBuf,serverMenuPacket> STREAM_CODEC =
            CustomPacketPayload.codec(serverMenuPacket::write,serverMenuPacket::new);

    private void write(FriendlyByteBuf buf) {
        buf.writeInt(this.data);
    }

    public serverMenuPacket(FriendlyByteBuf buf) {
        this.data = buf.readInt();
    }

    public serverMenuPacket(int data){
        this.data = data;
    }

    public static void receive(final serverMenuPacket data, final IPayloadContext context) {

        //打开菜单后不会接受key event
        if(context.player().hasContainerOpen()){
            context.player().closeContainer();
            return;
        }
        context.player().openMenu(
                new SimpleMenuProvider(
                        (containerId, playerInventory, p) -> new terraBag(containerId, playerInventory),
                        Component.translatable("menu.title.terraInventory")
                )
        );
        
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
