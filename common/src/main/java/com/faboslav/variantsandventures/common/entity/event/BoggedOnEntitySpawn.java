package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;

public final class BoggedOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		Mob entity = event.entity();

		if (event.spawnReason() == MobSpawnType.NATURAL
			|| event.spawnReason() == MobSpawnType.SPAWNER
			|| event.spawnReason() == MobSpawnType.CHUNK_GENERATION
			|| event.spawnReason() == MobSpawnType.STRUCTURE
		) {
			if (entity.getType() != EntityType.SKELETON) {
				return false;
			}

			if (VariantsAndVentures.getConfig().enableBetterBoggedSpawns == false) {
				return false;
			}

			if (event.entity().blockPosition().getY() < VariantsAndVentures.getConfig().boggedMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().boggedSpawnChance) {
				return false;
			}

			LevelAccessor worldAccess = event.worldAccess();
			Holder<Biome> biome = worldAccess.getBiome(entity.blockPosition());

			if (biome.is(VariantsAndVenturesTags.HAS_BOGGED) == false) {
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
