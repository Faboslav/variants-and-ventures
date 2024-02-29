package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.sound.SoundEvent;

public final class GelidOnSnowballHitEvent
{
	public static void handleSnowballHit(ProjectileHitEvent event) {
		ProjectileEntity projectile = event.projectile();

		if (
			projectile instanceof SnowballEntity == false
			|| projectile.getOwner() instanceof GelidEntity == false
		) {
			return;
		}

		Entity target = event.hitResult().getEntity();

		if (target instanceof LivingEntity == false) {
			return;
		}

		target.playSound(getImpactSound(), 1.0F, 0.4F / (((LivingEntity) target).getRandom().nextFloat() * 0.4F + 0.8F));
		float difficulty = target.getWorld().getLocalDifficulty(target.getBlockPos()).getLocalDifficulty();
		target.damage(DamageSource.thrownProjectile(projectile, projectile.getOwner()), 2 * difficulty);
		target.setFrozenTicks(140 * (int) difficulty);
	}

	private static SoundEvent getImpactSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_SNOWBALL_IMPACT.get();
	}
}
