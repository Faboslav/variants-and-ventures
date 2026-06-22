package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import com.faboslav.variantsandventures.common.versions.VersionedEntityType;

public final class GelidOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			VersionedEntityType.ZOMBIE,
			VariantsAndVenturesEntityTypes.GELID.get(),
			VariantsAndVentures.getConfig().enableGelid || VariantsAndVentures.getConfig().enableGelidSpawns,
			VariantsAndVentures.getConfig().gelidSpawnChance,
			VariantsAndVentures.getConfig().gelidMinimumYLevel,
			VariantsAndVenturesTags.HAS_GELID
		);
	}
}
