package com.oldviking.cat_armor.item;

import com.chocohead.mm.api.ClassTinkerers;
import com.oldviking.cat_armor.CatArmor;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item CAT_ARMOR = registerItem("cat_armor",
            new AnimalArmorItem(ArmorMaterials.ARMADILLO, ClassTinkerers.getEnum(AnimalArmorItem.Type.class, "FELINE"), true, new Item.Settings().maxDamage(ArmorItem.Type.BODY.getMaxDamage(4))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CatArmor.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CatArmor.LOGGER.info("[" + CatArmor.MOD_ID + "] Registering Mod Items");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.CAT_ARMOR));
    }
}
