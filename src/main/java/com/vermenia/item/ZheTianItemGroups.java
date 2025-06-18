package com.vermenia.item;

import com.vermenia.ZheTian;
import com.vermenia.block.ZheTianBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ZheTianItemGroups {

    public static final RegistryKey<ItemGroup> ZHETIAN_ITEMS = RegisterGrop("zhetian_items");
    public static final RegistryKey<ItemGroup> ZHETIAN_BLOCKS = RegisterGrop("zhetian_blocks");

    public static void Init() {
        RegisterGroups();
    }

    private static RegistryKey<ItemGroup> RegisterGrop(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(ZheTian.MOD_ID, id));
    }

    private static void RegisterGroups() {
        Registry.register(Registries.ITEM_GROUP, ZHETIAN_ITEMS,
                ItemGroup.create(null, -1).displayName(Text.translatable("itemGroup.zhetian_items"))
                        .icon(() -> new ItemStack(ZheTianItemsRegister.TIAN_DI_JING))
                        .entries((displayContext, entries) -> {
                            entries.add(ZheTianItemsRegister.TIAN_DI_JING);
                        }).build());
        Registry.register(Registries.ITEM_GROUP, ZHETIAN_BLOCKS,
                ItemGroup.create(null, -1).displayName(Text.translatable("itemGroup.zhetian_blocks"))
                        .icon(() -> new ItemStack(ZheTianBlock.YU_KUANG))
                        .entries((displayContext, entries) -> {
                            entries.add(ZheTianBlock.YU_KUANG);
                        }).build());
    }
}