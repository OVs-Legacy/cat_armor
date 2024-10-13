package com.oldviking;

import com.oldviking.item.ModItems;
import com.oldviking.util.ModRegistries;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatArmor implements ModInitializer {
	public static final String MOD_ID = "cat_armor";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("["+MOD_ID+"] Initializing...");

		ModItems.registerModItems();
		ModRegistries.registerModRegistries();
	}
}