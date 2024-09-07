package com.theXu.terracraft0323.geo.item;


import com.theXu.terracraft0323.geo.renderer.armor.WolfArmorRenderer;
import com.theXu.terracraft0323.geo.renderer.armor.shenShengArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

/**
 * Example {@link GeoAnimatable GeoAnimatable} {@link ArmorItem} implementation
 * @see GeoItem
 * @see WolfArmorRenderer
 */
public final class shenShengArmorItem extends ArmorItem implements GeoItem {
	// Create our armor model/renderer and return it
	@Override
	public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
		consumer.accept(new GeoRenderProvider() {
			private shenShengArmorRenderer renderer;

			@Override
			public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
				if (this.renderer == null)
					this.renderer = new shenShengArmorRenderer();
				// Defer creation of our renderer then cache it so that it doesn't get instantiated too early

				return this.renderer;
			}
		});
	}


	private final RawAnimation move = RawAnimation.begin().thenPlay("animation.model.move");
	private final RawAnimation stop = RawAnimation.begin().thenPlay("animation.model.stop");

	// Let's add our animation controller
	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, 20, state -> {
			// Apply our generic idle animation.
			// Whether it plays or not is decided down below.

			Entity entity = state.getData(DataTickets.ENTITY);

			if (entity.getDeltaMovement().length() > 0.1) {
				state.setAnimation(move);
					return PlayState.CONTINUE;
			} else {
				state.setAnimation(stop);
					return PlayState.CONTINUE;
			}

		}));

	}


	public shenShengArmorItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}