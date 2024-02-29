package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
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

public final class GelidOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableGelid == false || VariantsAndVentures.getConfig().enableGelidSpawns == false) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().gelidSpawnChance) {
				return false;
			}

			WorldAccess worldAccess = event.worldAccess();
			RegistryEntry<Biome> biome = worldAccess.getBiome(entity.getBlockPos());

			if (biome.isIn(VariantsAndVenturesTags.HAS_GALID) == false) {
				return false;
			}

			GelidEntity gelid = VariantsAndVenturesEntityType.GELID.get().create(
				(ServerWorld) event.worldAccess(),
				null,
				null,
				null,
				event.entity().getBlockPos(),
				event.spawnReason(),
				false,
				false
			);

			if (gelid == null) {
				return false;
			}

			gelid.copyPositionAndRotation(entity);
			gelid.prevBodyYaw = entity.prevBodyYaw;
			gelid.bodyYaw = entity.bodyYaw;
			gelid.prevHeadYaw = entity.prevHeadYaw;
			gelid.headYaw = entity.headYaw;
			gelid.setBaby(event.isBaby());
			worldAccess.spawnEntity(gelid);

			return true;
		}

		return false;
	}
}
