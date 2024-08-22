package com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan;

import com.theXu.terracraft0323.NeoMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.joml.Vector3f;

public class kesuluzhiyanRender extends EntityRenderer {
    private final EntityModel<kesuluzhiyan> model;
    public kesuluzhiyanRender(EntityRendererProvider.Context context) {
        super(context);
        model = new kesuluzhiyanModel<>(context.bakeLayer(kesuluzhiyanModel.LAYER_LOCATION));

    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"textures/creature/kesuluzhiyan.png");
    }

    @Override
    public void render(Entity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        //你的渲染应该在posh和pop之间，避免污染其他的渲染。

        pPoseStack.pushPose();
        // 绕y轴旋转45°

        var entity = ((kesuluzhiyan)pEntity);
        Vector3f rot = entity.getRot();


        pPoseStack.mulPose(Axis.YN.rotationDegrees(rot.y));
        pPoseStack.mulPose(Axis.XN.rotationDegrees(-rot.x));
        pPoseStack.scale(3,3,3);

        pPoseStack.translate(0,-0.5,0);

        // 构建顶点
        VertexConsumer buffer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        // 调用模型的render方法进行渲染，这里的OverlayTexture下有很多类型，自己选用。
        this.model.renderToBuffer(pPoseStack,buffer,pPackedLight, OverlayTexture.NO_OVERLAY);

        pPoseStack.popPose();

    }


}
