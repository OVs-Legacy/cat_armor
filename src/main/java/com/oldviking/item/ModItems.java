package com.oldviking.item;

import com.oldviking.CatArmor;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CatArmor.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CatArmor.LOGGER.info("[" + CatArmor.MOD_ID + "] Registering Mod Items");
    }
}
