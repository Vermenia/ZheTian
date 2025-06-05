package com.vermenia.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

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

    public JingWen(String name, JingWenType type) {
        super(new Item.Settings().maxCount(1).fireproof());
        this.name = name;
        this.type = type;
    }

    public JingWen(Settings settings) {
        super(settings);
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
}
