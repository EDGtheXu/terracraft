package com.theXu.terracraft0323.network.packet.C2S;

import com.theXu.terracraft0323.NeoMafishMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ThrowPowerC2SPacket implements CustomPacketPayload {
    public static float throw_power;
    public  float throw_power_message;
    public static final Type<ThrowPowerC2SPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(NeoMafishMod.MODID,"throw_power_c2s"));
    public static final StreamCodec<FriendlyByteBuf,ThrowPowerC2SPacket> STREAM_CODEC =
            CustomPacketPayload.codec(ThrowPowerC2SPacket::write,ThrowPowerC2SPacket::new);
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
    public ThrowPowerC2SPacket(float throw_power){
        this.throw_power_message = throw_power;
    }

    public ThrowPowerC2SPacket(FriendlyByteBuf buf){
        this.throw_power_message = buf.readFloat();
    }

    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeFloat(this.throw_power_message);
    }
    public static void handle(final ThrowPowerC2SPacket data, final IPayloadContext context){
        context.enqueueWork(()->{
            ThrowPowerC2SPacket.throw_power = data.throw_power_message;
        });
    }
}
