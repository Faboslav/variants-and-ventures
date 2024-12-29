package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public final class VerdantEntity extends AbstractSkeleton
{
	public VerdantEntity(EntityType<? extends AbstractSkeleton> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_VERDANT_AMBIENT.get();
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundEvent = this.getAmbientSound();
		if (soundEvent != null) {
			this.playSound(soundEvent, 0.25F, this.getVoicePitch());
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VariantsAndVenturesSoundEvents.ENTITY_VERDANT_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_VERDANT_DEATH.get();
	}

	public SoundEvent getStepSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_VERDANT_STEP.get();
	}

	@Override
	protected AbstractArrow getArrow(
		ItemStack arrow,
		float damageModifier,
		@Nullable ItemStack shotFrom
	) {
		AbstractArrow persistentProjectileEntity = super.getArrow(arrow, damageModifier, shotFrom);
		if (persistentProjectileEntity instanceof Arrow) {
			((Arrow) persistentProjectileEntity).addEffect(new MobEffectInstance(MobEffects.POISON, 100));
		}

		return persistentProjectileEntity;
	}
}