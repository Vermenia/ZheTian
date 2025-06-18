package com.vermenia.block;


import com.vermenia.ZheTian;
import com.vermenia.item.ZheTianItemsRegister;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.vermenia.item.ZheTianItemGroups.ZHETIAN_BLOCKS;

public class ZheTianBlock {
    public static Block YU_KUANG = register("ore/yu_kuang", new Block(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static Block YUAN_SHI_KUANG = register("ore/yuan_shi_kuang", new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)));

    public static Block register(String id, Block block) {
        registerBlockItem(id, block);
        return Registry.register(Registries.BLOCK, new Identifier(ZheTian.MOD_ID, id), block);
    }

    public static void registerBlockItem(String id, Block block) {
        Registry.register(Registries.ITEM, new Identifier(ZheTian.MOD_ID, id), new BlockItem(block, new Item.Settings()));
    }

    public static void Init() {
        ItemGroupEvents.modifyEntriesEvent(ZHETIAN_BLOCKS).register(ZheTianBlock::RegisterItemToGrop);
    }


    private static void RegisterItemToGrop(FabricItemGroupEntries entries) {
        entries.add(YU_KUANG);
        entries.add(YUAN_SHI_KUANG);
    }
}
