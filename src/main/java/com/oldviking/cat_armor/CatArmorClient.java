package com.oldviking.cat_armor;

import com.oldviking.cat_armor.entity.client.CatArmorModel;
import com.oldviking.cat_armor.entity.client.CatArmorRenderer;
import com.oldviking.cat_armor.entity.layer.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.EntityType;

public class CatArmorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CAT_ARMOR, CatArmorModel::getTexturedModelData);
        EntityRendererRegistry.register(EntityType.CAT, CatArmorRenderer::new);
    }
}
