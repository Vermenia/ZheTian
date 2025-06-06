package com.vermenia;

import com.vermenia.render.StatusRender;
import com.vermenia.screen.JingWenScreen;
import com.vermenia.screen.XiuLianScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ZheTianClient implements ClientModInitializer {
	// 经文界面绑定按键
	private static KeyBinding openJingWenGuiKey;
	// 修炼界面绑定按键
	private static KeyBinding openXiuLianGuiKey;

	@Override
	public void onInitializeClient() {
		// 注册按键
		openJingWenGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.vermenia.jingwen_screen",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_J,
				"category.vermenia"
		));
		openXiuLianGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.vermenia.xiulian_screen",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_X,
				"category.vermenia"
		));

		// 注册按键检测事件
		net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openJingWenGuiKey.wasPressed()) {
				client.setScreen(new JingWenScreen());
			}
		});
		net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openXiuLianGuiKey.wasPressed()) {
				client.setScreen(new XiuLianScreen());
			}
		});

		HudRenderCallback.EVENT.register(new StatusRender());
	}
}