package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
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

public final class ThicketOnEntitySpawn
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

			if (VariantsAndVentures.getConfig().enableThicket == false || VariantsAndVentures.getConfig().enableThicketSpawns == false) {
				return false;
			}

			if (event.entity().blockPosition().getY() < VariantsAndVentures.getConfig().thicketMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().thicketSpawnChance) {
				return false;
			}

			LevelAccessor worldAccess = event.worldAccess();
			Holder<Biome> biome = worldAccess.getBiome(entity.blockPosition());

			if (biome.is(VariantsAndVenturesTags.HAS_THICKET) == false) {
				return false;
			}

			ThicketEntity thicket = VariantsAndVenturesEntityTypes.THICKET.get().create(
				(ServerLevel) event.worldAccess(),
				null,
				event.entity().blockPosition(),
				event.spawnReason(),
				false,
				false
			);

			if (thicket == null) {
				return false;
			}

			thicket.copyPosition(entity);
			thicket.yBodyRotO = entity.yBodyRotO;
			thicket.yBodyRot = entity.yBodyRot;
			thicket.yHeadRotO = entity.yHeadRotO;
			thicket.yHeadRot = entity.yHeadRot;
			thicket.setBaby(event.isBaby());
			worldAccess.addFreshEntity(thicket);

			return true;
		}

		return false;
	}
}
