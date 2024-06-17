package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

public final class HuskOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		MobEntity entity = event.entity();

		if (event.spawnReason() == SpawnReason.NATURAL
			|| event.spawnReason() == SpawnReason.SPAWNER
			|| event.spawnReason() == SpawnReason.CHUNK_GENERATION
			|| event.spawnReason() == SpawnReason.STRUCTURE
		) {
			if (entity.getType() != EntityType.ZOMBIE) {
				return false;
			}

			if (VariantsAndVentures.getConfig().enableBetterHuskSpawns == false) {
				return false;
			}

			if (event.entity().getBlockPos().getY() < VariantsAndVentures.getConfig().huskMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().huskSpawnChance) {
				return false;
			}

			WorldAccess worldAccess = event.worldAccess();
			RegistryEntry<Biome> biome = worldAccess.getBiome(entity.getBlockPos());

			if (biome.isIn(VariantsAndVenturesTags.HAS_HUSK) == false) {
				return false;
			}

			HuskEntity husk = EntityType.HUSK.create(
				(ServerWorld) event.worldAccess(),
				null,
				event.entity().getBlockPos(),
				event.spawnReason(),
				false,
				false
			);

			if (husk == null) {
				return false;
			}

			husk.copyPositionAndRotation(entity);
			husk.prevBodyYaw = entity.prevBodyYaw;
			husk.bodyYaw = entity.bodyYaw;
			husk.prevHeadYaw = entity.prevHeadYaw;
			husk.headYaw = entity.headYaw;
			husk.setBaby(event.isBaby());
			worldAccess.spawnEntity(husk);

			return true;
		}

		return false;
	}
}
