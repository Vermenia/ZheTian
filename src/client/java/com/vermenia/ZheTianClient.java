package com.vermenia;

import com.vermenia.screen.JingWenScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ZheTianClient implements ClientModInitializer {
	// 定义按键绑定
	private static KeyBinding openJingWenGuiKey;

	@Override
	public void onInitializeClient() {
		// 注册按键
		openJingWenGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.vermenia.jingwen_screen", // 按键的翻译键（用于显示名称）
				InputUtil.Type.KEYSYM, 						// 按键类型键盘
				GLFW.GLFW_KEY_G,       						// 默认 G 键打开
				"category.vermenia"     					// 按键分类（用于设置菜单中的分组）
		));

		// 注册按键检测事件
		net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
			// 检查按键是否被按下
			while (openJingWenGuiKey.wasPressed()) {
				// 打开自定义界面
				client.setScreen(new JingWenScreen(Text.literal("经文")));
			}
		});
	}
}