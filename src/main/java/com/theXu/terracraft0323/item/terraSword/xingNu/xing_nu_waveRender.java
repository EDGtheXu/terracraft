package com.theXu.terracraft0323.item.terraSword.xingNu;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.item.terraSword.iceSword.ice_sword_waveRender;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.jetbrains.annotations.NotNull;

public class xing_nu_waveRender extends ice_sword_waveRender {

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractHurtingProjectile pEntity) {
        return  ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "textures/entity/wave/xing_nu_wave.png");
    }

    public xing_nu_waveRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

}
