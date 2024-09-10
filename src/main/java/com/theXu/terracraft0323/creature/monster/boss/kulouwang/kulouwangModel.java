package com.theXu.terracraft0323.creature.monster.boss.kulouwang;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class kulouwangModel extends DefaultedEntityGeoModel<kulouwang> {
    public kulouwangModel() {
        super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "ku_lou_wang"));
    }

    // We want our model to render using the translucent render type
    @Override
    public RenderType getRenderType(kulouwang animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}