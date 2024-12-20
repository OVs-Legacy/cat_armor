package com.oldviking.cat_armor.entity.client;

import com.oldviking.cat_armor.CatArmor;
import com.oldviking.cat_armor.util.CatArmorFeatureRenderer;
import net.minecraft.client.render.entity.CatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.state.CatEntityRenderState;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Identifier;

public class CatArmorRenderer extends CatEntityRenderer {
    public static final Identifier ARMOR_TEXTURE = Identifier.of(CatArmor.MOD_ID, "textures/entity/cat/cat_armor");

    public CatArmorRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new CatArmorFeatureRenderer(this, context.getEntityModels(), context.getEquipmentRenderer()));
    }

    @Override
    public Identifier getTexture(CatEntityRenderState catEntityRenderState) {
        return catEntityRenderState.texture;
    }
}
