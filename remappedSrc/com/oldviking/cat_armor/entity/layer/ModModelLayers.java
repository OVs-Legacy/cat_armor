package com.oldviking.cat_armor.entity.layer;

import com.oldviking.cat_armor.CatArmor;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer CAT_ARMOR =
            new EntityModelLayer(Identifier.of(CatArmor.MOD_ID, "cat_armor"), "main");
}
