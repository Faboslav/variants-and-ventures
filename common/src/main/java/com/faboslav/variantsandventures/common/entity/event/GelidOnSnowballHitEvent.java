package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;

public final class GelidOnSnowballHitEvent
{
	public static void handleSnowballHit(ProjectileHitEvent event) {
		Projectile projectile = event.projectile();

		if (
			!(projectile instanceof Snowball)
			|| !(projectile.getOwner() instanceof GelidEntity)
		) {
			return;
		}

		Entity target = event.hitResult().getEntity();

		if (!(target instanceof LivingEntity)) {
			return;
		}

		target.playSound(getImpactSound(), 1.0F, 0.4F / (target.getRandom().nextFloat() * 0.4F + 0.8F));
		float difficulty = target.level().getCurrentDifficultyAt(target.blockPosition()).getEffectiveDifficulty();
		target.hurt(projectile.getOwner().damageSources().thrown(projectile, projectile.getOwner()), 2 * difficulty);
		target.setTicksFrozen(140 * (int) difficulty);
	}

	private static SoundEvent getImpactSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_SNOWBALL_IMPACT.get();
	}
}
