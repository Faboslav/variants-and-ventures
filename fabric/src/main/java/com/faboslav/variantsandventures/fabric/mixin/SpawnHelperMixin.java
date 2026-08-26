package com.faboslav.variantsandventures.fabric.mixin;

import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.versions.VersionedEntitySpawnReason;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=1.21.3 {
import net.minecraft.world.entity.EntitySpawnReason;
//?} else {
/*import net.minecraft.world.entity.MobSpawnType;
*///?}

@Mixin(NaturalSpawner.class)
public final class SpawnHelperMixin
{
	@Inject(
		method = "spawnCategoryForPosition(Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/NaturalSpawner$SpawnPredicate;Lnet/minecraft/world/level/NaturalSpawner$AfterSpawnCallback;)V",
		at = @At(
			value = "INVOKE",
			//? if >=1.21.5 {
			target = "Lnet/minecraft/world/entity/Mob;snapTo(DDDFF)V",
			//?} else {
			/*target = "Lnet/minecraft/world/entity/Mob;moveTo(DDDFF)V",
			*///?}
			shift = At.Shift.AFTER
		),
		cancellable = true
	)
	private static void variantsandventures$onEntitySpawn(
		CallbackInfo ci,
		@Local(ordinal = 0) LocalRef<Mob> mobRef
	) {
		Mob mob = mobRef.get();
		Mob[] replacement = new Mob[1];

		if (!EntitySpawnEvent.EVENT.invoke(new EntitySpawnEvent(
			mob,
			mob.level(),
			mob.isBaby(),
			VersionedEntitySpawnReason.NATURAL,
			entity -> {
				replacement[0] = entity;
				return true;
			}
		))) {
			return;
		}

		if (replacement[0] == null) {
			ci.cancel();
			return;
		}

		mobRef.set(replacement[0]);
	}

	@WrapOperation(
		method = "spawnMobsForChunkGeneration",
		at = @At(
			value = "INVOKE",
			//? if >=1.21.3 {
			target = "Lnet/minecraft/world/entity/Mob;checkSpawnRules(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/EntitySpawnReason;)Z"
			//?} else {
			/*target = "Lnet/minecraft/world/entity/Mob;checkSpawnRules(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;)Z"
			*///?}
		)
	)
	private static boolean variantsandventures$onCheckEntitySpawn(
		Mob instance,
		LevelAccessor worldAccess,
		//? if >=1.21.3 {
		EntitySpawnReason spawnReason,
		//?} else {
		/*MobSpawnType spawnReason,
		 *///?}
		Operation<Boolean> operation
	) {
		if (EntitySpawnEvent.EVENT.invoke(new EntitySpawnEvent(instance, worldAccess, instance.isBaby(), spawnReason))) {
			return false;
		}

		return operation.call(instance, worldAccess, spawnReason);
	}
}
