package com.oldviking.cat_armor;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvents;

public class EarlyRiser implements Runnable {
    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        //region
        String mappedClassNameForLayer = remapper.mapClassName("intermediary", "net.minecraft.class_10186$class_10190");

        ClassTinkerers.enumBuilder(mappedClassNameForLayer, String.class)
                .addEnum("CAT_BODY", () -> new Object[] { "cat_body" })
                .build();
        //endregion
    }
}
