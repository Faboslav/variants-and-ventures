package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.world.entity.EntityType;

public final class BoggedOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			EntityType.SKELETON,
			EntityType.BOGGED,
			VariantsAndVentures.getConfig().vanillaMobs.enableBetterBoggedSpawns,
			VariantsAndVentures.getConfig().vanillaMobs.boggedSpawnChance,
			VariantsAndVentures.getConfig().vanillaMobs.boggedMinimumYLevel,
			VariantsAndVenturesTags.HAS_BOGGED
		);
	}
}
