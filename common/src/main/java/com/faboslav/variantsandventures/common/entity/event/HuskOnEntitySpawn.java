package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;

public final class HuskOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		Mob entity = event.entity();

		if (event.spawnReason() == MobSpawnType.NATURAL
			|| event.spawnReason() == MobSpawnType.SPAWNER
			|| event.spawnReason() == MobSpawnType.CHUNK_GENERATION
			|| event.spawnReason() == MobSpawnType.STRUCTURE
		) {
			if (entity.getType() != EntityType.ZOMBIE) {
				return false;
			}

			if (VariantsAndVentures.getConfig().enableBetterHuskSpawns == false) {
				return false;
			}

			if (event.entity().blockPosition().getY() < VariantsAndVentures.getConfig().huskMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().huskSpawnChance) {
				return false;
			}

			LevelAccessor worldAccess = event.worldAccess();
			Holder<Biome> biome = worldAccess.getBiome(entity.blockPosition());

			if (biome.is(VariantsAndVenturesTags.HAS_HUSK) == false) {
				return false;
			}

			Husk husk = EntityType.HUSK.create(
				(ServerLevel) event.worldAccess(),
				null,
				event.entity().blockPosition(),
				event.spawnReason(),
				false,
				false
			);

			if (husk == null) {
				return false;
			}

			husk.copyPosition(entity);
			husk.yBodyRotO = entity.yBodyRotO;
			husk.yBodyRot = entity.yBodyRot;
			husk.yHeadRotO = entity.yHeadRotO;
			husk.yHeadRot = entity.yHeadRot;
			husk.setBaby(event.isBaby());
			worldAccess.addFreshEntity(husk);

			return true;
		}

		return false;
	}
}
