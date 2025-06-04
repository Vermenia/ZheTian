package com.vermenia.item;

import com.vermenia.ZheTian;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.vermenia.item.ZheTianItemGroups.ZHETIAN_ITEMS;


public class ZheTianItemsRegister {

    public static final Item TIAN_DI_JING = RegisterItems("jingwen/tian_di_jing", new JingWen("天帝经"));

    public static void Init() {
        ItemGroupEvents.modifyEntriesEvent(ZHETIAN_ITEMS).register(ZheTianItemsRegister::RegisterItemToGrop);
    }

    public static Item RegisterItems(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ZheTian.MOD_ID, id), item);
    }

    private static void RegisterItemToGrop(FabricItemGroupEntries entries) {
        entries.add(TIAN_DI_JING);
    }
}
