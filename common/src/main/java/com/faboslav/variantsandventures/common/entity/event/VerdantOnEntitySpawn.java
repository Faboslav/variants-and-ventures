package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityType;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

public final class VerdantOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableVerdant == false || VariantsAndVentures.getConfig().enableVerdantSpawns == false) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().verdantSpawnChance) {
				return false;
			}

			WorldAccess worldAccess = event.worldAccess();
			RegistryEntry<Biome> biome = worldAccess.getBiome(entity.getBlockPos());

			if (biome.isIn(VariantsAndVenturesTags.HAS_VERDANT) == false) {
				return false;
			}

			VerdantEntity verdant = VariantsAndVenturesEntityType.VERDANT.get().create(
				(ServerWorld) event.worldAccess(),
				null,
				null,
				null,
				event.entity().getBlockPos(),
				event.spawnReason(),
				false,
				false
			);

			if (verdant == null) {
				return false;
			}

			verdant.copyPositionAndRotation(entity);
			verdant.prevBodyYaw = entity.prevBodyYaw;
			verdant.bodyYaw = entity.bodyYaw;
			verdant.prevHeadYaw = entity.prevHeadYaw;
			verdant.headYaw = entity.headYaw;
			verdant.setBaby(event.isBaby());
			worldAccess.spawnEntity(verdant);

			return true;
		}

		return false;
	}
}
