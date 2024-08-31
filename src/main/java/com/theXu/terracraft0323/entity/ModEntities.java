package com.theXu.terracraft0323.entity;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan.kesuluzhiyan;
import com.theXu.terracraft0323.item.terraSummon.ke_yan_fa_zhang.ke_yan_fa_zhang;
import com.theXu.terracraft0323.item.terraSummon.ke_yan_fa_zhang.ke_yan_fa_zhang_summon;
import com.theXu.terracraft0323.item.terraSword.iceSword.ice_sword_wave;
import com.theXu.terracraft0323.item.terraSword.tailaren.tai_la_ren_wave;
import com.theXu.terracraft0323.item.terraSword.waveHandler.empty;
import com.theXu.terracraft0323.item.terraSword.xingNu.xing_nu_wave;
import com.theXu.terracraft0323.item.terraSword.yongYeSword.yong_ye_ren_wave;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, NeoMod.MODID);

    public static final DeferredHolder<EntityType<?>,EntityType<TNTProjectileEntity>> TNT_PROJECTILE = ENTITY_TYPES.register("tnt_projectile",
            ()-> EntityType.Builder.<TNTProjectileEntity>of(TNTProjectileEntity::new, MobCategory.MISC).sized(0.25f,0.25f).build("tnt_projectile"));

    public static final DeferredHolder<EntityType<?>,EntityType<StoneBallProjectileEntity>> STONE_PROJECTILE = ENTITY_TYPES.register("stone_projectile",
            ()-> EntityType.Builder.<StoneBallProjectileEntity>of(StoneBallProjectileEntity::new, MobCategory.MISC).sized(0.25f,0.25f).build("stone_projectile"));




//    public static final DeferredHolder<EntityType<?>,EntityType<FuProjectileEntity>> FU_PROJECTILE = ENTITY_TYPES.register("fu_projectile",
//            ()-> EntityType.Builder.<FuProjectileEntity>of(TNTProjectileEntity::new, MobCategory.MISC).sized(0.25f,0.25f).build(null));

//    public static final DeferredHolder<EntityType<?>,TNTProjectileEntity> FIREWORK_ARROW = ENTITY_TYPES.register("firework_arrow",
//            ()-> EntityType.Builder.of())

    public static final DeferredHolder<EntityType<?>,EntityType<LightningProjectileEntity>> LIGHTNING_PROJECTILE = ENTITY_TYPES.register("lightning_projectile",
            ()-> EntityType.Builder.<LightningProjectileEntity>of(LightningProjectileEntity::new,MobCategory.MISC).sized(0.25f,0.25f).build("lightning_projectile"));

/*
    public static final DeferredHolder<EntityType<?>,EntityType<LightningProjectileEntity>> LIGHTNING_PROJECTILE = ENTITY_TYPES.register("lightning_projectile",
            ()-> EntityType.Builder.<LightningProjectileEntity>of(LightningProjectileEntity::new,MobCategory.MISC).sized(0.25f,0.25f).build("lightning_projectile"));
*/



//TerraCraft

    //wave

    public static final Supplier<EntityType<empty>> EMPTY_ENTITY =
            ENTITY_TYPES.register("empty_entity",
                    () ->EntityType.Builder.<empty>of(empty::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .setTrackingRange(4)
                            .setUpdateInterval(10)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"empty_entity").toString())
            );


    public static final Supplier<EntityType<tai_la_ren_wave>> TAI_LA_REN_WAVE =
            ENTITY_TYPES.register("tai_la_ren_wave",
                    () ->EntityType.Builder.<tai_la_ren_wave>of(tai_la_ren_wave::new, MobCategory.MISC)
                            .sized(4F, 4F)
                            .setTrackingRange(4)
                            .setUpdateInterval(1)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"tai_la_ren").toString())
            );
    public static final Supplier<EntityType<yong_ye_ren_wave>> YONG_YE_REN_WAVE =
            ENTITY_TYPES.register("yong_ye_ren_wave",
                    () ->EntityType.Builder.<yong_ye_ren_wave>of(yong_ye_ren_wave::new, MobCategory.MISC)
                            .sized(10F, 2F)
                            .setTrackingRange(4)
                            .setUpdateInterval(10)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"yong_ye_ren").toString())
            );

    public static final Supplier<EntityType<ice_sword_wave>> ICE_SWORD_WAVE =
            ENTITY_TYPES.register("ice_sword_wave",
                    () ->EntityType.Builder.<ice_sword_wave>of(ice_sword_wave::new, MobCategory.MISC)
                            .sized(1F, 1F)
                            .setTrackingRange(4)
                            .setUpdateInterval(10)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"ice_sword").toString())
            );

    public static final Supplier<EntityType<xing_nu_wave>> XING_NU_WAVE =
            ENTITY_TYPES.register("xing_nu_wave",
                    () ->EntityType.Builder.<xing_nu_wave>of(xing_nu_wave::new, MobCategory.MISC)
                            .sized(1F, 1F)
                            .setTrackingRange(4)
                            .setUpdateInterval(10)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"xing_nu").toString())
            );




//creature
    public static final Supplier<EntityType<kesuluzhiyan>> KE_YAN =
            ENTITY_TYPES.register("ke_yan",
                    () ->EntityType.Builder.<kesuluzhiyan>of(kesuluzhiyan::new, MobCategory.MISC)
                            .sized(4F, 4F)
                            .setTrackingRange(200)
                            .setUpdateInterval(1)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"ke_yan").toString())
            );


//summon
    public static final Supplier<EntityType<ke_yan_fa_zhang_summon>> KE_YAN_SUMMON =
            ENTITY_TYPES.register("ke_yan_fa_zhang_summon",
                    () ->EntityType.Builder.<ke_yan_fa_zhang_summon>of(ke_yan_fa_zhang_summon::new, MobCategory.MISC)
                            .sized(1F, 1F)
                            .setTrackingRange(10)
                            .setUpdateInterval(1)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,"ke_yan_fa_zhang_summon").toString())
            );












    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
