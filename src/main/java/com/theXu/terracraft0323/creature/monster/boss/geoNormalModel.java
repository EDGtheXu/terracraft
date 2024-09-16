package com.theXu.terracraft0323.creature.monster.boss;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class geoNormalModel<T extends terraBossBase> extends DefaultedEntityGeoModel<T> {
    public geoNormalModel(String path) {
        super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, path));
    }

    @Override
    public RenderType getRenderType(T animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}