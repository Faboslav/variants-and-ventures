package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.init.VariantsAndVenturesItems;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public final class ThicketEntity extends ZombieEntity
{
	public ThicketEntity(EntityType<? extends ZombieEntity> entityType, World world) {
		super(entityType, world);
	}

	public static boolean canSpawn(
		EntityType<StrayEntity> type,
		ServerWorldAccess world,
		SpawnReason spawnReason,
		BlockPos pos,
		Random random
	) {
		return canSpawnInDark(type, world, spawnReason, pos, random) && (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(pos));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_THICKET_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VariantsAndVenturesSoundEvents.ENTITY_THICKET_HURT.get();
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
			float difficulty = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
			((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 140 * (int) difficulty), this);
		}

		return attackResult;
	}

	@Override
	protected ItemStack getSkull() {
		return new ItemStack(VariantsAndVenturesItems.THICKET_HEAD.get());
	}
}

