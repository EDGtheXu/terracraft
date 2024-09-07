package com.theXu.terracraft0323.geo.renderer;

import com.theXu.terracraft0323.geo.entity.testMonster;
import com.theXu.terracraft0323.geo.model.monsterModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class monsterRenderer  extends GeoEntityRenderer<testMonster> {
    public monsterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new monsterModel());
    }
}