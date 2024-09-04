package com.theXu.terracraft0323.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.ability.playerLevel.abilityRegister;
import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.mixinhelper.BellBlockDelayMixinHelper;
import com.theXu.terracraft0323.network.packet.menuhandler.serverMenuPacket;
import com.theXu.terracraft0323.ui.gui.caveShowRender;
import com.theXu.terracraft0323.ui.gui.itemInfoScreen;
import net.minecraft.client.GuiMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Vector3i;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.List;

@EventBusSubscriber(modid = NeoMod.MODID,value = Dist.CLIENT)
public class PlayerClientEvent {
    @SubscribeEvent
    public static void onPlayerDisConnection(EntityLeaveLevelEvent event){
        //客户端已经成功连接到服务器
        // 清空hashmap
        BellBlockDelayMixinHelper.BellBlockEntityMap.clear();
        BellBlockDelayMixinHelper.HitCoolDownMap.clear();
        BellBlockDelayMixinHelper.DirectionMap.clear();



    }

    @SubscribeEvent
    public static void renderGui(RenderGuiEvent.Post event) {

        itemInfoScreen iI = new itemInfoScreen(Minecraft.getInstance());
        iI.render(event.getGuiGraphics());


    }



    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton.Post event) {
        //System.out.println(event.getKey());
        //System.out.println(event.getAction());
        //System.out.println(event.getButton());

    }


    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        //System.out.println(event.getKey());
        if(event.getKey()==32){
            abilityRegister.get().inputJumping = true;

        }else if(registerKeyEvent.KeyBinding.MAIN_MENU_KEY.consumeClick()){
            System.out.println("input I");
            PacketDistributor.sendToServer(new serverMenuPacket(1));
        }

    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.MouseScrollingEvent event) {
        System.out.println(event.getScrollDeltaX());
        System.out.println(event.getScrollDeltaY());
    }

    static int tick = 20;
    private static VertexBuffer vertexBuffer;
    static caveShowRender blockGen;


    @SubscribeEvent
    public static void onWorldRenderLast(RenderLevelStageEvent event){
        if(!Minecraft.getInstance().player.hasEffect(ModEffects.CAVE_SHOW))
            return;
        boolean ifRefresh = false;

        if(--tick<0){
            tick = 200;
            blockGen = new caveShowRender(Minecraft.getInstance().player);
            blockGen.genBlocks();
            ifRefresh = true;
        }
        if(blockGen==null) return;


        Vec3 playerPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        if(ifRefresh){
            var mapList = blockGen.getBlockMap();

            vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
            Tesselator tessellator = Tesselator.getInstance();
            BufferBuilder buffer = tessellator.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);

            Map<Block,List<BlockPos>> centers = new LinkedHashMap<>();

            for(var n : mapList.entrySet()) {
                //初始化键
                centers.put(n.getKey(),new ArrayList<>());

                Color color = caveShowRender.targets.get(n.getKey());
                int colorInt = color.getRGB();
                if(n.getValue()==null)return;
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                int a = (int) (255*0.2);

                for (net.minecraft.core.BlockPos blockProps : n.getValue()) {
                    if (blockProps == null) {
                        return;
                    }

                    final float size = 1.0f;
                    final int x = blockProps.getX(),
                            y = blockProps.getY(),
                            z = blockProps.getZ();

                    //相近方块禁止渲染
                    boolean ifNear = false;
                    //double minDistance = 100;
                    for(BlockPos pos : centers.get(n.getKey())){
                        double distance = pos.distSqr(blockProps);
                        if(distance < 25){
                            ifNear = true;
                            break;
                        }
                        //minDistance
                    }
                    if(ifNear){
                        continue;
                    }
                    centers.get(n.getKey()).add(blockProps);



                    buffer.addVertex(x, y + size, z).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y + size, z).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y + size, z).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y + size, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y + size, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x, y + size, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x, y + size, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x, y + size, z).setColor(r,g,b,a);

                    // BOTTOM
                    buffer.addVertex(x + size, y, z).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x, y, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x, y, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x, y, z).setColor(r,g,b,a);
                    buffer.addVertex(x, y, z).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y, z).setColor(r,g,b,a);

                    // Edge 1
                    buffer.addVertex(x + size, y, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y + size, z + size).setColor(r,g,b,a);

                    // Edge 2
                    buffer.addVertex(x + size, y, z).setColor(r,g,b,a);
                    buffer.addVertex(x + size, y + size, z).setColor(r,g,b,a);

                    // Edge 3
                    buffer.addVertex(x, y, z + size).setColor(r,g,b,a);
                    buffer.addVertex(x, y + size, z + size).setColor(r,g,b,a);

                    // Edge 4
                    buffer.addVertex(x, y, z).setColor(r,g,b,a);
                    buffer.addVertex(x, y + size, z).setColor(r,g,b,a);



                }

            }



            MeshData build = buffer.build();
            if (build == null) {
                vertexBuffer = null;
                return;
            } else {
                vertexBuffer.bind();
                vertexBuffer.upload(build);
                VertexBuffer.unbind();
            }
        }






        if (vertexBuffer != null) {


            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glDisable(GL11.GL_DEPTH_TEST);

            RenderSystem.setShader(GameRenderer::getPositionColorShader);


            PoseStack poseStack = event.getPoseStack();
            poseStack.pushPose();

            poseStack.mulPose(event.getModelViewMatrix());
            poseStack.translate(-playerPos.x(), -playerPos.y(), -playerPos.z());

            vertexBuffer.bind();
            vertexBuffer.drawWithShader(poseStack.last().pose(), event.getProjectionMatrix(), RenderSystem.getShader());



            VertexBuffer.unbind();
            poseStack.popPose();

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);

        }


    }


}
