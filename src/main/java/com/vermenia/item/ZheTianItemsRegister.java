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

    // 天帝经
    public static final Item TIAN_DI_JING = RegisterItems("jingwen/tian_di_jing", new JingWen("天帝经", JingWen.JingWenType.WanZheng));
    public static final Item TIAN_DI_JING_LUN_HAI = RegisterItems("jingwen/tian_di_jing_lun_hai", new JingWen("天帝经：轮海卷", JingWen.JingWenType.LunHai));
    public static final Item TIAN_DI_JING_DAO_GONG = RegisterItems("jingwen/tian_di_jing_dao_gong", new JingWen("天帝经：道宫卷", JingWen.JingWenType.DaoGong));
    public static final Item TIAN_DI_JING_SI_JI = RegisterItems("jingwen/tian_di_jing_si_ji", new JingWen("天帝经：四极卷", JingWen.JingWenType.SiJi));
    public static final Item TIAN_DI_JING_HUA_LONG = RegisterItems("jingwen/tian_di_jing_sheng_long", new JingWen("天帝经：升龙卷", JingWen.JingWenType.ShengLong));
    public static final Item TIAN_DI_JING_XIAN_TAI = RegisterItems("jingwen/tian_di_jing_xian_tai", new JingWen("天帝经：仙台卷", JingWen.JingWenType.XianTai));
    // 药液
    public static final Item YAO_YE = RegisterItems("wupin/yao_ye", new YaoYe());
    // 材料
    public static final Item YU_SHI = RegisterItems("wupin/yu_shi", new CaiLiao());
    public static final Item YUAN_SHI = RegisterItems("wupin/yuan_shi", new CaiLiao());

    public static void Init() {
        ItemGroupEvents.modifyEntriesEvent(ZHETIAN_ITEMS).register(ZheTianItemsRegister::RegisterItemToGrop);
    }

    public static Item RegisterItems(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ZheTian.MOD_ID, id), item);
    }

    private static void RegisterItemToGrop(FabricItemGroupEntries entries) {
        entries.add(TIAN_DI_JING);
        entries.add(TIAN_DI_JING_LUN_HAI);
        entries.add(TIAN_DI_JING_DAO_GONG);
        entries.add(TIAN_DI_JING_SI_JI);
        entries.add(TIAN_DI_JING_HUA_LONG);
        entries.add(TIAN_DI_JING_XIAN_TAI);
        entries.add(YAO_YE);
        entries.add(YU_SHI);
        entries.add(YUAN_SHI);

        SettingProperties();
    }

    private static void SettingProperties() {
        ((JingWen) TIAN_DI_JING).setTooltip("天帝经是叶凡在成道后所创的经文，融合了其对道的理解，威力极强，被认为是最顶级的经文之一。");
        ((JingWen) TIAN_DI_JING_LUN_HAI).setTooltip("天帝经是叶凡在成道后所创的经文，融合了其对道的理解，威力极强，被认为是最顶级的经文之一。\n此为轮海残卷，只能修行轮海秘境");
        ((JingWen) TIAN_DI_JING_DAO_GONG).setTooltip("天帝经是叶凡在成道后所创的经文，融合了其对道的理解，威力极强，被认为是最顶级的经文之一。\n此为轮海残卷，只能修行道宫秘境");
        ((JingWen) TIAN_DI_JING_SI_JI).setTooltip("天帝经是叶凡在成道后所创的经文，融合了其对道的理解，威力极强，被认为是最顶级的经文之一。\n此为轮海残卷，只能修行四极秘境");
        ((JingWen) TIAN_DI_JING_HUA_LONG).setTooltip("天帝经是叶凡在成道后所创的经文，融合了其对道的理解，威力极强，被认为是最顶级的经文之一。\n此为轮海残卷，只能修行升龙秘境");
        ((JingWen) TIAN_DI_JING_XIAN_TAI).setTooltip("天帝经是叶凡在成道后所创的经文，融合了其对道的理解，威力极强，被认为是最顶级的经文之一。\n此为轮海残卷，只能修行仙台秘境");
        ((YaoYe) YAO_YE).setTooltip("最初级的收集灵气的物品，可以帮助凡人化开轮海，给修士补充微量灵气。\n每隔一段时间可自行恢复灵气");
    }
}
