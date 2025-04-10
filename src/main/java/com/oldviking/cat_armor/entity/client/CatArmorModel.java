package com.oldviking.cat_armor.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CatEntityModel;

public class CatArmorModel extends CatEntityModel {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart tail1;
    private final ModelPart tail2;

    public CatArmorModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.left_front_leg = root.getChild("left_front_leg");
        this.right_front_leg = root.getChild("right_front_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.right_hind_leg = root.getChild("right_hind_leg");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid("main", -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.25F))
                .uv(0, 24).cuboid("nose", -1.5F, -0.001F, -4.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.25F))
                .uv(0, 10).cuboid("ear1", -2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.25F))
                .uv(6, 10).cuboid("ear2", 1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.25F)), ModelTransform.origin(0.0F, 15.0F, -9.0F));

        modelPartData.addChild("body", ModelPartBuilder.create().uv(20, 0).cuboid(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 12.0F, -10.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));

        modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.25F)), ModelTransform.origin(1.2F, 14.1F, -5.0F));

        modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.25F)), ModelTransform.origin(-1.2F, 14.1F, -5.0F));

        modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.25F)), ModelTransform.origin(1.1F, 18.0F, 5.0F));

        modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.25F)), ModelTransform.origin(-1.1F, 18.0F, 5.0F));

        modelPartData.addChild("tail1", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 15F, 8.0F, 0.9F, 0.0F, 0.0F));

        modelPartData.addChild("tail2", ModelPartBuilder.create().uv(4, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 20F, 14.0F, 0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 32);
    }
}
