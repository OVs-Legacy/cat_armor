package com.oldviking.cat_armor;

import com.oldviking.cat_armor.datagen.ModItemTagProvider;
import com.oldviking.cat_armor.datagen.ModModelProvider;
import com.oldviking.cat_armor.datagen.ModRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CatArmorDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeGenerator::new);
		pack.addProvider(ModItemTagProvider::new);
	}
}
