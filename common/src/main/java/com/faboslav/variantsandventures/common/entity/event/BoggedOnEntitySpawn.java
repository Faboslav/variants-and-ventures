//? if >= 1.20.6 {
package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import com.faboslav.variantsandventures.common.versions.VersionedEntityType;

public final class BoggedOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			VersionedEntityType.SKELETON,
			VersionedEntityType.BOGGED,
			VariantsAndVentures.getConfig().enableBetterBoggedSpawns,
			VariantsAndVentures.getConfig().boggedSpawnChance,
			VariantsAndVentures.getConfig().boggedMinimumYLevel,
			VariantsAndVenturesTags.HAS_BOGGED
		);
	}
}
//?}
