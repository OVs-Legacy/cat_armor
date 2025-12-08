package com.oldviking.cat_armor.util;

import com.chocohead.mm.api.ClassTinkerers;
import com.oldviking.cat_armor.CatArmor;
import com.oldviking.cat_armor.entity.layer.ModModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.CatEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.passive.Cracks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class CatArmorFeatureRenderer extends FeatureRenderer<CatEntityRenderState, CatEntityModel> {
    public static final RegistryKey<EquipmentAsset> CAT_ARMOR_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(CatArmor.MOD_ID, "cat_armor"));

    private final CatEntityModel model;
    private final EquipmentRenderer equipmentRenderer;
    private static final Map<Cracks.CrackLevel, Identifier> CRACK_TEXTURES;

    public CatArmorFeatureRenderer(FeatureRendererContext<CatEntityRenderState, CatEntityModel> context, LoadedEntityModels loader, EquipmentRenderer equipmentRenderer) {
        super(context);
        this.model = new CatEntityModel(loader.getModelPart(ModModelLayers.CAT_ARMOR));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CatEntityRenderState state, float limbAngle, float limbDistance) {
        ItemStack bodyArmor = ((CatEntityRenderStateAccessor) state).getBodyArmor();
        EquippableComponent equippableComponent = bodyArmor.get(DataComponentTypes.EQUIPPABLE);
        if(equippableComponent != null && equippableComponent.assetId().isPresent()) {
            CatEntityModel catEntityModel = this.model;
            catEntityModel.setAngles(state);
            // This should be done differently, right?
            matrices.scale(0.8F, 0.8F, 0.8F);
            matrices.translate(0.0F, 0.38F, 0.0F);
            this.equipmentRenderer.render(ClassTinkerers.getEnum(EquipmentModel.LayerType.class, "CAT_BODY"), CAT_ARMOR_KEY, catEntityModel, bodyArmor, matrices, vertexConsumers, light);
            this.renderCracks(matrices, vertexConsumers, light, bodyArmor, catEntityModel);
        }
    }

    private void renderCracks(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, ItemStack itemStack, CatEntityModel catEntityModel) {
        Cracks.CrackLevel crackLevel = Cracks.WOLF_ARMOR.getCrackLevel(itemStack);
        if (crackLevel != Cracks.CrackLevel.NONE) {
            Identifier identifier = CRACK_TEXTURES.get(crackLevel);
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.createArmorTranslucent(identifier));
            model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        }
    }

    static {
        CRACK_TEXTURES = Map.of(Cracks.CrackLevel.LOW, Identifier.of(CatArmor.MOD_ID,"textures/entity/cat/cat_armor_crackiness_low.png"), Cracks.CrackLevel.MEDIUM, Identifier.of(CatArmor.MOD_ID,"textures/entity/cat/cat_armor_crackiness_medium.png"), Cracks.CrackLevel.HIGH, Identifier.of(CatArmor.MOD_ID,"textures/entity/cat/cat_armor_crackiness_high.png"));
    }
}
