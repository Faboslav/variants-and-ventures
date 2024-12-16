package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.BoggedEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

public final class BoggedOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableBetterBoggedSpawns == false) {
				return false;
			}

			if (event.entity().getBlockPos().getY() < VariantsAndVentures.getConfig().boggedMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().boggedSpawnChance) {
				return false;
			}

			WorldAccess worldAccess = event.worldAccess();
			RegistryEntry<Biome> biome = worldAccess.getBiome(entity.getBlockPos());

			if (biome.isIn(VariantsAndVenturesTags.HAS_BOGGED) == false) {
				return false;
			}

			entity.convertTo(EntityType.BOGGED, true);

			/*
			BoggedEntity bogged = EntityType.BOGGED.create(
				(ServerWorld) event.worldAccess(),
				null,
				event.entity().getBlockPos(),
				event.spawnReason(),
				false,
				false
			);

			if (bogged == null) {
				return false;
			}

			bogged.copyPositionAndRotation(entity);
			bogged.prevBodyYaw = entity.prevBodyYaw;
			bogged.bodyYaw = entity.bodyYaw;
			bogged.prevHeadYaw = entity.prevHeadYaw;
			bogged.headYaw = entity.headYaw;
			bogged.setBaby(event.isBaby());
			worldAccess.spawnEntity(bogged);
			*/

			return true;
		}

		return false;
	}
}
