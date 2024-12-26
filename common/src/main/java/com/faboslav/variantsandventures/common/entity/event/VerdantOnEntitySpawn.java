package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;

public final class VerdantOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableVerdant == false || VariantsAndVentures.getConfig().enableVerdantSpawns == false) {
				return false;
			}

			if (event.entity().blockPosition().getY() < VariantsAndVentures.getConfig().verdantMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextInt(100) >= VariantsAndVentures.getConfig().verdantSpawnChance) {
				return false;
			}

			LevelAccessor worldAccess = event.worldAccess();
			Holder<Biome> biome = worldAccess.getBiome(entity.blockPosition());

			if (biome.is(VariantsAndVenturesTags.HAS_VERDANT) == false) {
				return false;
			}

			VerdantEntity verdant = VariantsAndVenturesEntityTypes.VERDANT.get().create(
				(ServerLevel) event.worldAccess(),
				null,
				event.entity().blockPosition(),
				event.spawnReason(),
				false,
				false
			);

			if (verdant == null) {
				return false;
			}

			verdant.copyPosition(entity);
			verdant.yBodyRotO = entity.yBodyRotO;
			verdant.yBodyRot = entity.yBodyRot;
			verdant.yHeadRotO = entity.yHeadRotO;
			verdant.yHeadRot = entity.yHeadRot;
			verdant.setBaby(event.isBaby());
			worldAccess.addFreshEntity(verdant);

			return true;
		}

		return false;
	}
}
