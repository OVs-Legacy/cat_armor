package com.oldviking.cat_armor.item;

import com.chocohead.mm.api.ClassTinkerers;
import com.oldviking.cat_armor.CatArmor;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final RegistryKey<Item> CAT_ARMOR_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CatArmor.MOD_ID, "cat_armor"));
    public static final Item CAT_ARMOR = registerItem(CAT_ARMOR_KEY,
            new AnimalArmorItem(ArmorMaterials.ARMADILLO_SCUTE, ClassTinkerers.getEnum(AnimalArmorItem.Type.class, "FELINE"), new Item.Settings().registryKey(CAT_ARMOR_KEY)));

    private static Item registerItem(RegistryKey<Item> registryKey, Item item) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    public static void registerModItems() {
        CatArmor.LOGGER.info("[" + CatArmor.MOD_ID + "] Registering Mod Items");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.CAT_ARMOR));
    }
}
