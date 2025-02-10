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

        String mappedClassName = remapper.mapClassName("intermediary", "net.minecraft.class_4059$class_9076");
        String soundEvents = 'L' + remapper.mapClassName("intermediary", "net.minecraft.class_3414")+ ';';
        String entityType = 'L' + remapper.mapClassName("intermediary", "net.minecraft.class_1299")+ ';';

        ClassTinkerers.enumBuilder(mappedClassName, soundEvents, "["+entityType)
                .addEnum("FELINE", () -> new Object[] { SoundEvents.ITEM_WOLF_ARMOR_BREAK, new EntityType[] { EntityType.CAT } })
                .build();
    }
}
