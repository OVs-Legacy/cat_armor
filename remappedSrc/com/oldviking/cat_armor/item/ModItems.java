package com.oldviking.cat_armor.item;

import com.oldviking.cat_armor.CatArmor;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final RegistryKey<Item> CAT_ARMOR_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CatArmor.MOD_ID, "cat_armor"));
    public static final Item CAT_ARMOR = registerItem(CAT_ARMOR_KEY,
            new Item(new Item.Settings().maxDamage(EquipmentType.BODY.getMaxDamage(ArmorMaterials.ARMADILLO_SCUTE.durability())).attributeModifiers(ArmorMaterials.ARMADILLO_SCUTE.createAttributeModifiers(EquipmentType.BODY)).repairable(ArmorMaterials.ARMADILLO_SCUTE.repairIngredient()).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.BODY).equipSound(ArmorMaterials.ARMADILLO_SCUTE.equipSound()).model(ArmorMaterials.ARMADILLO_SCUTE.assetId()).allowedEntities(RegistryEntryList.of(EntityType.CAT.getRegistryEntry())).build()).component(DataComponentTypes.BREAK_SOUND, SoundEvents.ITEM_WOLF_ARMOR_BREAK).maxCount(1).registryKey(CAT_ARMOR_KEY)));

    private static Item registerItem(RegistryKey<Item> registryKey, Item item) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    public static void registerModItems() {
        CatArmor.LOGGER.info("[" + CatArmor.MOD_ID + "] Registering Mod Items");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.CAT_ARMOR));
    }
}
