package com.theXu.terracraft0323;

import com.theXu.terracraft0323.attribute.ModAttributes;
import com.theXu.terracraft0323.block.ModBlock;
import com.theXu.terracraft0323.block.entity.ModBlockEntities;
import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.item.ModItems;
import com.theXu.terracraft0323.item.component.ModDataComponents;
import com.theXu.terracraft0323.potion.ModPotions;
import com.theXu.terracraft0323.sound.ModSounds;
import com.theXu.terracraft0323.item.ModTabs;
import com.theXu.terracraft0323.ui.modMenuType;
import com.theXu.terracraft0323.ui.blockMenuTypes;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(NeoMod.MODID)
public class NeoMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "terracraft0323";
    private static final Logger LOGGER = LogUtils.getLogger();
    public NeoMod(IEventBus modEventBus, ModContainer modContainer)
    {
        ModDataComponents.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_TABS.register(modEventBus);
        ModBlock.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);
        modMenuType.MENU_TYPES.register(modEventBus);
        blockMenuTypes.register(modEventBus);
        ModAttributes.AttributesTypes.register(modEventBus);




        modContainer.registerConfig(ModConfig.Type.COMMON,Config.SPEC);




    }
}
