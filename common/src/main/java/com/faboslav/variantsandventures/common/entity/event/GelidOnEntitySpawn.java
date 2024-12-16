package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
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

public final class GelidOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableGelid == false || VariantsAndVentures.getConfig().enableGelidSpawns == false) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().gelidSpawnChance) {
				return false;
			}

			if (event.entity().blockPosition().getY() < VariantsAndVentures.getConfig().gelidMinimumYLevel) {
				return false;
			}

			LevelAccessor worldAccess = event.worldAccess();
			Holder<Biome> biome = worldAccess.getBiome(entity.blockPosition());

			if (biome.is(VariantsAndVenturesTags.HAS_GELID) == false) {
				return false;
			}

			GelidEntity gelid = VariantsAndVenturesEntityTypes.GELID.get().create(
				(ServerLevel) event.worldAccess(),
				null,
				event.entity().blockPosition(),
				event.spawnReason(),
				false,
				false
			);

			if (gelid == null) {
				return false;
			}

			gelid.copyPosition(entity);
			gelid.yBodyRotO = entity.yBodyRotO;
			gelid.yBodyRot = entity.yBodyRot;
			gelid.yHeadRotO = entity.yHeadRotO;
			gelid.yHeadRot = entity.yHeadRot;
			gelid.setBaby(event.isBaby());
			worldAccess.addFreshEntity(gelid);

			return true;
		}

		return false;
	}
}
