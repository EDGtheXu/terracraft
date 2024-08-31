package com.theXu.terracraft0323.event;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.ServerManager;
import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.ability.playerLevel.playerLevel;
import com.theXu.terracraft0323.recipe.terraRecipe;
import com.theXu.terracraft0323.ui.jewelrySlots.jewelryInventorySaver;
import com.theXu.terracraft0323.ui.jewelrySlots.terraBag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Pre;


@EventBusSubscriber(modid = NeoMod.MODID)
public class PlayerTickEventGen {
    public static Player globalPlayer = null;



    @SubscribeEvent
    public static void onPlayerTick(Pre event) {





        Player player = event.getEntity();
            globalPlayer = player;
            //abilityRegister.levels = levelSaver.getServerState(ServerManager.getServerInstance());


        //饰品背包tick
            terraBag.tick(player);

        //飞行翅膀

            playerLevel levels = abilityRegister.get();
            var canfly = levels.canFly;

            if(canfly){
                //System.out.println(levels.flyRemain);
                LocalPlayer lplayer = Minecraft.getInstance().player;
                player.getAbilities().flying = true;
                if(lplayer!=null){
                    if(lplayer.input==null)return;
                    if (!lplayer.input.jumping)
                        player.getAbilities().flying = false;
                    else  {

                        if(player instanceof ServerPlayer)
                            levels.flyRemain -= 1;


                        if(levels.flyRemain <= 0){//
                            player.getAbilities().flying = false;
                            Vec3 d = player.getDeltaMovement();
                            Vec3 dy = d.multiply(1,0.5,1);
                            Vec3 dxz = d.multiply(1,0,1).scale((levels.flySpeedH-1)/20);

                            d = dy.y>0? d.add(dxz):dy.add(dxz);
                            player.setDeltaMovement(d);
                        }else{
                            //System.out.println("flying");

                            Vec3 d = player.getDeltaMovement();
                            Vec3 dxz = d.multiply(1,0,1).scale((levels.flySpeedH-1)/20);
                            Vec3 dy = d.multiply(0,1,0).scale((levels.flySpeedV-1)/20);


                            d = d.add(dxz).add(dy);

                            player.setDeltaMovement(d);

                        }

                    }

                    if(player.onGround()){
                        levels.flyRemain = levels.flyMaxTime;
                        if(lplayer.input.jumping)
                            player.getAbilities().flying = true;
                    }
                }



            }

    }



    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {

            //能力属性初始化
            abilityRegister.initLevel();
            //饰品属性初始化
            terraBag.jis = jewelryInventorySaver.getServerState(ServerManager.getServerInstance());
            terraBag.tick(player);

            //制作栏初始化
            if(event.getLevel().isClientSide)
                terraRecipe.initRecipe();

        }
    }

}
