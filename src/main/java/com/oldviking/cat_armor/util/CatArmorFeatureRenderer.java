package com.oldviking.cat_armor.util;

import com.oldviking.cat_armor.CatArmor;
import com.oldviking.cat_armor.entity.layer.ModModelLayers;
import com.oldviking.cat_armor.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.Cracks;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class CatArmorFeatureRenderer extends FeatureRenderer<CatEntity, CatEntityModel<CatEntity>> {
    private final CatEntityModel<CatEntity> model;
    private static final Map<Cracks.CrackLevel, Identifier> CRACK_TEXTURES;

    public CatArmorFeatureRenderer(FeatureRendererContext<CatEntity, CatEntityModel<CatEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new CatEntityModel<>(loader.getModelPart(ModModelLayers.CAT_ARMOR));
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CatEntity catEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (catEntity.getBodyArmor().isOf(ModItems.CAT_ARMOR)) {
            ItemStack itemStack = catEntity.getBodyArmor();
            Item bodyArmor = itemStack.getItem();
            if (bodyArmor instanceof AnimalArmorItem) {
                AnimalArmorItem animalArmorItem = (AnimalArmorItem) bodyArmor;
                if (animalArmorItem.getType() == AnimalArmorItem.Type.valueOf("FELINE")) {
                    ((CatEntityModel) this.getContextModel()).copyStateTo(this.model);
                    this.model.animateModel(catEntity, limbAngle, limbDistance, tickDelta);
                    this.model.setAngles(catEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
                    VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(animalArmorItem.getEntityTexture()));
                    this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
                    this.renderDyed(matrixStack, vertexConsumerProvider, light, itemStack, animalArmorItem);
                    this.renderCracks(matrixStack, vertexConsumerProvider, light, itemStack);
                    return;
                }
            }
        }
    }

    private void renderDyed(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, ItemStack itemStack, AnimalArmorItem animalArmorItem) {
        if (itemStack.isIn(ItemTags.DYEABLE)) {
            int i = DyedColorComponent.getColor(itemStack, 0);
            if (ColorHelper.Argb.getAlpha(i) == 0) return;

            Identifier identifier = animalArmorItem.getOverlayTexture();
            if (identifier == null) return;

            this.model.render(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(identifier)), light, OverlayTexture.DEFAULT_UV, ColorHelper.Argb.fullAlpha(i));
        }
    }

    private void renderCracks(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, ItemStack itemStack) {
        Cracks.CrackLevel crackLevel = Cracks.WOLF_ARMOR.getCrackLevel(itemStack);
        if (crackLevel != Cracks.CrackLevel.NONE) {
            Identifier identifier = (Identifier) CRACK_TEXTURES.get(crackLevel);
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(identifier));
            this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        }
    }

    static {
        CRACK_TEXTURES = Map.of(Cracks.CrackLevel.LOW, Identifier.of(CatArmor.MOD_ID,"textures/entity/cat/cat_armor_crackiness_low.png"), Cracks.CrackLevel.MEDIUM, Identifier.of(CatArmor.MOD_ID,"textures/entity/cat/cat_armor_crackiness_medium.png"), Cracks.CrackLevel.HIGH, Identifier.of(CatArmor.MOD_ID,"textures/entity/cat/cat_armor_crackiness_high.png"));
    }
}
