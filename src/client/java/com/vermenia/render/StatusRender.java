package com.vermenia.render;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class StatusRender implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.debugEnabled) return;
        // 这些数据应该请求服务端获取
        int level = 5;
        int maxLevel = 10;
        int mana = 128;
        int maxMana = 200;

        int x = 5;
        int y = 15;

        int barWidth = 100;
        int barHeight = 4;

        drawBar(context, x, y, barWidth, barHeight, level, maxLevel, 0xFFFFFF, "修炼进度");

        drawBar(context, x, y + 14, barWidth, barHeight, mana, maxMana, 0x88CCFF, "灵力");
    }

    private void drawBar(DrawContext context, int x, int y, int width, int height,
                         int current, int max, int color, String label) {
        float percent = Math.min((float) current / max, 1.0f);
        int filledWidth = (int) (percent * width);

        context.fill(x, y, x + width, y + height, 0xFF000000); // 黑色背景
        context.fill(x, y, x + filledWidth, y + height, 0xFF000000 | color); // 进度条

        // 文本：显示在进度条上方
        context.drawText(MinecraftClient.getInstance().textRenderer, label, x + 2, y - 10, 0xFFFFFF, true);
    }

    private int getCustomLevel() {
        return 5; // TODO: 从服务端同步值
    }

    private int getCustomExp() {
        return 500; // TODO: 获取当前经验
    }

    private int getMaxExp() {
        return 500; // TODO: 获取当前等级升级所需经验
    }

    private int getMaxMana() {
        return 128; // TODO: 从服务端同步值
    }
}