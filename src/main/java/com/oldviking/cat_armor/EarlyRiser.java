package com.oldviking.cat_armor;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class EarlyRiser implements Runnable {
    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String mappedClassName = remapper.mapClassName("intermediary", "net.minecraft.class_4059$class_9076");
        String soundEvents = 'L' + remapper.mapClassName("intermediary", "net.minecraft.class_3414")+ ';';
        Function<Identifier, Identifier> function = (id) -> { return Identifier.of("textures/entity/cat/cat_armor"); };
        ClassTinkerers.enumBuilder(mappedClassName, Function.class, soundEvents)
                .addEnum("FELINE", () -> new Object[] { function, SoundEvents.ITEM_WOLF_ARMOR_BREAK })
                .build();
    }
}
