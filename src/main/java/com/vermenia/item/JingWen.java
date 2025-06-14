package com.vermenia.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.vermenia.ZheTian.learnedJingWen;

public class JingWen extends Item {
    enum JingWenType {
        WanZheng,
        LunHai,
        DaoGong,
        SiJi,
        ShengLong,
        XianTai,
        ZhanDou
    }
    private String name;
    private JingWenType type;
    private String tooltip;

    public JingWen(String name, JingWenType type) {
        super(new Item.Settings().maxCount(1).fireproof());
        this.name = name;
        this.type = type;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public JingWen(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal(getColoredTip()));
    }

    private String getColoredTip() {
        String colorCode = switch (type) {
            case WanZheng -> "§6";   // 金色
            case LunHai   -> "§b";   // 青色
            case DaoGong  -> "§d";   // 粉色
            case SiJi     -> "§c";   // 红色
            case ShengLong-> "§a";   // 绿色
            case XianTai  -> "§e";   // 黄色
            case ZhanDou  -> "§f";
        };
        return colorCode + tooltip;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            learnedJingWen.addLearnedItem(String.valueOf(user.getName()), name);
            user.sendMessage(Text.literal("学习了" + name + "！"), false);
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    public JingWenType getType() {
        return type;
    }

    public String getTooltip() {
        return tooltip;
    }
}
