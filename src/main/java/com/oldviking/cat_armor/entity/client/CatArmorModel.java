package com.oldviking.cat_armor.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class CatArmorModel extends EntityModel<Entity> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart tail1;
    private final ModelPart tail2;

    public CatArmorModel(ModelPart root) {
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
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.25F))
                .uv(0, 24).cuboid(-1.5F, -0.02F, -4.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.25F))
                .uv(0, 10).cuboid(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.25F))
                .uv(6, 10).cuboid(1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 15.0F, -9.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(20, 0).cuboid(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 12.0F, -10.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData left_front_leg = modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(1.1F, 14.1F, -5.0F));

        ModelPartData right_front_leg = modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.1F, 14.1F, -5.0F));

        ModelPartData left_hind_leg = modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(1.1F, 18.0F, 5.0F));

        ModelPartData right_hind_leg = modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.1F, 18.0F, 5.0F));

        ModelPartData tail1 = modelPartData.addChild("tail1", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 15.5F, 8.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData tail2 = modelPartData.addChild("tail2", ModelPartBuilder.create().uv(4, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 15.5F, 16.0F, 1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        head.render(matrices, vertexConsumer, light, overlay, color);
        body.render(matrices, vertexConsumer, light, overlay, color);
        left_front_leg.render(matrices, vertexConsumer, light, overlay, color);
        right_front_leg.render(matrices, vertexConsumer, light, overlay, color);
        left_hind_leg.render(matrices, vertexConsumer, light, overlay, color);
        right_hind_leg.render(matrices, vertexConsumer, light, overlay, color);
        tail1.render(matrices, vertexConsumer, light, overlay, color);
        tail2.render(matrices, vertexConsumer, light, overlay, color);
    }
}
