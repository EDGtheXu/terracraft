package com.theXu.terracraft0323.creature.monster.boss.kulouwang;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class kulouwangHandModel extends DefaultedEntityGeoModel<kulouwangHand> {
    public kulouwangHandModel () {
        super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "ku_lou_wang_hand_left"));
    }

    // We want our model to render using the translucent render type
    @Override
    public RenderType getRenderType(kulouwangHand animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}