//? if >= 1.21.11 {
package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.world.entity.EntityType;

public final class ParchedOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			EntityType.SKELETON,
			EntityType.PARCHED,
			VariantsAndVentures.getConfig().enableBetterParchedSpawns,
			VariantsAndVentures.getConfig().parchedSpawnChance,
			VariantsAndVentures.getConfig().parchedMinimumYLevel,
			VariantsAndVenturesTags.HAS_PARCHED
		);
	}
}
//?}
