package com.faboslav.variantsandventures.neoforge.mixin;

import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SpawnHelper.class)
public final class SpawnHelperMixin
{
	@WrapOperation(
		method = "spawnEntitiesInChunk(Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/chunk/Chunk;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/SpawnHelper$Checker;Lnet/minecraft/world/SpawnHelper$Runner;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/SpawnHelper;isValidSpawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/mob/MobEntity;D)Z"
		)
	)
	private static boolean variantsandventures$onEntitySpawn(
		ServerWorld serverWorld,
		MobEntity mob,
		double d,
		Operation<Boolean> operation
	) {
		if (EntitySpawnEvent.EVENT.invoke(new EntitySpawnEvent(mob, serverWorld, mob.isBaby(), SpawnReason.NATURAL))) {
			return false;
		}
		return operation.call(serverWorld, mob, d);
	}

	@WrapOperation(
		method = "populateEntities",
		at = @At(
			value = "INVOKE",
			target = "Lnet/neoforged/neoforge/event/EventHooks;checkSpawnPosition(Lnet/minecraft/entity/mob/MobEntity;Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/entity/SpawnReason;)Z"
		)
	)
	private static boolean variantsandventures$onCheckEntitySpawn(
		MobEntity instance,
		ServerWorldAccess worldAccess,
		SpawnReason spawnReason,
		Operation<Boolean> operation
	) {
		if (EntitySpawnEvent.EVENT.invoke(new EntitySpawnEvent(instance, worldAccess, instance.isBaby(), spawnReason))) {
			return false;
		}
		return operation.call(instance, worldAccess, spawnReason);
	}
}
