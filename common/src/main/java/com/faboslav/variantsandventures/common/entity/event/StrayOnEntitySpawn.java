package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.world.entity.EntityType;
public final class StrayOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			EntityType.SKELETON,
			EntityType.STRAY,
			VariantsAndVentures.getConfig().getVanillaMobsConfig().enableBetterStraySpawns,
			VariantsAndVentures.getConfig().getVanillaMobsConfig().straySpawnChance,
			VariantsAndVentures.getConfig().getVanillaMobsConfig().strayMinimumYLevel,
			VariantsAndVenturesTags.HAS_STRAY
		);
	}
}
