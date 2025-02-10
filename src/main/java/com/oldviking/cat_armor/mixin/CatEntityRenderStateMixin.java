package com.oldviking.cat_armor.mixin;

import com.oldviking.cat_armor.util.CatEntityRenderStateAccessor;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.state.CatEntityRenderState;
import net.minecraft.client.render.entity.state.FelineEntityRenderState;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatEntityRenderState.class)
public abstract class CatEntityRenderStateMixin extends FelineEntityRenderState implements CatEntityRenderStateAccessor {
    @Unique
    private ItemStack bodyArmor;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void onInit(CallbackInfo ci) {
        this.bodyArmor = ItemStack.EMPTY;
    }

    @Unique
    public ItemStack getBodyArmor() {
        return this.bodyArmor;
    }

    @Unique
    public void setBodyArmor(ItemStack bodyArmor) {
        this.bodyArmor = bodyArmor;
    }
}
