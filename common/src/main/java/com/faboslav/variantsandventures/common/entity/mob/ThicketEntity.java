package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import com.faboslav.variantsandventures.common.util.AdvancementHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;

public final class ThicketEntity extends Zombie
{
	public ThicketEntity(EntityType<? extends Zombie> entityType, Level world) {
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
			this.playSound(soundEvent, 0.35F, this.getVoicePitch());
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
			this.playSound(soundEvent, 0.6F, this.getVoicePitch());
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
	public void tick() {
		if (!VariantsAndVentures.getConfig().enableThicket) {
			this.discard();
		}

		super.tick();
	}

	@Override
	public boolean canBeAffected(MobEffectInstance mobEffectInstance) {
		//? >= 1.21.1 {
		var isPoison = mobEffectInstance.is(MobEffects.POISON);
		//?} else {
		/*var isPoison = mobEffectInstance.getEffect() == MobEffects.POISON;
		*///?}

		return !isPoison && super.canBeAffected(mobEffectInstance);
	}

	@Override
	//? if >=1.21.3 {
	public boolean doHurtTarget(ServerLevel level, Entity source)
	//?} else {
	/*public boolean doHurtTarget(Entity source)
 	*///?}
	{
		this.level().broadcastEntityEvent(this, EntityEvent.START_ATTACKING);
		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_THICKET_ATTACK.get(), 0.6f, this.getVoicePitch());
		//? if >=1.21.3 {
		boolean attackResult = super.doHurtTarget(level, source);
		//?} else {
		/*boolean attackResult = super.doHurtTarget(source);
		*///?}

		if (attackResult && this.getMainHandItem().isEmpty() && source instanceof LivingEntity) {
			((LivingEntity) source).addEffect(new MobEffectInstance(MobEffects.POISON, 100), this);
		}

		return attackResult;
	}

	@Override
	public void die(DamageSource damageSource) {
		super.die(damageSource);
		AdvancementHelper.triggerMonsterHunter(this.level(), damageSource);
	}
}

