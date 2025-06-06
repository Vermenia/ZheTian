package com.vermenia;

import com.vermenia.data.LearnedJingWen;
import com.vermenia.item.ZheTianItemGroups;
import com.vermenia.item.ZheTianItemsRegister;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ZheTian implements ModInitializer {
	public static final String MOD_ID = "zhetian";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static LearnedJingWen learnedJingWen;

	public static final Identifier REQUEST_LEARNED_DATA = Identifier.of(MOD_ID, "request_learned_data");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello ZheTian!");
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			ServerWorld overworld = server.getOverworld();
			learnedJingWen = LearnedJingWen.get(overworld);
			System.out.println("读取经文完成");
		});

		ZheTianItemGroups.Init();
		ZheTianItemsRegister.Init();

		// 注册请求数据网络包
		ServerPlayNetworking.registerGlobalReceiver(REQUEST_LEARNED_DATA, (server, player, handler, buf, responseSender) -> {
			NbtCompound nbt = new NbtCompound();
			NbtList list = new NbtList();
			List<String> jingWens = learnedJingWen.getLearnedItems(String.valueOf(player.getName()));
			for (String jingWen : jingWens) {
				list.add(NbtString.of(jingWen));
			}
			nbt.put("LearnedItems", list);
			PacketByteBuf packetBuf = PacketByteBufs.create();
			packetBuf.writeNbt(nbt);
			ServerPlayNetworking.send(player, REQUEST_LEARNED_DATA, packetBuf);
		});
	}

}