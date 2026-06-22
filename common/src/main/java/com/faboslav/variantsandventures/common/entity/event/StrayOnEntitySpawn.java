package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import com.faboslav.variantsandventures.common.versions.VersionedEntityType;

public final class StrayOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			VersionedEntityType.SKELETON,
			VersionedEntityType.STRAY,
			VariantsAndVentures.getConfig().enableBetterStraySpawns,
			VariantsAndVentures.getConfig().straySpawnChance,
			VariantsAndVentures.getConfig().strayMinimumYLevel,
			VariantsAndVenturesTags.HAS_STRAY
		);
	}
}
