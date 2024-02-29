package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

public final class StrayOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		MobEntity entity = event.entity();

		if (event.spawnReason() == SpawnReason.NATURAL
			|| event.spawnReason() == SpawnReason.SPAWNER
			|| event.spawnReason() == SpawnReason.CHUNK_GENERATION
			|| event.spawnReason() == SpawnReason.STRUCTURE
		) {
			if (entity.getType() != EntityType.SKELETON) {
				return false;
			}

			if (VariantsAndVentures.getConfig().enableBetterStraySpawns == false) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().straySpawnChance) {
				return false;
			}

			WorldAccess worldAccess = event.worldAccess();
			RegistryEntry<Biome> biome = worldAccess.getBiome(entity.getBlockPos());

			if (biome.isIn(VariantsAndVenturesTags.HAS_STRAY) == false) {
				return false;
			}

			StrayEntity stray = EntityType.STRAY.create(
				(ServerWorld) event.worldAccess(),
				null,
				null,
				null,
				event.entity().getBlockPos(),
				event.spawnReason(),
				false,
				false
			);

			if (stray == null) {
				return false;
			}

			stray.copyPositionAndRotation(entity);
			stray.prevBodyYaw = entity.prevBodyYaw;
			stray.bodyYaw = entity.bodyYaw;
			stray.prevHeadYaw = entity.prevHeadYaw;
			stray.headYaw = entity.headYaw;
			stray.setBaby(event.isBaby());
			worldAccess.spawnEntity(stray);

			return true;
		}

		return false;
	}
}
