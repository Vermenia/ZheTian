package com.vermenia.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LearnedJingWen extends PersistentState {
    private static boolean initialed = false;
    private static final HashMap<String, List<String>> learnedItems = new HashMap<>();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound playersNbt = new NbtCompound();
        for (String name : learnedItems.keySet()) {
            NbtList list = new NbtList();
            for (String item : learnedItems.get(name)) {
                list.add(NbtString.of(item));
            }
            playersNbt.put(name, list);
        }
        nbt.put("PlayerLearnedItems", playersNbt);
        return nbt;
    }

    public static LearnedJingWen fromNbt(NbtCompound nbt) {
        LearnedJingWen data = new LearnedJingWen();
        NbtCompound playersNbt = nbt.getCompound("PlayerLearnedItems");
        for (String player : playersNbt.getKeys()) {
            NbtList list = nbt.getList(player, NbtString.STRING_TYPE);
            List<String> learnedJingWen = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                learnedJingWen.add(list.getString(i));
            }
            data.learnedItems.put(player, learnedJingWen);
        }
        return data;
    }

    public void addLearnedItem(String name, String item) {
        List<String> learnedJingWen = learnedItems.computeIfAbsent(name, k -> new ArrayList<>());
        if (!learnedJingWen.contains(item)) {
            learnedJingWen.add(item);
            markDirty(); // 标记数据已更改，触发保存
        }
    }

    public List<String> getLearnedItems(ServerWorld world, String name) {
        if (!initialed) {
            world.getPersistentStateManager().getOrCreate(
                    LearnedJingWen::fromNbt,
                    LearnedJingWen::new,
                    "learned_jingwen"
            );
            initialed = true;
        }
        return learnedItems.computeIfAbsent(name, k -> new ArrayList<>());
    }
}
