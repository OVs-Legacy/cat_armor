package com.oldviking;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatArmor implements ModInitializer {
	public static final String MOD_ID = "cat_armor";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("["+MOD_ID+"] Initializing...");
	}
}