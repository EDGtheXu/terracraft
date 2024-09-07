package com.theXu.terracraft0323.item;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.block.ModBlock;
import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.geo.item.GeckoArmorItem;
import com.theXu.terracraft0323.geo.item.WolfArmorItem;
import com.theXu.terracraft0323.geo.item.shenShengArmorItem;
import com.theXu.terracraft0323.item.custom.*;
import com.theXu.terracraft0323.item.menuItem.menuItem;
import com.theXu.terracraft0323.item.terraBow.shenShengLianNu.shen_sheng_lian_nu;
import com.theXu.terracraft0323.item.terraJewelry.boot.bootBase;
import com.theXu.terracraft0323.item.terraJewelry.defence.jinMaZhang;
import com.theXu.terracraft0323.item.terraJewelry.fly.flyBase;
import com.theXu.terracraft0323.item.terraSummon.ke_yan_fa_zhang.ke_yan_fa_zhang;
import com.theXu.terracraft0323.item.terraSword.iceSword.ice_sword;
import com.theXu.terracraft0323.item.terraSword.tailaren.tai_la_ren;
import com.theXu.terracraft0323.item.terraSword.xingNu.xing_nu;
import com.theXu.terracraft0323.item.terraSword.yongYeSword.yong_ye_ren;
import com.theXu.terracraft0323.magicStoreCraft.geo.item.magicStorageItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoMod.MODID);


    public static final DeferredItem<Item> MAFISH = ITEMS.register("mafish",()->
            new Item(new Item.Properties().stacksTo(1).fireResistant().food(ModFoods.Mafish)));



    public static final DeferredItem<Item> RUBY = registerItem("ruby",()-> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> RAW_RUBY=registerItem("raw_ruby",()-> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> BREAD_SWORD = registerItem("bread_sword", BreadSwordItem::new);
    public static final DeferredItem<Item> BREAD_SWORD_HOT = registerItem("bread_sword_hot", BreadSwordHotItem::new);
    public static final DeferredItem<Item> BREAD_SWORD_VERY_HOT = registerItem("bread_sword_very_hot", BreadSwordVeryHotItem::new);
    public static final DeferredItem<Item>  TNT_BALL = registerItem("tnt_ball", ()-> new TNTBallItem(new Item.Properties()));
    public static final DeferredItem<Item>  STONE_BALL = registerItem("stone_ball", ()-> new StoneBallItem(new Item.Properties()));
    public static final DeferredItem<Item> ZHU_GE = registerItem("zhuge",()-> new ZhuGeItem(new Item.Properties().stacksTo(1).durability(425)));
    public static final DeferredItem<Item> POISON_SWORD = registerItem("poison_sword",
            ()->  new PoisonSwordItem(Tiers.DIAMOND, new Item.Properties().food(ModFoods.POISON_SWORD)));
    public static final DeferredItem<Item> LIGHTNING_BALL = registerItem("lightning_ball",
            ()-> new LightningBallItem(new Item.Properties()));
    public static final DeferredItem<Item> LIGHTNING_ITEM = registerItem("lightning_item",
            ()-> new LightningItem(Tiers.NETHERITE,new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<Item> CHEESE_BERGER = registerItem("cheese_berger",
            ()-> new CheeseBergerItem(new Item.Properties().food(ModFoods.CHEESE_BERGER)));
    public static final DeferredItem<Item> COLLIABLE = registerItem("colliable",
            ()-> new ColliableItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MATH_SWORD = registerItem("math_sword",
            ()-> new MathSwordItem(Tiers.NETHERITE, new Item.Properties()));

    //public static final DeferredItem<Item> VILLAGER_ITEM = registerItem("villager_item",() -> new VillagerItem(new Item.Properties().food(ModFoods.VILLAGER_ITEM).stacksTo(1)));
    //public static final DeferredItem<Item> LLAMA_ITEM = registerItem("llama_item", ()-> new LlamaItem(new Item.Properties()));
    //public static final DeferredItem<Item> IRON_GOLEM_ITEM = registerItem("iron_golem_item",()-> new Item(new Item.Properties().food(ModFoods.IRON_GOLEM_ITEM).stacksTo(1)));

    public static final DeferredItem<Item> RUBY_STAFF=registerItem("ruby_staff",
            ()-> new RubyStuffItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SWITCH = registerItem("switch",() -> new SwitchItem(new Item.Properties().food(ModFoods.SWITCH)));
    public static final DeferredItem<Item> RTX4090 = registerItem("rtx4090", ()-> new RTX4090Item(Tiers.NETHERITE,new Item.Properties()));
    public static final DeferredItem<Item> MILK_FLESH = registerItem("milk_flesh",()->new MilkFleshItem(new Item.Properties().food(ModFoods.MILK_FLESH)));
    public static final DeferredItem<Item> STARGAZY_PIE = registerItem("stargazy_pie",
            ()-> new StargazyPieItem(new Item.Properties().food(ModFoods.STARGAZY_PIE)));
    public static final DeferredItem<Item> TIME_STOP = registerItem("time_stop",
            ()-> new TimeStopItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> FU = registerItem("fu", FuItem::new);




//terraCraft


    //blockitem
    public static final DeferredItem<Item> MAGIC_BLOCK_ITEM = registerItem("magic_storage_block",
        ()->new magicStorageItem(ModBlock.MAGIC_STORE.get()));

    //geo entity
    public static final DeferredHolder<Item, DeferredSpawnEggItem> BIKE_SPAWN_EGG = ITEMS.register("bike_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.BIKE, 0xD3E3E6, 0xE9F1F5, new Item.Properties()));
    public static final DeferredHolder<Item, DeferredSpawnEggItem> RACE_CAR_SPAWN_EGG = ITEMS.register("race_car_spawn_egg",() ->  new DeferredSpawnEggItem(ModEntities.RACE_CAR, 0x9E1616, 0x595959, new Item.Properties()));
    //public static final DeferredHolder<Item, DeferredSpawnEggItem> PARASITE_SPAWN_EGG = ITEMS.register("parasite_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.PARASITE, 0x302219, 0xACACAC, new Item.Properties()));
    //public static final DeferredHolder<Item, DeferredSpawnEggItem> MUTANT_ZOMBIE_SPAWN_EGG = ITEMS.register("mutant_zombie_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.MUTANT_ZOMBIE, 0x3C6236, 0x579989, new Item.Properties()));
    public static final DeferredHolder<Item, DeferredSpawnEggItem> FAKE_GLASS_SPAWN_EGG = ITEMS.register("fake_glass_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.FAKE_GLASS, 0xDD0000, 0xD8FFF7, new Item.Properties()));
    public static final DeferredHolder<Item, DeferredSpawnEggItem> COOL_KID_SPAWN_EGG = ITEMS.register("cool_kid_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.COOL_KID, 0x5F2A31, 0x6F363E, new Item.Properties()));



    //菜单

    public static final DeferredItem<Item> MAIN_MENU = registerItem("main_menu",
        ()->new menuItem(new Item.Properties().stacksTo(1)));



//武器

    //近战
    public static final DeferredItem<Item> YONG_YE_REN = registerItem("sword/yong_ye_ren",
            yong_ye_ren::new);
    public static final DeferredItem<Item> TAI_LA_REN =registerItem("sword/tai_la_ren",
            tai_la_ren::new);
    public static final DeferredItem<Item>  ICE_SWORD = registerItem("sword/ice_sword",
            ice_sword::new);
    public static final DeferredItem<Item> XING_NU = registerItem("sword/xing_nu",
            xing_nu::new);

    //远程
    public static final DeferredItem<Item>  SHEN_SHENG_LIAN_NU= registerItem("bow/shen_sheng_lian_nu",
            ()->new shen_sheng_lian_nu(50));


    //召唤
    public static final DeferredItem<Item>  KE_YAN_FA_ZHANG= registerItem("summon/ke_yan_fa_zhang",
            ke_yan_fa_zhang::new);


//盔甲
    public static final DeferredHolder<Item, WolfArmorItem> WOLF_ARMOR_HELMET = ITEMS.register("wolf_armor_helmet", () -> new WolfArmorItem(ModTiers.WOLF_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredHolder<Item, WolfArmorItem> WOLF_ARMOR_CHESTPLATE = ITEMS.register("wolf_armor_chestplate", () -> new WolfArmorItem(ModTiers.WOLF_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredHolder<Item, WolfArmorItem> WOLF_ARMOR_LEGGINGS = ITEMS.register("wolf_armor_leggings", () -> new WolfArmorItem(ModTiers.WOLF_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredHolder<Item, WolfArmorItem> WOLF_ARMOR_BOOTS = ITEMS.register("wolf_armor_boots", () -> new WolfArmorItem(ModTiers.WOLF_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, GeckoArmorItem> GECKO_ARMOR_HELMET = ITEMS.register("gecko_armor_helmet", () -> new GeckoArmorItem(ModTiers.GECKO_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredHolder<Item, GeckoArmorItem> GECKO_ARMOR_CHESTPLATE = ITEMS.register("gecko_armor_chestplate", () -> new GeckoArmorItem(ModTiers.GECKO_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredHolder<Item, GeckoArmorItem> GECKO_ARMOR_LEGGINGS = ITEMS.register("gecko_armor_leggings", () -> new GeckoArmorItem(ModTiers.GECKO_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredHolder<Item, GeckoArmorItem> GECKO_ARMOR_BOOTS = ITEMS.register("gecko_armor_boots", () -> new GeckoArmorItem(ModTiers.GECKO_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, shenShengArmorItem> SHEN_SHENG_ARMOR_HELMET = ITEMS.register("armor/shen_sheng_armor_helmet", () -> new shenShengArmorItem(ModTiers.SHEN_SHENG_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredHolder<Item, shenShengArmorItem> SHEN_SHENG_ARMOR_CHESTPLATE = ITEMS.register("armor/shen_sheng_armor_chestplate", () -> new shenShengArmorItem(ModTiers.SHEN_SHENG_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredHolder<Item, shenShengArmorItem> SHEN_SHENG_ARMOR_LEGGINGS = ITEMS.register("armor/shen_sheng_armor_leggings", () -> new shenShengArmorItem(ModTiers.SHEN_SHENG_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredHolder<Item, shenShengArmorItem> SHEN_SHENG_ARMOR_BOOTS = ITEMS.register("armor/shen_sheng_armor_boots", () -> new shenShengArmorItem(ModTiers.SHEN_SHENG_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties()));


//材料
    public static final DeferredItem<Item> SHEN_SHENG_DING = ITEMS.register("material/shen_sheng_ding",()->
            new Item(new Item.Properties()));

//饰品
    public static final DeferredItem<Item>  TAI_LA_XUE = registerItem("jewelry/tai_la_xue",
            ()->new bootBase(new Item.Properties().rarity(Rarity.RARE)
                    .attributes(ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.MOVEMENT_SPEED,
                                    new AttributeModifier(
                                            ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "jew_tailaxue"),
                                            0.2,
                                            AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.ARMOR).build()

                    ),
                    2));




    public static final DeferredItem<Item>  JIN_MA_ZHANG= registerItem("jewelry/jin_ma_zhang",
            ()-> new jinMaZhang(new Item.Properties()
                    .attributes(ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.SAFE_FALL_DISTANCE,
                                    new AttributeModifier(
                                            ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "jew_jinmazhang"),
                                            1022,
                                            AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.ARMOR).build()

                    )
            ));



  //翅膀
    public static final DeferredItem<Item>  TIAN_JIE_XING_PAN = registerItem("jewelry/tian_jie_xing_pan",
            ()-> new flyBase(new Item.Properties()
                    .attributes(ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.ARMOR,
                                    new AttributeModifier(
                                            ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "jew_armor"),
                                            10,
                                            AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.ARMOR).build()

                    )
                    .attributes(ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.SAFE_FALL_DISTANCE,
                                    new AttributeModifier(
                                            ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, "jew_jinmazhang"),
                                            500,
                                            AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.ARMOR).build()

                    )
                    .rarity(Rarity.EPIC),4.5f,2f,3f));














    public static DeferredItem<Item> registerItem(String name, Supplier<Item> itemSupplier){
        return ITEMS.register(name,itemSupplier);
    }



}
