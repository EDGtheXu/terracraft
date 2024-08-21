package com.theXu.terracraft0323.item.terraSword.tailaren;


import com.theXu.terracraft0323.NeoMafishMod;
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

public class tai_la_ren_waveRender extends EntityRenderer {
    // 存储我们的模型。
    private final EntityModel<tai_la_ren_wave> flyingSwordModel;

    public tai_la_ren_waveRender(EntityRendererProvider.Context pContext) {
        super(pContext);
        //这里使用pContext.bakeLayer(FlyingSwordModel.LAYER_LOCATION)来准备ModelPart，这里的获得ModelPart是等会我们需要去注册的，通过LAYER_LOCATION注册我们的ModelPart
        flyingSwordModel = new tai_la_ren_waveModel<tai_la_ren_wave>(pContext.bakeLayer(tai_la_ren_waveModel.LAYER_LOCATION));

    }


    //这个方法返回一个ResourceLocation对象，指明了飞行剑实体的纹理文件位置。
    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(NeoMafishMod.MODID, "textures/entity/wave/tai_la_ren_wave_model_entity.png");
    }
    //重写了render方法，这个方法定义了实体在游戏中的渲染逻辑。

    @Override
    public void render(Entity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        //你的渲染应该在posh和pop之间，避免污染其他的渲染。
        pPoseStack.pushPose();
        // 绕y轴旋转45°


        float f1 = ((tai_la_ren_wave)pEntity).initForward.y;
        float f2 = ((tai_la_ren_wave)pEntity).initForward.x;


        pPoseStack.mulPose(Axis.YN.rotationDegrees(f1));
        pPoseStack.mulPose(Axis.XN.rotationDegrees(-f2));
        pPoseStack.scale(3,3,3);


        pPoseStack.translate(0,0.5,0);
        // 构建顶点
        VertexConsumer buffer = pBuffer.getBuffer(this.flyingSwordModel.renderType(this.getTextureLocation(pEntity)));
        // 调用模型的render方法进行渲染，这里的OverlayTexture下有很多类型，自己选用。
        this.flyingSwordModel.renderToBuffer(pPoseStack,buffer,pPackedLight, OverlayTexture.NO_OVERLAY);

        //横向
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(90));
        this.flyingSwordModel.renderToBuffer(pPoseStack,buffer,pPackedLight, OverlayTexture.NO_OVERLAY);



        pPoseStack.popPose();
    }



}
