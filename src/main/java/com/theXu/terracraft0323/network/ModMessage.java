package com.theXu.terracraft0323.network;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.network.packet.C2S.*;
import com.theXu.terracraft0323.network.packet.S2C.BellSoundS2CPacket;
import com.theXu.terracraft0323.network.packet.S2C.NeverGonnaS2CPacket;
import com.theXu.terracraft0323.network.packet.menuhandler.serverMenuPacket;
import com.theXu.terracraft0323.network.packet.terraCraftPacket.serverCraftPacket;
import com.theXu.terracraft0323.network.packet.terraCraftPacket.serverSavePacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = NeoMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModMessage {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(NeoMod.MODID);
        registrar.playBidirectional(
                ThrowPowerC2SPacket.TYPE,
                ThrowPowerC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        null,
                        ThrowPowerC2SPacket::handle
                )
        );


        registrar.playBidirectional(
                SheepBreedingC2SPacket.TYPE,
                SheepBreedingC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        null,
                        SheepBreedingC2SPacket::handle
                )
        );

        registrar.playBidirectional(
                GameOptionsC2SPacket.TYPE,
                GameOptionsC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<GameOptionsC2SPacket>(
                        null,
                        GameOptionsC2SPacket::handle
                )
        );

        registrar.playBidirectional(
                FuC2SPacket.TYPE,
                FuC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        null,
                        FuC2SPacket::handle
                )
        );

        registrar.playBidirectional(
                ShieldDashC2SPacket.TYPE,
                ShieldDashC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        null,
                        ShieldDashC2SPacket::handle
                )
        );



        registrar.playBidirectional(
                BowDashC2SPacket.TYPE,
                BowDashC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        null,
                        BowDashC2SPacket::receive
                )
        );


        // server to client

        registrar.playBidirectional(
                BellSoundS2CPacket.TYPE,
                BellSoundS2CPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        BellSoundS2CPacket::handle,
                        null
                )
        );

        registrar.playBidirectional(
                NeverGonnaS2CPacket.TYPE,
                NeverGonnaS2CPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<NeverGonnaS2CPacket>(
                        NeverGonnaS2CPacket::handle,
                        null
                )
        );

        registrar.playBidirectional(
                serverMenuPacket.TYPE,
                serverMenuPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<serverMenuPacket>(
                        null,
                        serverMenuPacket::receive
                )
        );

        registrar.playBidirectional(
                serverCraftPacket.TYPE,
                serverCraftPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<serverCraftPacket>(
                        null,
                        serverCraftPacket::receive
                )
        );

        registrar.playBidirectional(
                serverSavePacket.TYPE,
                serverSavePacket.STREAM_CODEC,
                new DirectionalPayloadHandler<serverSavePacket>(
                        null,
                        serverSavePacket::receive
                )
        );

    }
}
