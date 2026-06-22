package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import com.faboslav.variantsandventures.common.versions.VersionedEntityType;

public final class HuskOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			VersionedEntityType.ZOMBIE,
			VersionedEntityType.HUSK,
			VariantsAndVentures.getConfig().enableBetterHuskSpawns,
			VariantsAndVentures.getConfig().huskSpawnChance,
			VariantsAndVentures.getConfig().huskMinimumYLevel,
			VariantsAndVenturesTags.HAS_HUSK
		);
	}
}
