package com.theXu.terracraft0323.tag;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CAN_BALL_REPLACED =
            tag("can_ball_replaced");

        public static final TagKey<Block> INCORRECT_FOR_YU_YAN_TOOL =
                tag("incorrect_for_yu_yan_tool");
        public static final TagKey<Block> INCORRECT_FOR_SHEN_SHENG_TOOL =
                tag("incorrect_for_shen_sheng_tool");
        public static final TagKey<Block> INCORRECT_FOR_BA_JIN_TOOL =
                tag("incorrect_for_ba_jin_tool");
        public static final TagKey<Block> INCORRECT_FOR_YE_LV_TOOL =
                tag("incorrect_for_ye_lv_tool");


        public static final TagKey<Block> NEED_YU_YAN_TOOL =
                tag("need_yu_yan_tool");
        public static final TagKey<Block> NEED_SHEN_SHENG_TOOL =
                tag("need_shen_sheng_tool");
        public static final TagKey<Block> NEED_BA_JIN_TOOL =
                tag("need_ba_jin_tool");
        public static final TagKey<Block> NEED_YE_LV_TOOL =
                tag("need_ye_lv_tool");





        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> JEWELRY =
                tag("jewelry");

        public static final TagKey<Item> JEWELRY_FLY =
                tag("jewelry_fly");




        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(NeoMod.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }
    }

}
