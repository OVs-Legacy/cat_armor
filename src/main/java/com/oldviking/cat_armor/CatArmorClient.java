package com.oldviking.cat_armor;

import com.oldviking.cat_armor.entity.client.CatArmorModel;
import com.oldviking.cat_armor.entity.client.CatArmorRenderer;
import com.oldviking.cat_armor.entity.layer.ModModelLayers;
import com.oldviking.cat_armor.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.EntityType;

public class CatArmorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return tintIndex != 1 ? -1 : DyedColorComponent.getColor(stack, 0);
        }, ModItems.CAT_ARMOR);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CAT_ARMOR, CatArmorModel::getTexturedModelData);
        EntityRendererRegistry.register(EntityType.CAT, CatArmorRenderer::new);
    }
}
