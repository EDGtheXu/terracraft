package com.theXu.terracraft0323.geo.model;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.geo.entity.testMonster;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class monsterModel extends DefaultedEntityGeoModel<testMonster> {
    public monsterModel() {
        super(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "test_monster"));
    }

    // We want our model to render using the translucent render type
    @Override
    public RenderType getRenderType(testMonster animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}