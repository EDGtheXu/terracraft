package com.theXu.terracraft0323.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class registerKeyEvent {

    //注册按键
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.MAIN_MENU_KEY );
    }



    public class KeyBinding {
        // 我们添加一个我们自己的分类
        public static final String KEY_CATEGORY_EXAMPLE_MOD = "key.category.terracraft";

        // 使用KeyMapping创建一个我们自己的热键
        public static final KeyMapping MAIN_MENU_KEY = new KeyMapping(KEY_CATEGORY_EXAMPLE_MOD, KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I,"key.terracraft.mainmenu");


    }
}
