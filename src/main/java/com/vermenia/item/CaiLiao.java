package com.vermenia.item;

import net.minecraft.item.Item;

public class CaiLiao extends Item {
    public CaiLiao() {
        super(new Settings().maxCount(64));
    }
    public CaiLiao(Settings settings) {
        super(settings);
    }
}
