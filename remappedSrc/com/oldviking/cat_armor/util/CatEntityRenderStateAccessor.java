package com.oldviking.cat_armor.util;

import net.minecraft.item.ItemStack;

public interface CatEntityRenderStateAccessor {
    ItemStack getBodyArmor();
    void setBodyArmor(ItemStack itemStack);
}
