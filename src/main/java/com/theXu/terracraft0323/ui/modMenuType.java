package com.theXu.terracraft0323.ui;

import com.theXu.terracraft0323.NeoMod;
import com.theXu.terracraft0323.magicStoreCraft.magicStorageMenu;
import com.theXu.terracraft0323.ui.mainMenu.mainMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class modMenuType {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, NeoMod.MODID);
    public static final Supplier<MenuType<mainMenu>> MAIN_MENU  =MENU_TYPES.register("main_menu", () -> new MenuType<>(mainMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final Supplier<MenuType<magicStorageMenu>> TERRA_MENU =MENU_TYPES.register("mod_inventory", () -> new MenuType<>(magicStorageMenu::new, FeatureFlags.DEFAULT_FLAGS));





//
//    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
//        return MENU_TYPES.register(name, () -> IMenuTypeExtension.create(factory));
//    }

    //加入到事件总线
    public static void register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }
}
