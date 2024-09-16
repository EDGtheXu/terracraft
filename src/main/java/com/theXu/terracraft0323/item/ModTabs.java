package com.theXu.terracraft0323.item;

import com.theXu.terracraft0323.block.ModBlock;
import com.theXu.terracraft0323.event.registerRenderEvent;
import com.theXu.terracraft0323.item.terraBow.shenShengLianNu.shen_sheng_lian_nu;
import com.theXu.terracraft0323.item.terraSword.tailaren.tai_la_ren;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.theXu.terracraft0323.NeoMod.MODID;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
/*
    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> NEO_MAFISHMOD =
            CREATIVE_TABS.register("mafish", ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.mafish"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(()-> ModItems.MAFISH.get().getDefaultInstance())
                    .displayItems((itemDisplayParameters, output) -> {
                    //在这里添加item
                        output.accept(ModItems.MAFISH.get());
//                        output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.ONE_STEP_TEN_LINE, 1)));
//                        output.accept((ItemLike) ModEnchantments.ONE_STEP_TEN_LINE);
                        output.accept(ModBlock.WHATE_CAT_BLOCK.get());
                        output.accept(ModItems.RUBY_STAFF.get());

                        output.accept(ModItems.COLLIABLE.get());
                        output.accept(ModItems.TIME_STOP.get());

                        output.accept(ModItems.BREAD_SWORD_VERY_HOT.get());
                        output.accept(ModItems.BREAD_SWORD.get());
                        output.accept(ModItems.BREAD_SWORD_HOT.get());

                        output.accept(ModItems.LIGHTNING_ITEM.get());

                        output.accept(ModItems.TNT_BALL.get());
                        output.accept(ModItems.STONE_BALL.get());

                        output.accept(ModItems.ZHU_GE.get());
                        output.accept(ModItems.POISON_SWORD.get());
                        output.accept(ModItems.LIGHTNING_BALL.get());

                        //会崩溃
//                        output.accept(ModItems.MATH_SWORD.get());

                        output.accept(ModItems.MILK_FLESH.get());
                        output.accept(ModItems.STARGAZY_PIE.get());
                        output.accept(ModItems.CHEESE_BERGER.get());

                        output.accept(ModBlock.POTATO_TNT.get());

                        output.accept(ModItems.SWITCH.get());


                    }).build());

*/

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> NEO_TERRA =
            CREATIVE_TABS.register("terracraft0323", ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.terracraft0323"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(()-> ModItems.TAI_LA_REN.get().getDefaultInstance())
                    .displayItems((itemDisplayParameters, output) -> {
                        //在这里添加item

//TerraCraft

                        //weapon
                        //近战
                        output.accept(ModItems.MAIN_MENU.get());
                        output.accept(((tai_la_ren)ModItems.TAI_LA_REN.get()).createStack());
                        output.accept(ModItems.YONG_YE_REN.get());
                        output.accept(ModItems.ICE_SWORD.get());
                        output.accept(ModItems.XING_NU.get());
                        //远程
                        output.accept(((shen_sheng_lian_nu)ModItems.SHEN_SHENG_LIAN_NU.get()).createStack());

                        //magic store craft
                        output.accept(ModItems.MAGIC_BLOCK_ITEM.get());
                        //output.accept(ModBlock.MAGIC_STORE_BLOCK.get());


                        //jewelry
                        output.accept(ModItems.TIAN_JIE_XING_PAN.get());
                        output.accept(ModItems.TAI_LA_XUE.get());
                        output.accept(ModItems.JIN_MA_ZHANG.get());



                        //egg

                        output.accept(ModItems.BIKE_SPAWN_EGG.get());
                        output.accept(ModItems.RACE_CAR_SPAWN_EGG.get());
                        //output.accept(ModItems.PARASITE_SPAWN_EGG.get());
                        //output.accept(ModItems.MUTANT_ZOMBIE_SPAWN_EGG.get());
                        output.accept(ModItems.FAKE_GLASS_SPAWN_EGG.get());
                        output.accept(ModItems.COOL_KID_SPAWN_EGG.get());

                        //armor
                        output.accept(ModItems.GECKO_ARMOR_HELMET.get());
                        output.accept(ModItems.GECKO_ARMOR_CHESTPLATE.get());
                        output.accept(ModItems.GECKO_ARMOR_LEGGINGS.get());
                        output.accept(ModItems.GECKO_ARMOR_BOOTS.get());

                        output.accept(ModItems.WOLF_ARMOR_HELMET.get());
                        output.accept(ModItems.WOLF_ARMOR_CHESTPLATE.get());
                        output.accept(ModItems.WOLF_ARMOR_LEGGINGS.get());
                        output.accept(ModItems.WOLF_ARMOR_BOOTS.get());

                        output.accept(ModItems.SHEN_SHENG_ARMOR_HELMET.get());
                        output.accept(ModItems.SHEN_SHENG_ARMOR_CHESTPLATE.get());
                        output.accept(ModItems.SHEN_SHENG_ARMOR_LEGGINGS.get());
                        output.accept(ModItems.SHEN_SHENG_ARMOR_BOOTS.get());

                        //材料
                        output.accept(ModItems.YIN_DING.get());
                        output.accept(ModItems.GANG_DING.get());
                        output.accept(ModItems.MO_KUANG_DING.get());
                        output.accept(ModItems.YU_YAN_DING.get());
                        output.accept(ModItems.XING_HONG_DING.get());
                        output.accept(ModItems.BA_JIN_DING.get());
                        output.accept(ModItems.SHEN_SHENG_DING.get());
                        output.accept(ModItems.YE_LV_DING.get());
                        output.accept(ModItems.YE_MING_DING.get());

                        //工具
                        output.accept(ModItems.YU_YAN_PICKAXE.get());
                        output.accept(ModItems.BA_JIN_PICKAXE.get());


                        //普通方块
                        output.accept(ModBlock.YU_YAN_ORE.get());
                        output.accept(ModBlock.BA_JIN_ORE.get());


                    }).build());
}
