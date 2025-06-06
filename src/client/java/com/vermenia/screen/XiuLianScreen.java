package com.vermenia.screen;

import com.vermenia.ZheTian;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

/*
-----------------------------------------------------
|             轮海秘境功法: 天帝经: 轮海卷   更换  详细信息           |
|             道宫秘境功法: 天帝经: 道宫卷   更换  详细信息           |
|             四极秘境功法: 天帝经: 四极卷   更换  详细信息           |
|             化龙秘境功法: 天帝经: 化龙卷   更换  详细信息           |
|             仙台秘境功法: 天帝经: 仙台卷   更换  详细信息           |
|      当前修炼进度: 100     突破所需进度: 150     突破           |
-----------------------------------------------------
 */
public class XiuLianScreen extends Screen {
    private static final String[] realms = {
        "轮海", "道宫", "四极", "化龙", "仙台"
    };
    public XiuLianScreen() {
        super(Text.literal("修炼"));
    }

    @Override
    protected void init() {
        int startX = this.width / 2 - 150;
        int startY = this.height / 2 - 80;

        for (int i = 0; i < realms.length; i++) {
            int buttonY = startY + i * 25;

            this.addDrawableChild(ButtonWidget.builder(Text.literal("更换"), btn -> {
                // 处理更换
            }).position(startX + 190, buttonY).size(50, 20).build());

            this.addDrawableChild(ButtonWidget.builder(Text.literal("详细信息"), btn -> {
                // 处理详情
            }).position(startX + 280, buttonY).size(60, 20).build());
        }

        // 修炼进度部分
        int progressY = startY + realms.length * 25 + 10;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("突破"), btn -> {
            // 处理突破逻辑
        }).position(startX + 280, progressY).size(60, 20).build());
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        int startX = this.width / 2 - 150;
        int startY = this.height / 2 - 80;

        for (int i = 0; i < realms.length; i++) {
            String realm = realms[i];
            String text = realm + "秘境功法: 天帝经: " + realm + "卷";
            context.drawText(this.textRenderer, text, startX, startY + i * 25 + 6, 0xFFFFFF, false);
        }

        // 修炼进度文字
        int progressY = startY + realms.length * 25 + 10;
        context.drawText(this.textRenderer, "当前修炼进度: 100     突破所需进度: 150", startX, progressY + 6, 0x00FFAA, false);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true; // 按 ESC 键关闭界面
    }
}
