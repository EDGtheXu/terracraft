package com.theXu.terracraft0323.render.itemModel;

import com.theXu.terracraft0323.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;

public class ItemModelEvent {

    public static void registerZhuGeItemProperties(){
        ItemProperties.register(
                        ModItems.ZHU_GE.get(),
                        ResourceLocation.withDefaultNamespace("pull"),
                        (stack, level, entity, seed) -> {
                            if (entity == null) {
                                return 0.0F;
                            } else {
                                return CrossbowItem.isCharged(stack)
                                        ? 0.0F
                                        : (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks())
                                        / (float)CrossbowItem.getChargeDuration(stack, entity);
                            }
                        }
                );


        ItemProperties.register(
                ModItems.ZHU_GE.get(),
                ResourceLocation.withDefaultNamespace("pulling"),
                (stack, level, entity, seed) -> entity != null
                        && entity.isUsingItem()
                        && entity.getUseItem() == stack
                        && !CrossbowItem.isCharged(stack)
                        ? 1.0F
                        : 0.0F
        );


        ItemProperties.register(
                ModItems.ZHU_GE.get(),
                ResourceLocation.withDefaultNamespace("charged"),
                (stack, level, entity, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
        );
        ItemProperties.register(ModItems.ZHU_GE.get(), ResourceLocation.withDefaultNamespace("firework"), (stack, level, entity, seed) -> {
            ChargedProjectiles chargedprojectiles = stack.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

    }
}
