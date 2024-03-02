package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

public final class ThicketOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableThicket == false || VariantsAndVentures.getConfig().enableThicketSpawns == false) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().thicketSpawnChance) {
				return false;
			}

			WorldAccess worldAccess = event.worldAccess();
			RegistryEntry<Biome> biome = worldAccess.getBiome(entity.getBlockPos());

			if (biome.isIn(VariantsAndVenturesTags.HAS_THICKET) == false) {
				return false;
			}

			ThicketEntity thicket = VariantsAndVenturesEntityTypes.THICKET.get().create(
				(ServerWorld) event.worldAccess(),
				null,
				null,
				event.entity().getBlockPos(),
				event.spawnReason(),
				false,
				false
			);

			if (thicket == null) {
				return false;
			}

			thicket.copyPositionAndRotation(entity);
			thicket.prevBodyYaw = entity.prevBodyYaw;
			thicket.bodyYaw = entity.bodyYaw;
			thicket.prevHeadYaw = entity.prevHeadYaw;
			thicket.headYaw = entity.headYaw;
			thicket.setBaby(event.isBaby());
			worldAccess.spawnEntity(thicket);

			return true;
		}

		return false;
	}
}
