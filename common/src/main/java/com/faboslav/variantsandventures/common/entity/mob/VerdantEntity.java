package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public final class VerdantEntity extends AbstractSkeletonEntity
{
	public VerdantEntity(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
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
			this.playSound(soundEvent, 0.25F, this.getSoundPitch());
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

	protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
		PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier);
		if (persistentProjectileEntity instanceof ArrowEntity) {
			((ArrowEntity) persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.POISON, 100));
		}

		return persistentProjectileEntity;
	}
}