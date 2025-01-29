package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.versions.VersionedEntitySpawnReason;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

public final class OnEntitySpawn
{
	public static boolean handleOnEntitySpawn(
		EntitySpawnEvent event,
		EntityType<? extends Mob> entityTypeToReplace,
		EntityType<? extends Mob> entityTypeToSpawn,
		boolean spawnCondition,
		double spawnChanceCondition,
		int minimumSpawnYLevel,
		TagKey<Biome> biomeTagKey
	) {
		Mob entity = event.entity();
		RandomSource random = entity.getRandom();

		if (event.spawnReason() == VersionedEntitySpawnReason.NATURAL
			|| event.spawnReason() == VersionedEntitySpawnReason.CHUNK_GENERATION
			|| event.spawnReason() == VersionedEntitySpawnReason.STRUCTURE
		) {
			if (entity.getType() != entityTypeToReplace) {
				return false;
			}

			if (
				!spawnCondition
				|| random.nextFloat() >= spawnChanceCondition
				|| entity.blockPosition().getY() < minimumSpawnYLevel
			) {
				return false;
			}

			LevelAccessor world = event.worldAccess();
			Holder<Biome> biome = world.getBiome(entity.blockPosition());

			if (!biome.is(biomeTagKey)) {
				return false;
			}

			Mob entityToSpawn = entityTypeToSpawn.create(entity.level()/*? >=1.21.3 {*/, event.spawnReason()/*?}*/);

			if (entityToSpawn == null) {
				return false;
			}

			entityToSpawn.moveTo(
				entity.getX(),
				entity.getY(),
				entity.getZ(),
				entityToSpawn.getRandom().nextFloat() * 360.0F,
				0.0F
			);

			entityToSpawn.copyPosition(entity);
			entityToSpawn.yBodyRotO = entity.yBodyRotO;
			entityToSpawn.yBodyRot = entity.yBodyRot;
			entityToSpawn.yHeadRotO = entity.yHeadRotO;
			entityToSpawn.yHeadRot = entity.yHeadRot;
			entityToSpawn.setBaby(event.isBaby());

			entityToSpawn.finalizeSpawn(
				(ServerLevelAccessor) world,
				world.getCurrentDifficultyAt(entity.blockPosition()),
				event.spawnReason(),
				null
			);

			world.addFreshEntity(entityToSpawn);
			return true;
		}

		return false;
	}
}
