package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;

public final class StrayOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableBetterStraySpawns == false) {
				return false;
			}

			if (event.entity().blockPosition().getY() < VariantsAndVentures.getConfig().strayMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().straySpawnChance) {
				return false;
			}

			LevelAccessor worldAccess = event.worldAccess();
			Holder<Biome> biome = worldAccess.getBiome(entity.blockPosition());

			if (biome.is(VariantsAndVenturesTags.HAS_STRAY) == false) {
				return false;
			}

			Stray stray = EntityType.STRAY.create(
				(ServerLevel) event.worldAccess(),
				null,
				event.entity().blockPosition(),
				event.spawnReason(),
				false,
				false
			);

			if (stray == null) {
				return false;
			}

			stray.copyPosition(entity);
			stray.yBodyRotO = entity.yBodyRotO;
			stray.yBodyRot = entity.yBodyRot;
			stray.yHeadRotO = entity.yHeadRotO;
			stray.yHeadRot = entity.yHeadRot;
			stray.setBaby(event.isBaby());
			worldAccess.addFreshEntity(stray);

			return true;
		}

		return false;
	}
}
