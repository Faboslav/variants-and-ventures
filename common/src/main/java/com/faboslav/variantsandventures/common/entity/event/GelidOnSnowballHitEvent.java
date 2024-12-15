package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;

public final class GelidOnSnowballHitEvent
{
	public static void handleSnowballHit(ProjectileHitEvent event) {
		ProjectileEntity projectile = event.projectile();

		if (
			!(projectile instanceof SnowballEntity)
			|| !(projectile.getOwner() instanceof GelidEntity)
		) {
			return;
		}

		Entity target = event.hitResult().getEntity();

		if (!(target instanceof LivingEntity)) {
			return;
		}

		var world = target.getWorld();

		if(!(world instanceof ServerWorld serverWorld)) {
			return;
		}

		target.playSound(getImpactSound(), 1.0F, 0.4F / (target.getRandom().nextFloat() * 0.4F + 0.8F));
		float difficulty = serverWorld.getLocalDifficulty(target.getBlockPos()).getLocalDifficulty();
		target.damage(serverWorld, projectile.getOwner().getDamageSources().thrown(projectile, projectile.getOwner()), 2 * difficulty);
		target.setFrozenTicks(140 * (int) difficulty);
	}

	private static SoundEvent getImpactSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_SNOWBALL_IMPACT.get();
	}
}
