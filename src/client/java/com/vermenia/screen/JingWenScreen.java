package com.vermenia.screen;

import com.vermenia.ZheTian;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class JingWenScreen extends Screen {
    static private final List<String> learnedItems = new ArrayList<>();
    public JingWenScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        if (learnedItems.isEmpty()) {
            System.out.println("请求获取已学习的经文");
            if (this.client != null) {
                ClientPlayNetworking.send(ZheTian.REQUEST_LEARNED_DATA, PacketByteBufs.create());
            }
        }
        ClientPlayNetworking.registerGlobalReceiver(ZheTian.REQUEST_LEARNED_DATA, (client, handler, buf, responseSender) -> {
            NbtCompound nbt = buf.readNbt();
            NbtList list = nbt.getList("LearnedItems", NbtString.STRING_TYPE);
            client.execute(() -> {
                learnedItems.clear();
                for (int i = 0; i < list.size(); i++) {
                    learnedItems.add(list.getString(i));
                }
                // 刷新列表
                refreshList();
            });
        });
    }

    private void refreshList() {
        this.clearChildren();

        int yOffset = this.height / 2 - 50;
        for (int i = 0; i < learnedItems.size(); i++) {
            String item = learnedItems.get(i);
            // 添加字符串（作为文本）
            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(item),
                    button -> {}
            ).dimensions(this.width / 2 - 100, yOffset + i * 30, 140, 20).build());

            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal("写成经文"),
                            button -> {
                    })
                    .dimensions(this.width / 2 + 50, yOffset + i * 30, 50, 20)
                    .build());
        }
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        // 渲染标题
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        if (learnedItems.isEmpty()) {
            context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("你还没学任何经文"), this.width / 2, 50, 0xFFFFFF);
        } else {
            refreshList();
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true; // 按 ESC 键关闭界面
    }
}
