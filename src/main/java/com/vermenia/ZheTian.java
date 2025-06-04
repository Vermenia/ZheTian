package com.vermenia;

import com.vermenia.item.ZheTianItemsRegister;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZheTian implements ModInitializer {
	public static final String MOD_ID = "zhetian";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello ZheTian!");
		ZheTianItemsRegister.Init();
	}
}