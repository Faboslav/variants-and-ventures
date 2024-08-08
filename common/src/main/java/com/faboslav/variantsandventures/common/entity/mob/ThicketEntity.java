package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public final class ThicketEntity extends ZombieEntity
{
	public ThicketEntity(EntityType<? extends ZombieEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_THICKET_AMBIENT.get();
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundEvent = this.getAmbientSound();
		if (soundEvent != null) {
			this.playSound(soundEvent, 0.4F, this.getSoundPitch());
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VariantsAndVenturesSoundEvents.ENTITY_THICKET_HURT.get();
	}

	@Override
	protected void playHurtSound(DamageSource source) {
		SoundEvent soundEvent = this.getHurtSound(source);
		if (soundEvent != null) {
			this.playSound(soundEvent, 0.75F, this.getSoundPitch());
		}
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_THICKET_DEATH.get();
	}

	@Override
	protected SoundEvent getStepSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_THICKET_STEP.get();
	}

	@Override
	public boolean tryAttack(Entity target) {
		this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_THICKET_ATTACK.get(), 1.0f, this.getSoundPitch());
		boolean attackResult = super.tryAttack(target);

		if (attackResult && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
			((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100), this);
		}

		return attackResult;
	}
}

