package com.oldviking.cat_armor.mixin;

import com.oldviking.cat_armor.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity {
    protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "getHurtSound", cancellable = true)
    protected void getHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(this.shouldArmorAbsorbDamage(source) ? SoundEvents.ITEM_WOLF_ARMOR_DAMAGE : SoundEvents.ENTITY_CAT_HURT);
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/passive/CatEntity;isOwner(Lnet/minecraft/entity/LivingEntity;)Z",
            shift = At.Shift.AFTER), cancellable = true)
    private void applyCatAmor(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!this.getWorld().isClient) {
            if (player.getStackInHand(hand).getItem() == ModItems.CAT_ARMOR && this.getBodyArmor().isEmpty() && !this.isBaby() && isOwner(player)) {
                this.equipBodyArmor(player.getStackInHand(hand).copyWithCount(1));
                player.getStackInHand(hand).decrementUnlessCreative(1, player);
                cir.setReturnValue(ActionResult.SUCCESS);
            } else if (player.getStackInHand(hand).getItem() == Items.SHEARS && !this.getBodyArmor().isEmpty() && isOwner(player)) {
                cir.setReturnValue(removeCatArmor(player.getStackInHand(hand), player, hand));
            } else if (this.getBodyArmor().canRepairWith(player.getStackInHand(hand)) && this.isInSittingPose() && this.hasArmor() && this.getBodyArmor().isDamaged() && isOwner(player)) {
                cir.setReturnValue(repairCatArmor(player.getStackInHand(hand), player, hand));
            }
        }
    }

    @Override
    protected void applyDamage(ServerWorld world, DamageSource source, float amount) {
        if (!this.shouldArmorAbsorbDamage(source)) {
            super.applyDamage(world, source, amount);
        } else {
            ItemStack itemStack = this.getBodyArmor();
            int damage = itemStack.getDamage();
            int maxDamage = itemStack.getMaxDamage();
            itemStack.damage(MathHelper.ceil(amount), this, EquipmentSlot.BODY);
            if (Cracks.WOLF_ARMOR.getCrackLevel(damage, maxDamage) != Cracks.WOLF_ARMOR.getCrackLevel(this.getBodyArmor())){
                crackArmor();
            }
        }
    }

    @Unique
    private ActionResult removeCatArmor(ItemStack itemStack, PlayerEntity player, Hand hand) {
        itemStack.damage(1, player, getSlotForHand(hand));
        this.playSoundIfNotSilent(SoundEvents.ITEM_ARMOR_UNEQUIP_WOLF);
        ItemStack itemStack2 = this.getBodyArmor();
        this.equipBodyArmor(ItemStack.EMPTY);
        World world = this.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            this.dropStack(serverWorld, itemStack2);
        }
        return ActionResult.SUCCESS;
    }

    @Unique
    private ActionResult repairCatArmor(ItemStack itemStack, PlayerEntity player, Hand hand) {
        itemStack.decrement(1);
        this.playSoundIfNotSilent(SoundEvents.ITEM_WOLF_ARMOR_REPAIR);
        ItemStack itemStack2 = this.getBodyArmor();
        int i = (int) (itemStack2.getMaxDamage() * 0.125F);
        itemStack2.setDamage(Math.max(0, itemStack2.getDamage() - i));
        return ActionResult.SUCCESS;
    }

    @Unique
    private boolean shouldArmorAbsorbDamage(DamageSource source) {
        return this.hasArmor() && !source.isIn(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }

    @Unique
    public boolean hasArmor() {
        return this.getBodyArmor().isOf(ModItems.CAT_ARMOR);
    }

    @Unique
    private void crackArmor() {
        this.playSoundIfNotSilent(SoundEvents.ITEM_WOLF_ARMOR_CRACK);
        World world = this.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, Items.ARMADILLO_SCUTE.getDefaultStack()),
                    this.getX(), this.getY() + 1, this.getZ(), 20, 0.2, 0.1, 0.2, 0.1);
        }
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.CAT_FOOD);
    }

    @Nullable
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
